SUMMARY = "Perl Compatible Regular Expressions"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://LICENCE;md5=3de34df49e1fe3c3b59a08dff214488b"

SRC_URI = "git://github.com/aswild/libpcre;branch=pcre2"
SRCREV = "6aa0e8f38a59f77e301d53313c3bde651a4f1f12"
PV = "10.23+${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    --enable-jit \
"

inherit autotools
