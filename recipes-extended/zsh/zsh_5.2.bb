# Zsh, the best shell
# Adapted from the recipe in meta-openembedded/meta-oe

DESCRIPTION = "zsh shell"
LICENSE = "zsh"
LIC_FILES_CHKSUM = "file://LICENCE;md5=b7bc853894664be455a922db9805288e"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BP}.tar.gz"
SRC_URI[md5sum] = "e79e3edfc133e2091456ca0be6f2ecb5"
SRC_URI[sha256sum] = "fa924c534c6633c219dcffdcd7da9399dabfb63347f88ce6ddcd5bb441215937"

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
