
SUMMARY = "Media asset management for Python, with glue code for various web frameworks"
HOMEPAGE = "http://github.com/miracle2k/webassets/"
AUTHOR = "Michael Elsdoerfer <michael@elsdoerfer.com>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=01c7e9175fd063ebb0a6304af80e9874"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/c1/c4/2da869584205c064614535cc626defa493b98f0d114e8f4741c99800000e/webassets-2.0.tar.gz"
SRC_URI[md5sum] = "25270b7a0452cf8a0af29898e0411c2e"
SRC_URI[sha256sum] = "167132337677c8cedc9705090f6d48da3fb262c8e0b2773b29f3352f050181cd"

S = "${WORKDIR}/webassets-2.0"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
