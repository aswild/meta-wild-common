# htop Yocto recipe

DESCRIPTION = "htop process viewer"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "ncurses lmsensors"
# htop uses dlopen rather than direct linking for libsensors
RDEPENDS:${PN} = "lmsensors-libsensors"

SRCREV = "348c0a6bf4f33571835a0b6a1a0f5deb15132128"
PV = "3.4.1"

SRC_URI = "git://github.com/htop-dev/htop;protocol=https;nobranch=1 \
           file://1000-configure-no-system-directories.patch \
           file://htoprc \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF = "--disable-unwind"

do_install:append() {
    install -Dm644 ${UNPACKDIR}/htoprc ${D}${sysconfdir}/htoprc
}

FILES:${PN} += " \
    ${sysconfdir}/htoprc \
    ${datadir}/icons \
"
