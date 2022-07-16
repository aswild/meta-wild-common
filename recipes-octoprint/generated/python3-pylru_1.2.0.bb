
SUMMARY = "A least recently used (LRU) cache implementation"
HOMEPAGE = "https://github.com/jlhutch/pylru"
AUTHOR = "Jay Hutchinson <jlhutch+pylru@gmail.com>"
LICENSE = "UNKNOWN"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/9c/88/30972cd0518452563221c80bffc2a5832499d736648ef8fe492affae15c5/pylru-1.2.0.tar.gz"
SRC_URI[md5sum] = "8f0050f86d1fa68bb18673de37da543b"
SRC_URI[sha256sum] = "492f934bb98dc6c8b2370c02c95c65516ddc08c8f64d27f70087eb038621d297"

S = "${WORKDIR}/pylru-1.2.0"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
