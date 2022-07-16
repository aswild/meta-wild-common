
SUMMARY = "Universal feed parser, handles RSS 0.9x, RSS 1.0, RSS 2.0, CDF, Atom 0.3, and Atom 1.0 feeds"
HOMEPAGE = "https://github.com/kurtmckee/feedparser"
AUTHOR = "Kurt McKee <contactme@kurtmckee.org>"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4dd024c5a568d41577d68c900f653171"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/96/c2/5c5c266988142936ca81aaa22e5ef3750cce49e10c0ee9d0b9feda167491/feedparser-6.0.8.tar.gz"
SRC_URI[md5sum] = "8d0ba773e049e8f1edc2541737593a92"
SRC_URI[sha256sum] = "5ce0410a05ab248c8c7cfca3a0ea2203968ee9ff4486067379af4827a59f9661"

S = "${WORKDIR}/feedparser-6.0.8"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
