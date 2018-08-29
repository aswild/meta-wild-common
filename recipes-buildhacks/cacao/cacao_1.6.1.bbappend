# it's 2018 and shit still relies on 'python' being python2
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://python2.patch"
