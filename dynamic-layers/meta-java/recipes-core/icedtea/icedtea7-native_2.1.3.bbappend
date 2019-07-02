CFLAGS_append = " -Wno-error=stringop-overflow -Wno-error=return-type -Wno-error=format-overflow"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
OPENJDK_PATCHES += "file://icedtea-fix-prefix_relocInfo-friend.patch;apply=no"
DISTRIBUTION_PATCHES += "patches/icedtea-fix-prefix_relocInfo-friend.patch"
