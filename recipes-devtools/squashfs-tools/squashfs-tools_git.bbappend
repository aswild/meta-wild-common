# zstd support added in v4.4, git tag 4.4
PV = "4.4+git${SRCPV}"
SRCREV = "52eb4c279cd283ed9802dd1ceb686560b22ffb67"

# rebase oe-core fix-compat.patch, fetch from this layer
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# new patches
SRC_URI += "file://0001-squashfs-tools-fix-build-failure-against-gcc-10.patch"

PACKAGECONFIG_append = " zstd"
PACKAGECONFIG[zstd] = "ZSTD_SUPPORT=1,ZSTD_SUPPORT=0,zstd"
