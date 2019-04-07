# it's 2018 and shit still relies on 'python' being python2
FILESEXTRAPATHS_prepend := "${THISDIR}/python-scons:"
SRC_URI += "file://scripts-python2.patch"
