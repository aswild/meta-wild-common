# fix stuff with glibc 2.33.
# This patch is a squash of commits f332f56..60e25a3 which add/fix wrappers for
# new fstatat functions that cause host user contamination in a bunch of recipes.

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://0001-pseudo-glibc-2.33-fixes.patch"
