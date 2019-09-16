# backport upstream commit to fix awk scripts for awk 5.0, as seen in Arch.
# https://bugs.archlinux.org/task/63654
# https://github.com/gpg/libgpg-error/commit/7865041c77f4f7005282f10f9b6666b19072fbdf
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://awk-5.0-fixes.patch"
