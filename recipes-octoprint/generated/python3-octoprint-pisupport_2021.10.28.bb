
SUMMARY = "Provides additional information about your Pi in the UI"
HOMEPAGE = "https://github.com/OctoPrint/OctoPrint-PiSupport"
AUTHOR = "Gina Häußge <gina@octoprint.org>"
LICENSE = "AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=73f1eb20517c55bf9493b7dd6e480788"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/b2/08/fa4724ee8ed92810e8630fd050c777a8bdb114cfd4152dd75d9de4fc61bb/OctoPrint-PiSupport-2021.10.28.tar.gz"
SRC_URI[md5sum] = "f7cc45925835c02d3d88d1b374688bd1"
SRC_URI[sha256sum] = "8ee7d1946b10f5463e315d225bedda9ada0be88acdc034319977a10e164ce715"

S = "${WORKDIR}/OctoPrint-PiSupport-2021.10.28"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
