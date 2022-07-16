
SUMMARY = "Asset management for Flask, to compress and merge CSS and Javascript files."
HOMEPAGE = "http://github.com/miracle2k/flask-assets"
AUTHOR = "Michael Elsdoerfer <michael@elsdoerfer.com>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2c0c455026ee819a9365678bea8b2c82"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/55/bb/c3f04674d54151875321a2aa55a82977b903d3cd6cc130ba04cbc67c6b06/Flask-Assets-2.0.tar.gz"
SRC_URI[md5sum] = "796f5c29b253e01c9cbf62c5d00cc15f"
SRC_URI[sha256sum] = "1dfdea35e40744d46aada72831f7613d67bf38e8b20ccaaa9e91fdc37aa3b8c2"

S = "${WORKDIR}/Flask-Assets-2.0"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
