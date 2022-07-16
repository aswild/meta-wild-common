
SUMMARY = "File system general utilities"
HOMEPAGE = "http://github.com/gorakhargosh/pathtools"
AUTHOR = "Yesudeep Mangalapilly <yesudeep@gmail.com>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a870e4c037fc40b3973dd56a1526fc5b"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/e7/7f/470d6fcdf23f9f3518f6b0b76be9df16dcc8630ad409947f8be2eb0ed13a/pathtools-0.1.2.tar.gz"
SRC_URI[md5sum] = "9a1af5c605768ea5804b03b734ff0f82"
SRC_URI[sha256sum] = "7c35c5421a39bb82e58018febd90e3b6e5db34c5443aaaf742b3f33d4655f1c0"

S = "${WORKDIR}/pathtools-0.1.2"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
