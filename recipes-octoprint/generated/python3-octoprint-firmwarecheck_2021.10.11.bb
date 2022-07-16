
SUMMARY = "Checks for unsafe or broken printer firmwares"
HOMEPAGE = "https://github.com/OctoPrint/OctoPrint-FirmwareCheck"
AUTHOR = "Gina Häußge <gina@octoprint.org>"
LICENSE = "AGPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=73f1eb20517c55bf9493b7dd6e480788"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/db/31/fe4ddb176b5de9930278a72ed15fdfc57470066c000f23c6fdd056bbcd8b/OctoPrint-FirmwareCheck-2021.10.11.tar.gz"
SRC_URI[md5sum] = "4e881f30c220e63b9d3d37cb95ccdd2e"
SRC_URI[sha256sum] = "506861624a8faf2633765eaf2aa6635bead8afc7471a375f399cbe5f33d0f99e"

S = "${WORKDIR}/OctoPrint-FirmwareCheck-2021.10.11"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
