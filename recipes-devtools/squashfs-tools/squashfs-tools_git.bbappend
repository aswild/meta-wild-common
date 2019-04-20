# enable zstd support, requires a newer git revision
# SRCREV matches Arch package squashfs-tools 4.3-8
SRCREV = "6e242dc95485ada8d1d0b3dd9346c5243d4a517f"

# rebase oe-core fix-compat.patch, fetch from this layer
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS += "zstd"
EXTRA_OEMAKE += "ZSTD_SUPPORT=1"
