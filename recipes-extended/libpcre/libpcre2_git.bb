SUMMARY = "Perl Compatible Regular Expressions"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://LICENCE;md5=ab9633efd38d6f799398df2c248b5aec"

SRC_URI = "git://github.com/aswild/libpcre;branch=pcre2"
SRCREV = "${AUTOREV}"
PV = "10.22+${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

EXTRA_OECONF = " \
    --disable-shared \
    --enable-jit \
"

inherit autotools
