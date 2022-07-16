
SUMMARY = "Add colours to the output of Python's logging module."
HOMEPAGE = "https://github.com/borntyping/python-colorlog"
AUTHOR = "Sam Clements <sam@borntyping.co.uk>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5c3c6ebdec7792ae12df8d1c0a46b26a"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/07/d4/ac5127f7d7e022caf740b9f624e5b9fe9a69fefc0f4f9c047b1e9298c87a/colorlog-5.0.1.tar.gz"
SRC_URI[md5sum] = "cc5d67c7d3ca686866a5ffbdeeeed39c"
SRC_URI[sha256sum] = "f17c013a06962b02f4449ee07cfdbe6b287df29efc2c9a1515b4a376f4e588ea"

S = "${WORKDIR}/colorlog-5.0.1"

DEPENDS += " ${PYTHON_PN}-setuptools-native"
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
