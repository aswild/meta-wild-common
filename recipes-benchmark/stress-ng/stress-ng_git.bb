DESCRIPTION = "stress-ng will stress test a computer system in various selectable ways"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

TAGVER = "V0.08.12"
SRC_URI = "git://kernel.ubuntu.com/cking/stress-ng.git;tag=${TAGVER};branch=master"
PV = "${TAGVER}+git${SRCPV}"

DEPENDS = " \
    attr \
    libaio \
    libbsd \
    libcap \
    libgcrypt \
    usrsctp \
    zlib \
"

S = "${WORKDIR}/git"

# Remove -e to the Make call since the Makefile messes with CFLAGS
# Force MQ_POSIX because the check for it is broken
EXTRA_OEMAKE = "MAKEFLAGS= HAVE_MQ_POSIX=1"

do_compile() {
    rm -f ${S}/config
    oe_runmake
}

do_install() {
    oe_runmake DESTDIR=${D} install

    # The makefile uses "cp -rp" to install example jobs, which causes host user contamination
    # so manually chown to root
    chown -R root:root ${D}/usr/share/stress-ng
}
