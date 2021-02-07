# fix build on glibc 2.33
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://0001-linux-portdefs.h-Fix-pseudo-to-work-with-glibc-2.33.patch"
