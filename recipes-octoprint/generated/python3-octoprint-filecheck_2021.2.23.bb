
SUMMARY = "Checks for common issues in uploaded files"
HOMEPAGE = "https://github.com/OctoPrint/OctoPrint-FileCheck"
AUTHOR = "Gina Häußge <gina@octoprint.org>"
LICENSE = "AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=73f1eb20517c55bf9493b7dd6e480788"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/f8/97/47913acffa6fe701f6141667dcfca49165daffb5416bdcf7b861b14f5701/OctoPrint-FileCheck-2021.2.23.tar.gz"
SRC_URI[md5sum] = "6e25d338e04e1143029b5f723c2180f7"
SRC_URI[sha256sum] = "1b2b43386cb07d36e06891098766ec5cc9b5284fd9b5a21dd838a9bea1d9fb2d"

S = "${WORKDIR}/OctoPrint-FileCheck-2021.2.23"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
