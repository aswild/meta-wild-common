
SUMMARY = "Alternative regular expression module, to replace re."
HOMEPAGE = "https://github.com/mrabarnett/mrab-regex"
AUTHOR = "Matthew Barnett <regex@mrabarnett.plus.com>"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=7b5751ddd6b643203c31ff873051d069"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/97/cd/93ad08b2f97ec95da0bd860380ce0ac7481eaccc760356ee11eda369c048/regex-2021.11.10.tar.gz"
SRC_URI[md5sum] = "695d34b744803d28e7d976c71a2179ea"
SRC_URI[sha256sum] = "f341ee2df0999bfdf7a95e448075effe0db212a59387de1a70690e4acb03d4c6"

S = "${WORKDIR}/regex-2021.11.10"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
