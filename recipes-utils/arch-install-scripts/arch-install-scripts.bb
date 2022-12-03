SUMMARY = "Arch Linux Install Scripts"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

PV = "21"
SRC_URI = "git://git.archlinux.org/arch-install-scripts.git;tag=v${PV};branch=master \
           file://0001-set-HOME-to-root-if-needed.patch \
"
S = "${WORKDIR}/git"

DEPENDS = "m4-native"
EXTRA_OEMAKE = "V=1 PREFIX='${prefix}'"

do_configure[noexec] = "1"
do_install() {
    oe_runmake DESTDIR=${D} install
}

inherit allarch

PACKAGES =+ "arch-chroot"
SUMMARY:arch-chroot = "chroot helper script from Arch Linux"
FILES:arch-chroot = "${bindir}/arch-chroot"
RDEPENDS:arch-chroot = "bash util-linux-unshare"

FILES:${PN} += "${datadir}"
RDEPENDS:${PN} = "bash arch-chroot"
