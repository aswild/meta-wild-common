
SUMMARY = "User session management for Flask"
HOMEPAGE = "https://github.com/maxcountryman/flask-login"
AUTHOR = "Matthew Frazier <leafstormrush@gmail.com>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8aa87a1cd9fa41d969ad32cfdac2c596"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/f9/01/f6c0a3a654ca125cf9cd273314c03a8bc6a47bf861765c8c1d375e15a28d/Flask-Login-0.5.0.tar.gz"
SRC_URI[md5sum] = "a2d94aa6ae935345ebc68eb3cbb5fccd"
SRC_URI[sha256sum] = "6d33aef15b5bcead780acc339464aae8a6e28f13c90d8b1cf9de8b549d1c0b4b"

S = "${WORKDIR}/Flask-Login-0.5.0"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
