# htop Yocto recipe

DESCRIPTION = "htop process viewer"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "ncurses lmsensors"
# htop uses dlopen rather than direct linking for libsensors
RDEPENDS_${PN} = "lmsensors-libsensors"

SRCREV = "29983ff83a7f2c900fadb10e6cb570d167df2d80"
PV = "3.1.0"

SRC_URI = "git://github.com/htop-dev/htop;protocol=https;nobranch=1 \
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
