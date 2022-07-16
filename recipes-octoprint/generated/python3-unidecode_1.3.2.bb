
SUMMARY = "ASCII transliterations of Unicode text"
HOMEPAGE = ""
AUTHOR = "Tomaz Solc <tomaz.solc@tablix.org>"
LICENSE = "GPL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/41/a6/93288318cfae2fa0ca978dfe6bb94b22b7e9a9e98b6149a4af00b1e76ee8/Unidecode-1.3.2.tar.gz"
SRC_URI[md5sum] = "7bc19e0c18e914286367ea7a2721497b"
SRC_URI[sha256sum] = "669898c1528912bcf07f9819dc60df18d057f7528271e31f8ec28cc88ef27504"

S = "${WORKDIR}/Unidecode-1.3.2"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
