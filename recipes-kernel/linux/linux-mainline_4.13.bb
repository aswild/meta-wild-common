# Mainline Linux 4.13 kernel

LINUX_VERSION = "4.13.12"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;branch=linux-4.13.y;tag=v${LINUX_VERSION}"

KBUILD_DEFCONFIG_x86 = "i386_defconfig"
KBUILD_DEFCONFIG_x86-64 = "x86_64_defconfig"
KCONFIG_MODE = "alldefconfig"

SRC_URI += " \
    file://overlayfs.cfg \
    file://squashfs.cfg \
    file://nouveau.cfg \
"

require linux-mainline.inc
