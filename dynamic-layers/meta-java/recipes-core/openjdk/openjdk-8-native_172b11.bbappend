# The "hotspot" component of openjdk checks the linux kernel version to make sure it's supporte.
# Rather than do some sane like a greater than or equal check, it just looks at the first
# digit of the version in `uname -r`.
# This breaks with Linux 5.0, even though it's effectively the same as the 4.x series.
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://hotspot-linux-5.patch"

# Fix compile errors on newer host GCC
FLAGS_GCC8_append = " -Wno-error=stringop-overflow -Wno-error=format-overflow"

# meta-java does some gcc version specific compile flags. Since Arch
# is on GCC 9 now, set this variable so we build right.
FLAGS_GCC9 = "${FLAGS_GCC8}"
