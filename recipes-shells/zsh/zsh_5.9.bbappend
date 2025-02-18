FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += " \
    file://0004-pcre2.patch \
    file://0005-52383-avoid-incompatible-pointer-types.patch \
"

DEPENDS:remove = "libpcre"
DEPENDS += "libpcre2"

EXTRA_OECONF:remove = "--disable-dynamic"
EXTRA_OECONF += "--enable-pcre ac_cv_prog_PCRE_CONFIG='pkg-config libpcre2'"
