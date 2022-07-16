
SUMMARY = "Filesystem events monitoring"
HOMEPAGE = "http://github.com/gorakhargosh/watchdog"
AUTHOR = "Yesudeep Mangalapilly <yesudeep@gmail.com>"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/6f/10/500580a0987363a0d9e1f3dd5cb1bba94a47e19266c6ce9dfb6cdd455758/watchdog-0.10.4.tar.gz"
SRC_URI[md5sum] = "d8fba5e25277520b65ca55952ae1341b"
SRC_URI[sha256sum] = "e38bffc89b15bafe2a131f0e1c74924cf07dcec020c2e0a26cccd208831fcd43"

S = "${WORKDIR}/watchdog-0.10.4"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
