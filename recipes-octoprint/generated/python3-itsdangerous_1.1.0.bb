
SUMMARY = "Various helpers to pass data to untrusted environments and back."
HOMEPAGE = "https://palletsprojects.com/p/itsdangerous/"
AUTHOR = "Armin Ronacher <armin.ronacher@active-4.com>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=370799bf709a1e4a6a369fa089ac73a6"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/68/1a/f27de07a8a304ad5fa817bbe383d1238ac4396da447fa11ed937039fa04b/itsdangerous-1.1.0.tar.gz"
SRC_URI[md5sum] = "9b7f5afa7f1e3acfb7786eeca3d99307"
SRC_URI[sha256sum] = "321b033d07f2a4136d3ec762eac9f16a10ccd60f53c0c91af90217ace7ba1f19"

S = "${WORKDIR}/itsdangerous-1.1.0"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
