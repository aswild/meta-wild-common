# htop Yocto recipe

DESCRIPTION = "htop process viewer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4099d367cd5e59b6d4fc1ee33accb891"

DEPENDS = "ncurses lmsensors"
# htop uses dlopen rather than direct linking for libsensors
RDEPENDS_${PN} = "lmsensors-libsensors"

SRCREV = "ce6d60e7def146c13d0b8bca4642e7401a0a8995"
PV = "3.0.5"

SRC_URI = "git://github.com/htop-dev/htop \
           file://htoprc \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-unicode HTOP_NCURSESW_CONFIG_SCRIPT='pkg-config htop'"

do_install_append() {
    install -Dm644 ${WORKDIR}/htoprc ${D}${sysconfdir}/htoprc
}

FILES_${PN} += " \
    ${sysconfdir}/htoprc \
    ${datadir}/icons \
"
