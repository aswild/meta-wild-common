# https://bugs.archlinux.org/task/59562
# https://src.fedoraproject.org/rpms/m4/blob/814d592134fad36df757f9a61422d164ea2c6c9b/f/m4-1.4.18-glibc-change-work-around.patch
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://m4-1.4.18-glibc-change-work-around.patch"
