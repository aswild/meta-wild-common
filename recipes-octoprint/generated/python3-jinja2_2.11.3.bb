
SUMMARY = "A very fast and expressive template engine."
HOMEPAGE = "https://palletsprojects.com/p/jinja/"
AUTHOR = "Armin Ronacher <armin.ronacher@active-4.com>"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=5dc88300786f1c214c1e9827a5229462"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/4f/e7/65300e6b32e69768ded990494809106f87da1d436418d5f1367ed3966fd7/Jinja2-2.11.3.tar.gz"
SRC_URI[md5sum] = "231dc00d34afb2672c497713fa9cdaaa"
SRC_URI[sha256sum] = "a6d58433de0ae800347cab1fa3043cebbabe8baa9d29e668f1c768cb87a333c6"

S = "${WORKDIR}/Jinja2-2.11.3"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
