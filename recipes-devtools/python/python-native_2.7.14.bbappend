# https://mail.python.org/pipermail/python-dev/2018-January/152011.html
# https://bugs.python.org/issue27987
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://0001-allocator-fix-for-gcc8.patch"
