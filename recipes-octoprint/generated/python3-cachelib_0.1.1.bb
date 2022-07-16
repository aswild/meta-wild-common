
SUMMARY = "A collection of cache libraries in the same API interface."
HOMEPAGE = "https://github.com/pallets/cachelib"
AUTHOR = "Pallets team <contact@palletsprojects.com>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a32a7594742a04098377c0c777ec1af7"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/0a/ce/50a08e17ae057b4afc3ef997a9846a9c335490e8137129d1d9213b7c5fe0/cachelib-0.1.1.tar.gz"
SRC_URI[md5sum] = "fb261e459dfea074cd20440e2674e102"
SRC_URI[sha256sum] = "47e95a67d68c729cbad63285a790a06f0e0d27d71624c6e44c1ec3456bb4476f"

S = "${WORKDIR}/cachelib-0.1.1"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
