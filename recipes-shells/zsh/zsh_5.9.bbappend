FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += " \
    file://0004-pcre2.patch \
    file://0005-52383-avoid-incompatible-pointer-types.patch \
    \
    file://zprofile \
"

# allow dynamically linking modules
EXTRA_OECONF:remove = "--disable-dynamic"

# replace pcre with pcre2 (patch required)
DEPENDS:remove = "libpcre"
DEPENDS += "libpcre2"
EXTRA_OECONF += "--enable-pcre ac_cv_prog_PCRE_CONFIG='pkg-config libpcre2'"

# Use /etc/zsh/ for zprofile and related files (like Debian and Arch)
EXTRA_OECONF:remove = "--enable-etcdir=${sysconfdir}"
EXTRA_OECONF += "--enable-etcdir=${sysconfdir}/zsh"

do_install:append() {
    # install a zprofile which loads /etc/profile
    install -Dm644 ${UNPACKDIR}/zprofile ${D}${sysconfdir}/zsh/zprofile
}
