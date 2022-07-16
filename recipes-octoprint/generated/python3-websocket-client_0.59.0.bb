
SUMMARY = "WebSocket client for Python with low level API options"
HOMEPAGE = "https://github.com/websocket-client/websocket-client.git"
AUTHOR = "liris <liris.pp@gmail.com>"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c96ca6c1de8adc025adfada81d06fba5"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/48/bf/c706b56243f1641159ff211b51d3341024e1cdf63defc2b74595b6319039/websocket-client-0.59.0.tar.gz"
SRC_URI[md5sum] = "19ccf9abcd151b30975e7b52bfd02760"
SRC_URI[sha256sum] = "d376bd60eace9d437ab6d7ee16f4ab4e821c9dae591e1b783c58ebd8aaf80c5c"

S = "${WORKDIR}/websocket-client-0.59.0"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
