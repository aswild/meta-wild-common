
SUMMARY = "Cross-platform network interface and IP address enumeration library"
HOMEPAGE = "https://github.com/pydron/ifaddr"
AUTHOR = "Stefan C. Mueller <scm@smurn.org>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8debe8d42320ec0ff24665319b625a5e"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/3d/fc/4ce147e3997cd0ea470ad27112087545cf83bf85015ddb3054673cb471bb/ifaddr-0.1.7.tar.gz"
SRC_URI[md5sum] = "97c4eb7505643b5f1fe17733cb42abd9"
SRC_URI[sha256sum] = "1f9e8a6ca6f16db5a37d3356f07b6e52344f6f9f7e806d618537731669eb1a94"

S = "${WORKDIR}/ifaddr-0.1.7"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
