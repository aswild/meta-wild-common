
SUMMARY = "pathvalidate is a Python library to sanitize/validate a string such as filenames/file-paths/etc."
HOMEPAGE = "https://github.com/thombashi/pathvalidate"
AUTHOR = "Tsuyoshi Hombashi <tsuyoshi.hombashi@gmail.com>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c83e45046b59fcd90b15acc1c54e1c00"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/2e/89/7853a1ea323e848ab1e90c8930733bc19e35a935deb80d78b572c36ea33f/pathvalidate-2.5.0.tar.gz"
SRC_URI[md5sum] = "afc0343cd8c4ad732431a8b860d73330"
SRC_URI[sha256sum] = "119ba36be7e9a405d704c7b7aea4b871c757c53c9adc0ed64f40be1ed8da2781"

S = "${WORKDIR}/pathvalidate-2.5.0"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
