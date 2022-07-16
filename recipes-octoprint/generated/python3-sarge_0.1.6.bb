
SUMMARY = "A wrapper for subprocess which provides command pipeline functionality."
HOMEPAGE = "http://sarge.readthedocs.org/"
AUTHOR = "Vinay Sajip <vinay_sajip@yahoo.co.uk>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=924c72d14cb60aab9c3ac3b1d41ce585"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/2c/39/d5994d2060edef17c03e70eb8d9c4ac44ffae0294fe7bb3dc953e67133d8/sarge-0.1.6.tar.gz"
SRC_URI[md5sum] = "befddb379d166ad1dc396330f0e5391d"
SRC_URI[sha256sum] = "f48fb904e64f10ad6bef62422eaf4736acfd9b13ab64ba44822637a9dbb53265"

S = "${WORKDIR}/sarge-0.1.6"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
