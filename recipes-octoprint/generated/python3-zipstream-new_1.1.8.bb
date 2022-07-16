
SUMMARY = "Zipfile generator that takes input files as well as streams"
HOMEPAGE = "https://github.com/arjan-s/python-zipstream"
AUTHOR = "arjan5 <arjan@anymore.nl>"
LICENSE = "UNKNOWN"
LIC_FILES_CHKSUM = "file://setup.py;md5=970220b6e739abcc2d91cf2da764d467"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/e5/f3/1b5228576f215b200c7e922a280a92e4494df33baae6e0280a6f45371f13/zipstream-new-1.1.8.tar.gz"
SRC_URI[md5sum] = "acd41cc94496e08bd8e86a83b214d9e0"
SRC_URI[sha256sum] = "b031fe181b94e51678389d26b174bc76382605a078d7d5d8f5beae083f111c76"

S = "${WORKDIR}/zipstream-new-1.1.8"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
