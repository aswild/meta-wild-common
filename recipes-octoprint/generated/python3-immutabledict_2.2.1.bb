
SUMMARY = "Immutable wrapper around dictionaries (a fork of frozendict)"
HOMEPAGE = "https://github.com/corenting/immutabledict"
AUTHOR = "Corentin Garcia <corenting@gmail.com>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dad90d31a659029404b24a25e6a445a2"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/60/a1/9250455f34bb4358fe0125814f30fcf27ddb496aab5d1f47eabe45186843/immutabledict-2.2.1.tar.gz"
SRC_URI[md5sum] = "c90adbb556ca3f96be2f15ea3e223592"
SRC_URI[sha256sum] = "1ddb0edf1bb6c70d0197eb90ce1fe2b2d58502334f5fdfde72d7c633d723ec3a"

S = "${WORKDIR}/immutabledict-2.2.1"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
