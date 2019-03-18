# The "hotspot" component of openjdk checks the linux kernel version to make sure it's supporte.
# Rather than do some sane like a greater than or equal check, it just looks at the first
# digit of the version in `uname -r`.
# This breaks with Linux 5.0, even though it's effectively the same as the 4.x series.
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://hotspot-linux-5.patch"
