SUMMARY = "The Silver Searcher"
LICENSE = "Apache-2.0"

DEPENDS = "libpcre2 zlib xz"
RDEPENDS_${PN} = "zlib xz"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/aswild/the_silver_searcher;branch=master"
SRCREV = "${AUTOREV}"
PV = "1.0.2+${SRCPV}"

inherit autotools pkgconfig

S = "${WORKDIR}/git"
B = "${S}"

EXTRA_OECONF = " \
    --disable-silent-rules \
    --with-pcre2 \
"

FILES_${PN} = " \
    ${bindir}/ag \
    ${datadir}/* \
"
