
SUMMARY = "Tornado is a Python web framework and asynchronous networking library, originally developed at FriendFeed."
HOMEPAGE = "http://www.tornadoweb.org/"
AUTHOR = "Facebook <python-tornado@googlegroups.com>"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/cf/44/cc9590db23758ee7906d40cacff06c02a21c2a6166602e095a56cbf2f6f6/tornado-6.1.tar.gz"
SRC_URI[md5sum] = "f324f5e7607798552359d6ab054c4321"
SRC_URI[sha256sum] = "33c6e81d7bd55b468d2e793517c909b139960b6c790a60b7991b9b6b76fb9791"

S = "${WORKDIR}/tornado-6.1"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
