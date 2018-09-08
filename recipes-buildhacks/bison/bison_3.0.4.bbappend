# https://forum.openwrt.org/t/tools-bison-lib-fseterr-c-build-problems-on-18-06-0-due-to-glibc-2-28-changes/18926
FILESEXTRAPATHS_prepend_class-native := "${THISDIR}/files:"
SRC_URI_append_class-native = " file://gnulib-glibc-2.28.patch"
