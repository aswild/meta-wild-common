
SUMMARY = "Python implementation of Markdown."
HOMEPAGE = "https://Python-Markdown.github.io/"
AUTHOR = "Manfred Stienstra, Yuri takhteyev and Waylan limberg <waylan.limberg@icloud.com>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=745aaad0c69c60039e638bff9ffc59ed"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/ac/df/0ae25a9fd5bb528fe3c65af7143708160aa3b47970d5272003a1ad5c03c6/Markdown-3.1.1.tar.gz"
SRC_URI[md5sum] = "d84732ecc65b3a1bff693d9d4c24277f"
SRC_URI[sha256sum] = "2e50876bcdd74517e7b71f3e7a76102050edec255b3983403f1a63e7c8a41e7a"

S = "${WORKDIR}/Markdown-3.1.1"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
