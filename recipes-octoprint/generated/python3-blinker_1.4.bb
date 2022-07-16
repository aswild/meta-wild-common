
SUMMARY = "Fast, simple object-to-object and broadcast signaling"
HOMEPAGE = "http://pythonhosted.org/blinker/"
AUTHOR = "Jason Kirtland <jek@discorporate.us>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8baf1d53a00de619f60052e4752a89af"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/1b/51/e2a9f3b757eb802f61dc1f2b09c8c99f6eb01cf06416c0671253536517b6/blinker-1.4.tar.gz"
SRC_URI[md5sum] = "8b3722381f83c2813c52de3016b68d33"
SRC_URI[sha256sum] = "471aee25f3992bd325afa3772f1063dbdbbca947a041b8b89466dc00d606f8b6"

S = "${WORKDIR}/blinker-1.4"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
