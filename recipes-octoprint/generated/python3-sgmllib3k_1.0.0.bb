
SUMMARY = "Py3k port of sgmllib."
HOMEPAGE = "http://hg.hardcoded.net/sgmllib"
AUTHOR = "Hardcoded Software <hsoft@hardcoded.net>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://setup.py;md5=61ee5a8028a80d08091abbaac2eeae73"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/9e/bd/3704a8c3e0942d711c1299ebf7b9091930adae6675d7c8f476a7ce48653c/sgmllib3k-1.0.0.tar.gz"
SRC_URI[md5sum] = "d70efde06e40797f37e867123aa080ec"
SRC_URI[sha256sum] = "7868fb1c8bfa764c1ac563d3cf369c381d1325d36124933a726f29fcdaa812e9"

S = "${WORKDIR}/sgmllib3k-1.0.0"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
