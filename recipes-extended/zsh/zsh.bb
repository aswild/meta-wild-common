# Zsh, the best shell
# Adapted from the recipe in meta-openembedded/meta-oe

DESCRIPTION = "zsh shell"
LICENSE = "zsh"
LIC_FILES_CHKSUM = "file://LICENCE;md5=1a4c4cda3e8096d2fd483ff2f4514fec"

PV = "5.4.1"
SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BP}.tar.gz"
SRC_URI[md5sum] = "98ab8636a3c7960942e0ae6f91ae01ae"
SRC_URI[sha256sum] = "c447b832cae866f5045a1e963f9beb7231502527122242e33226db073c001ce2"

DEPENDS = " \
    bison-native \
    groff-native \
    ncurses \
    libcap \
    libpcre \
"

RDEPENDS_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd-zsh-completion', '', d)}"
RPROVIDES_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', '/bin/zsh', '', d)}"

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

EXTRA_OEMAKE = "-e MAKEFLAGS="

do_configure() {
    gnu-configize --force ${S}
    oe_runconf
}
