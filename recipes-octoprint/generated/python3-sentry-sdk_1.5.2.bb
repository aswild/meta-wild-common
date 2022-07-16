
SUMMARY = "Python client for Sentry (https://sentry.io)"
HOMEPAGE = "https://github.com/getsentry/sentry-python"
AUTHOR = "Sentry Team and Contributors <hello@sentry.io>"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c79f8d3c91fc847350efd28bfe0a341"

inherit setuptools3

SRC_URI = "https://files.pythonhosted.org/packages/26/a3/10731cd493c7781df05d209bbcf541b6ee0587c2d26ad2415d971de87369/sentry-sdk-1.5.2.tar.gz"
SRC_URI[md5sum] = "7b01dddd356bac8791eb4996941860f0"
SRC_URI[sha256sum] = "7bbaa32bba806ec629962f207b597e86831c7ee2c1f287c21ba7de7fea9a9c46"

S = "${WORKDIR}/sentry-sdk-1.5.2"

DEPENDS += " "
RDEPENDS_${PN} = ""

BBCLASSEXTEND = "native nativesdk"
