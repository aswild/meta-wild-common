FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://zstd-support.patch"

RDEPENDS_${PN} += "zstd"
