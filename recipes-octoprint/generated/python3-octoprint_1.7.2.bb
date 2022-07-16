
SUMMARY = "The snappy web interface for your 3D printer"
HOMEPAGE = "https://octoprint.org"
AUTHOR = "Gina Häußge <gina@octoprint.org>"
LICENSE = "AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://THIRDPARTYLICENSES.md;md5=80df32fa1c8f227d22d342822a4df9aa"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/d3/91/b655fe9bf815ba03bc42d9e2cd4758f24ae7910e99213142be795ba5e0f0/OctoPrint-1.7.2.tar.gz"
SRC_URI[md5sum] = "675f21b8cae42609f65e564880d65e30"
SRC_URI[sha256sum] = "d4c5fb2553640b74458c5b75ee69daf7ab0609cd2450bda7b9ce84c0d0977311"

S = "${WORKDIR}/OctoPrint-1.7.2"

DEPENDS += " ${PYTHON_PN}-markdown-native"
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
