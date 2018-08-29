# fix build on Arch with glibc 2.28
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://0001-fflush-adjust-to-glibc-2.28-libio.h-removal.patch"
