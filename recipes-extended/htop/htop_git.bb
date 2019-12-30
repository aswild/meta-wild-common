# htop Yocto recipe

DESCRIPTION = "htop process viewer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c312653532e8e669f30e5ec8bdc23be3"

DEPENDS = "ncurses"

SRCREV = "${AUTOREV}"
PV = "3.0.0b5+git${SRCPV}"
SRC_URI = "git://github.com/aswild/htop;branch=next \
           file://htoprc \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_install_append() {
    install -Dm644 ${WORKDIR}/htoprc ${D}${sysconfdir}/htoprc
}

FILES_${PN} += "${sysconfdir}/htoprc"
