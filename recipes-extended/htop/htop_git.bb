# htop Yocto recipe

DESCRIPTION = "htop process viewer"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "ncurses lmsensors"
# htop uses dlopen rather than direct linking for libsensors
RDEPENDS:${PN} = "lmsensors-libsensors"

SRCREV = "68c970c7ef4a0682760ed570b3d82388ae7ccf54"
PV = "3.3.0"

SRC_URI = "git://github.com/htop-dev/htop;protocol=https;nobranch=1 \
           file://htoprc \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-unicode HTOP_NCURSESW_CONFIG_SCRIPT='pkg-config htop'"

do_install:append() {
    install -Dm644 ${UNPACKDIR}/htoprc ${D}${sysconfdir}/htoprc
}

FILES:${PN} += " \
    ${sysconfdir}/htoprc \
    ${datadir}/icons \
"
