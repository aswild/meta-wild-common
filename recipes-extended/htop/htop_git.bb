# htop Yocto recipe

DESCRIPTION = "htop process viewer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c312653532e8e669f30e5ec8bdc23be3"

SRC_URI = "git://github.com/hishamhm/htop file://htoprc"
SRCREV = "f80e577c5973069c1c84d7435eec60adc968da82"
PV = "2.0.2"

S = "${WORKDIR}/git"

DEPENDS = "ncurses"
RDEPENDS_${PN} = "ncurses-libncursesw"

export HTOP_NCURSESW_CONFIG_SCRIPT = "pkg-config ncursesw"
export HTOP_NCURSESW6_CONFIG_SCRIPT = "pkg-config ncursesw6"

inherit autotools

do_install_append() {
    install -Dm644 ${WORKDIR}/htoprc ${D}${sysconfdir}/htoprc
}

FILES_${PN} += "${sysconfdir}/htoprc"
