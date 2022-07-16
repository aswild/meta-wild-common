
SUMMARY = "Emoji for Python"
HOMEPAGE = "https://github.com/carpedm20/emoji/"
AUTHOR = "Taehoon Kim, Kevin Wurster and Tahir Jalilov <carpedm20@gmail.com>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=af8276871b79ce45d11d70c8c244aa2b"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/83/db/beeea7a485754d0841935bb7b2ad22816b2a71d3472b5eca55dce83b5d6f/emoji-1.6.3.tar.gz"
SRC_URI[md5sum] = "44b72d7fe2fd6d45cc5d49d3dc6c34f4"
SRC_URI[sha256sum] = "cc28bdc1010b1c03c241f69c7af1e8715144ef45a273bfadc14dc89319ba26d0"

S = "${WORKDIR}/emoji-1.6.3"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
