FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://zstd-support.patch"

PACKAGECONFIG[zstd] = "--enable-zstd,--disable-zstd,zstd"
PACKAGECONFIG_append = " zstd"
