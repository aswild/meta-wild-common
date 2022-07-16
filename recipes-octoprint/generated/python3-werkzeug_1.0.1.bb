
SUMMARY = "The comprehensive WSGI web application library."
HOMEPAGE = "https://palletsprojects.com/p/werkzeug/"
AUTHOR = "Armin Ronacher <armin.ronacher@active-4.com>"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=5dc88300786f1c214c1e9827a5229462"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/10/27/a33329150147594eff0ea4c33c2036c0eadd933141055be0ff911f7f8d04/Werkzeug-1.0.1.tar.gz"
SRC_URI[md5sum] = "5d499cfdd30de5d9c946994783772efd"
SRC_URI[sha256sum] = "6c80b1e5ad3665290ea39320b91e1be1e0d5f60652b964a3070216de83d2e47c"

S = "${WORKDIR}/Werkzeug-1.0.1"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
