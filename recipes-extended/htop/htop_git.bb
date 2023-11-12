# htop Yocto recipe

DESCRIPTION = "htop process viewer"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "ncurses lmsensors"
# htop uses dlopen rather than direct linking for libsensors
RDEPENDS:${PN} = "lmsensors-libsensors"

SRCREV = "55c10eccd71b6b016a50237bc8c8508b7bf66783"
PV = "3.2.2"

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
