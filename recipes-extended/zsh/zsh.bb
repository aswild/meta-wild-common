# Zsh, the best shell
# Adapted from the recipe in meta-openembedded/meta-oe

DESCRIPTION = "zsh shell"
LICENSE = "zsh"
LIC_FILES_CHKSUM = "file://LICENCE;md5=b7bc853894664be455a922db9805288e"

PV = "5.3.1"
SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BP}.tar.gz"
SRC_URI[md5sum] = "d583fbca0c2410bf9542ce8a651c26ca"
SRC_URI[sha256sum] = "3d94a590ff3c562ecf387da78ac356d6bea79b050a9ef81e3ecb9f8ee513040e"

#S = "${WORKDIR}/zsh-zsh-${PV}"

DEPENDS = " \
    bison-native \
    groff-native \
    ncurses \
    libcap \
    libpcre \
"

inherit autotools gettext

bindir = "${base_bindir}"
EXTRA_OECONF = " \
    --enable-etcdir=${sysconfdir} \
    --with-term-lib='ncursesw ncurses' \
    --with-tcsetpgrp \
    --enable-cap \
    --enable-multibyte \
    --disable-gdbm \
    --disable-dynamic \
    zsh_cv_shared_environ=yes \
"

#    --enable-fndir=${datadir}/${PN}/${PV}/function \
#    --enable-site-fndir=${datadir}/${PN}/site-functions \
#

EXTRA_OEMAKE = "-e MAKEFLAGS="

do_configure() {
    gnu-configize --force ${S}
    oe_runconf
}
