SUMMARY = "Perl Compatible Regular Expressions version 2"
HOMEPAGE = "http://www.pcre.org"
SECDION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENCE;md5=12d55e15a0c6da5c645ba40382bd3293"

SRC_URI = " \
    https://ftp.pcre.org/pub/pcre/pcre2-10.30.tar.gz \
    file://pcre-cross.patch \
"
SRC_URI[md5sum] = "69430abc6501ec068cb65be1ca8a81f6"
SRC_URI[sha256sum] = "b549873a39f804480c2e6145a78adcba53e38162d90ef6ea92384f6ecf2fde76"

S = "${WORKDIR}/pcre2-${PV}"

PROVIDES += "pcre2"
BINCONFIG = "${bindir}/pcre2-config"

EXTRA_OECONF = " \
    --enable-jit \
    --enable-rebuild-chartables \
    --with-link-size=2 \
"

# Set build cc/cflags (for the build/native system) for building dftables properly
# This hooks in with pcre-cross.patch, since dftables should run on the build machine
BUILD_CFLAGS += "-DLINK_SIZE=2 -I${B}/src"
export CCLD_FOR_BUILD = "${BUILD_CCLD}"

CFLAGS += "-D_REENTRANT"

inherit autotools binconfig-disabled

PACKAGES =+ "${PN}-posix pcre2grep pcre2grep-doc pcre2test pcre2test-doc"
FILES_${PN}-posix = "${libdir}/libpcre2-posix.so.*"
FILES_pcre2grep = "${bindir}/pcre2grep"
FILES_pcre2grep-doc = "${mandir}/man1/pcre2grep.1"
FILES_pcre2test = "${bindir}/pcre2test"
FILES_pcre2test-doc = "${mandir}/man1/pcre2test.1"

BBCLASSEXTEND = "native nativesdk"
