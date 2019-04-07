SUMMARY = "The Silver Searcher"
LICENSE = "Apache-2.0"

DEPENDS = "libpcre2 zlib xz"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/aswild/the_silver_searcher;branch=master"
SRCREV = "${AUTOREV}"
PV = "2.2.0+git${SRCPV}"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    --enable-lzma \
    --enable-zlib \
    ac_cv_prog_CLANG_FORMAT=no \
    ac_cv_prog_CRAM=no \
"
CONFIGUREOPT_DEPTRACK = ""

inherit bash-completion
PACKAGES += "${PN}-zsh-completion"
FILES_${PN}-zsh-completion = "${datadir}/zsh/site-functions"
