FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
OPENJDK_PATCHES += "file://icedtea-glibc-2.32-sysctl.patch;apply=no"
DISTRIBUTION_PATCHES += "patches/icedtea-glibc-2.32-sysctl.patch"
