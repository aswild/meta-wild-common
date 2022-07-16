
SUMMARY = "A simple framework for building complex web applications."
HOMEPAGE = "https://palletsprojects.com/p/flask/"
AUTHOR = "Armin Ronacher <armin.ronacher@active-4.com>"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=ffeffa59c90c9c4a033c7574f8f3fb75"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/4d/5b/2d145f5fe718b2f15ebe69240538f06faa8bbb76488bf962091db1f7a26d/Flask-1.1.4.tar.gz"
SRC_URI[md5sum] = "49c23fb3096ee548f9737bbddc934c41"
SRC_URI[sha256sum] = "0fbeb6180d383a9186d0d6ed954e0042ad9f18e0e8de088b2b419d526927d196"

S = "${WORKDIR}/Flask-1.1.4"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
