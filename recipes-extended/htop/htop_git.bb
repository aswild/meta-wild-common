# htop Yocto recipe

DESCRIPTION = "htop process viewer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "ncurses lmsensors"
# htop uses dlopen rather than direct linking for libsensors
RDEPENDS:${PN} = "lmsensors-libsensors"

SRCREV = "4e6ec4a0873c56ca9de63eadf730c5bd677bd8da"
PV = "3.2.1"

SRC_URI = "git://github.com/htop-dev/htop;protocol=https;nobranch=1 \
           file://htoprc \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-unicode HTOP_NCURSESW_CONFIG_SCRIPT='pkg-config htop'"

do_install:append() {
    install -Dm644 ${WORKDIR}/htoprc ${D}${sysconfdir}/htoprc
}

FILES:${PN} += " \
    ${sysconfdir}/htoprc \
    ${datadir}/icons \
"
