SUMMARY = "Perl Compatible Regular Expressions"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://LICENCE;md5=12d55e15a0c6da5c645ba40382bd3293"

SRC_URI = "git://github.com/aswild/libpcre;branch=pcre2"
SRCREV = "4ebe780a489334bd4aae75b4a8786c3ad1c882d4"
PV = "10.30+${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    --enable-jit \
"

inherit autotools

PACKAGES += "${PN}-posix ${PN}-util"
FILES_${PN}       = "${libdir}/libpcre2-8.so.*"
FILES_${PN}-dev  += "${bindir}/pcre2-config"
FILES_${PN}-util  = "${bindir}/pcre2grep ${bindir}/pcre2test"
FILES_${PN}-posix = "${libdir}/libpcre2-posix.so.*"
