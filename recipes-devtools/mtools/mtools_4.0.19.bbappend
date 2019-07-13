# fix warning: ‘memset’ used with length equal to number of elements without
# multiplication by element size [-Wmemset-elt-size]
# Also fix "bad short file name" errors when running fsck.vfat
# https://lists.gnu.org/archive/html/info-mtools/2014-08/msg00000.html
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://direntry-dircache-fix.patch"
