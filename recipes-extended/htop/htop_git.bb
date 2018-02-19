# htop Yocto recipe

DESCRIPTION = "htop process viewer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=c312653532e8e669f30e5ec8bdc23be3"

SRCREV = "${AUTOREV}"
PV = "2.1.0+git${SRCPV}"

DEPENDS = "ncurses"
RDEPENDS_${PN} = "ncurses-libncursesw"
SRC_URI = "git://github.com/aswild/htop;branch=master \
           file://htoprc \
"
S = "${WORKDIR}/git"

export HTOP_NCURSESW_CONFIG_SCRIPT = "pkg-config ncursesw"
export HTOP_NCURSESW6_CONFIG_SCRIPT = "pkg-config ncursesw6"

inherit autotools pkgconfig

do_configure_prepend() {
    # hack to keep the git revision in the version output clean
    ( cd ${S} && git update-index --assume-unchanged INSTALL ) || true
}

do_install_append() {
    install -Dm644 ${WORKDIR}/htoprc ${D}${sysconfdir}/htoprc
}

FILES_${PN} += "${sysconfdir}/htoprc"
