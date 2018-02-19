# Zsh, the best shell
# Adapted from the recipe in meta-openembedded/meta-oe

DESCRIPTION = "zsh shell"
LICENSE = "zsh"
LIC_FILES_CHKSUM = "file://LICENCE;md5=1a4c4cda3e8096d2fd483ff2f4514fec"

PV = "5.4.2"
SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BP}.tar.gz"
SRC_URI[md5sum] = "dfe156fd69b0d8d1745ecf6d6e02e047"
SRC_URI[sha256sum] = "957bcdb2c57f64c02f673693ea5a7518ef24b6557aeb3a4ce222cefa6d74acc9"

DEPENDS = " \
    bison-native \
    groff-native \
    ncurses \
    libcap \
    libpcre \
"

# pcre is dynamically loaded, so needs to explicitly be in RDEPENDS
RDEPENDS_${PN} += "libpcre ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd-zsh-completion', '', d)}"
RPROVIDES_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', '/bin/zsh', '', d)}"

inherit autotools gettext pkgconfig

bindir = "${base_bindir}"
EXTRA_OECONF = " \
    --enable-etcdir=${sysconfdir} \
    --with-term-lib=ncursesw \
    --with-tcsetpgrp \
    --enable-cap \
    --enable-multibyte \
    --enable-pcre \
    --disable-gdbm \
    zsh_cv_shared_environ=yes \
    ac_cv_prog_PCRECONF='pkg-config libpcre' \
"

# in order to find libzsh-${PV}.so properly
LDFLAGS += "-Wl,-rpath=${libdir}/zsh"

do_configure() {
    # don't regenerate the configure script because the shipped aclocal.m4 causes problems
    gnu-configize --force ${S}
    oe_runconf
}

FILES_${PN} += "${sysconfdir}/zprofile"
do_install_append() {
    install -d ${D}${sysconfdir}
    echo "[[ -f ${sysconfdir}/profile ]] && . ${sysconfdir}/profile" >${D}${sysconfdir}/zprofile
}
