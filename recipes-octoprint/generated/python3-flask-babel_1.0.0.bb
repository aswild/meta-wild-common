
SUMMARY = "Adds i18n/l10n support to Flask applications"
HOMEPAGE = "http://github.com/python-babel/flask-babel"
AUTHOR = "Armin Ronacher <armin.ronacher@active-4.com>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=51917f3e8e858f5ae295a7d0e2eb3cc9"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/7a/73/e4a9532ca11daeead1a99251f2ec1a5faf98117b83dbfe1b30535004cd98/Flask-Babel-1.0.0.tar.gz"
SRC_URI[md5sum] = "162ccb275958f9a463211ece841a2b9a"
SRC_URI[sha256sum] = "d6a70468f9a8919d59fba2a291a003da3a05ff884275dddbd965f3b98b09ab3e"

S = "${WORKDIR}/Flask-Babel-1.0.0"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
