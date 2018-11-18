# Zsh, the best shell
# Adapted from the recipe in meta-openembedded/meta-oe

DESCRIPTION = "zsh shell"
LICENSE = "zsh"
LIC_FILES_CHKSUM = "file://LICENCE;md5=1a4c4cda3e8096d2fd483ff2f4514fec"

PV = "5.6.2"
SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BP}.tar.xz"
SRC_URI[md5sum] = "540673bf823d2e13806ac0395caa8345"
SRC_URI[sha256sum] = "a50bd66c0557e8eca3b8fa24e85d0de533e775d7a22df042da90488623752e9e"

DEPENDS = " \
    bison-native \
    groff-native \
    ncurses \
    libcap \
    libpcre \
"

# pcre is dynamically loaded, so needs to explicitly be in RDEPENDS
RDEPENDS_${PN} += "libpcre"
RRECOMMENDS_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd-zsh-completion', '', d)}"
RPROVIDES_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', '/bin/zsh', '', d)}"

inherit autotools-brokensep gettext pkgconfig

bindir = "${base_bindir}"
EXTRA_OECONF = " \
    --bindir=${base_bindir} \
    --enable-etcdir=${sysconfdir} \
    --enable-fndir=${datadir}/${PN}/${PV}/functions \
    --enable-site-fndir=${datadir}/${PN}/site-functions \
    --with-term-lib=ncursesw \
    --with-tcsetpgrp \
    --enable-cap \
    --enable-multibyte \
    --enable-pcre \
    --disable-gdbm \
    zsh_cv_shared_environ=yes \
    ac_cv_prog_PCRECONF='pkg-config libpcre' \
"

# Configure respects --bindir from EXTRA_OECONF, but then Src/Makefile will read bindir from environment
export bindir="${base_bindir}"

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
