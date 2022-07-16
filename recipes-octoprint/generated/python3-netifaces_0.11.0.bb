
SUMMARY = "Portable network interface information."
HOMEPAGE = "https://github.com/al45tair/netifaces"
AUTHOR = "Alastair Houghton <alastair@alastairs-place.net>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e4677613c25bf3673bfee98c0cc52202"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/a6/91/86a6eac449ddfae239e93ffc1918cf33fd9bab35c04d1e963b311e347a73/netifaces-0.11.0.tar.gz"
SRC_URI[md5sum] = "3146dcb3297dd018ae5eb9a52b440419"
SRC_URI[sha256sum] = "043a79146eb2907edf439899f262b3dfe41717d34124298ed281139a8b93ca32"

S = "${WORKDIR}/netifaces-0.11.0"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
