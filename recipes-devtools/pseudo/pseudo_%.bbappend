# Python build scripts need to explicitly #! python2 to build on systems where python3 is default

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append_hostpython3 += " file://1000-explicit-python2.patch"
