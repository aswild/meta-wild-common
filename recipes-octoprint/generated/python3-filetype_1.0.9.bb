
SUMMARY = "Infer file type and MIME type of any file/buffer. No external dependencies."
HOMEPAGE = "https://github.com/h2non/filetype.py"
AUTHOR = "Tomas Aparicio <tomas@aparicio.me>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cc0e8af5f14a75ce53b7b9db0f4bd91e"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/20/07/5bb16d6e9783488c0eec17228faf55ba9e7f368d59f6f50429273e1e9267/filetype-1.0.9.tar.gz"
SRC_URI[md5sum] = "a375bc5d7583b3ff4e989074fc888723"
SRC_URI[sha256sum] = "7124e1bc6a97ffc7c6bead5b8fb2e129baf312a9e60db5772bc27c741799d475"

S = "${WORKDIR}/filetype-1.0.9"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
