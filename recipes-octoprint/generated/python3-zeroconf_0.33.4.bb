
SUMMARY = "Pure Python Multicast DNS Service Discovery Library (Bonjour/Avahi compatible)"
HOMEPAGE = "https://github.com/jstasiak/python-zeroconf"
AUTHOR = "Paul Scott-Murphy, William McBrine, Jakub Stasiak <>"
LICENSE = "LGPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bb705b228ea4a14ea2728215b780d80"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/8e/52/8cb1d5fdd6ff773c7e8a7c7038a1dc1a698688821472b0e7c82c9bf93b27/zeroconf-0.33.4.tar.gz"
SRC_URI[md5sum] = "e3291ed5222108a493a64f740d8822a4"
SRC_URI[sha256sum] = "95b4fd11e0498ec8e38d0a0ff50e3bd7250be2b02d131ef8501b12cd3a499d66"

S = "${WORKDIR}/zeroconf-0.33.4"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
