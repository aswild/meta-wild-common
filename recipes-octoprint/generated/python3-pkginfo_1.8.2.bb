
SUMMARY = "Query metadatdata from sdists / bdists / installed packages."
HOMEPAGE = "https://code.launchpad.net/~tseaver/pkginfo/trunk"
AUTHOR = "Tres Seaver, Agendaless Consulting <tseaver@agendaless.com>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=6fc86b61c6077306ca1f5edc8edcc490"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/54/6a/42056522e1d79fa9768712782f37365ef786d905e4efeed6db44cad1803b/pkginfo-1.8.2.tar.gz"
SRC_URI[md5sum] = "3845080e56c0a0fc877adc50cf4465dc"
SRC_URI[sha256sum] = "542e0d0b6750e2e21c20179803e40ab50598d8066d51097a0e382cba9eb02bff"

S = "${WORKDIR}/pkginfo-1.8.2"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
