# create a disk image suitable for booting grub in both UEFI and BIOS modes
# Contains an MBR disk image with a single bootable type EF partition formatted
# as FAT32 containing:
#  * grub/
#    * i386-pc/
#    * x86_64-efi/
#    * grub.cfg
#    * other grub files
#  * vmlinuz:       kernel bzImage
#  * initramfs.img: cpio.gz initramfs
#  * rootfs.img:    squashfs rootfs image
#
# The Arch pkgbuild for grub to support BIOS and EFI is big and scary, and oe-core
# doesn't provide a suitable grub-native recipe, so for now this uses a big hack
# to put grub-install into the HOSTTOOLS dir

GRUBTOOLS = "install"
python do_link_grubtools() {
    # based on setup_hosttols_dir in base.bbclass
    tools = ['grub-' + t for t in d.getVar('GRUBTOOLS').split()]
    origenv = d.getVar('BB_ORIGENV', False)
    path = origenv.getVar('PATH')
    dest = d.getVar('HOSTTOOLS_DIR')

    notfound = []
    for tool in tools:
        desttool = os.path.join(dest, tool)
        if not os.path.exists(desttool):
            srctool = bb.utils.which(path, tool, executable=True)
            if srctool:
                os.symlink(srctool, desttool)
            else:
                notfound.append(tool)
    if notfound:
        bb.fatal("Unable to find tools '%s', needed to build grub images. Please install grub."%(', '.join(notfound)))
}
addtask link_grubtools before do_image_grub_multiboot

# sfdisk comes from util-linux. parted doesn't know how to make EF MBR partitions
do_image_grub_multiboot[depends] += " \
    mtools-native:do_populate_sysroot \
    dosfstools-native:do_populate_sysroot \
    util-linux-native:do_populate_sysroot \
    virtual/kernel:do_deploy \
"

DEPLOY_IMG_NAME     = "${IMAGE_NAME}.usb.img"
DEPLOY_IMG          = "${IMGDEPLOYDIR}/${DEPLOY_IMG_NAME}"
DEPLOY_IMG_SYMLINK  = "${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.usb.img"

IMAGE_CMD_grub-multiboot() {
    FATDEST=${WORKDIR}/fatdest
    rm -rf $FATDEST
    install -d $FATDEST


}
