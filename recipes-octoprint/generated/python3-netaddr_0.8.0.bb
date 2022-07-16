
SUMMARY = "A network address manipulation library for Python"
HOMEPAGE = "https://github.com/drkjam/netaddr/"
AUTHOR = "David P. D. Moss, Stefan Nordhausen et al <drkjam@gmail.com>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6345d695ffe3776f68a56fe7962db44"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/c3/3b/fe5bda7a3e927d9008c897cf1a0858a9ba9924a6b4750ec1824c9e617587/netaddr-0.8.0.tar.gz"
SRC_URI[md5sum] = "34cad578473b66ad77bc3b2a7613ed4a"
SRC_URI[sha256sum] = "d6cc57c7a07b1d9d2e917aa8b36ae8ce61c35ba3fcd1b83ca31c5a0ee2b5a243"

S = "${WORKDIR}/netaddr-0.8.0"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
