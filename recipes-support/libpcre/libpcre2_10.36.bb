DESCRIPTION = "There are two major versions of the PCRE library. The \
newest version is PCRE2, which is a re-working of the original PCRE \
library to provide an entirely new API. The original, very widely \
deployed PCRE library's API and feature are stable, future releases \
 will be for bugfixes only. All new future features will be to PCRE2, \
not the original PCRE 8.x series."
SUMMARY = "Perl Compatible Regular Expressions version 2"
HOMEPAGE = "http://www.pcre.org"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENCE;md5=60c08fab1357bfe9084b333bc33362d6"

SRC_URI = "https://ftp.pcre.org/pub/pcre/pcre2-${PV}.tar.gz \
           file://pcre-cross.patch \
"

SRC_URI[sha256sum] = "b95ddb9414f91a967a887d69617059fb672b914f56fa3d613812c1ee8e8a1a37"

CVE_PRODUCT = "pcre2"

S = "${WORKDIR}/pcre2-${PV}"

PROVIDES += "pcre2"
DEPENDS += "bzip2 zlib"

BINCONFIG = "${bindir}/pcre2-config"

inherit autotools binconfig-disabled

EXTRA_OECONF = "\
    --enable-newline-is-anycrlf \
    --enable-rebuild-chartables \
    --with-link-size=2 \
    --with-match-limit=10000000 \
    --enable-pcre2-16 \
    --enable-pcre2-32 \
    --enable-jit \
"
# Set LINK_SIZE in BUILD_CFLAGS given that the autotools bbclass use it to
# set CFLAGS_FOR_BUILD, required for the libpcre build.
BUILD_CFLAGS =+ "-DLINK_SIZE=2 -I${B}/src"
CFLAGS += "-D_REENTRANT"
CXXFLAGS_append_powerpc = " -lstdc++"

export CCLD_FOR_BUILD ="${BUILD_CCLD}"

PACKAGES =+ "libpcre2-8 libpcre2-16 libpcre2-32 libpcre2-posix pcre2grep pcre2grep-doc pcre2test pcre2test-doc"

# base package is empty, depends on main library packages
ALLOW_EMPTY_${PN} = "1"
FILES_${PN} = ""
RRECOMMENDS_${PN}_class-target = "libpcre2-8 libpcre2-16 libpcre2-32 libpcre2-posix"

SUMMARY_pcre2grep = "grep utility that uses perl 5 compatible regexes"
SUMMARY_pcre2grep-doc = "grep utility that uses perl 5 compatible regexes - docs"
SUMMARY_pcre2test = "program for testing Perl-comatible regular expressions"
SUMMARY_pcre2test-doc = "program for testing Perl-comatible regular expressions - docs"

FILES_libpcre2-8 = "${libdir}/libpcre2-8.so.*"
FILES_libpcre2-16 = "${libdir}/libpcre2-16.so.*"
FILES_libpcre2-32 = "${libdir}/libpcre2-32.so.*"
FILES_libpcre2-posix = "${libdir}/libpcre2-posix.so.*"
FILES_pcre2grep = "${bindir}/pcre2grep"
FILES_pcre2grep-doc = "${mandir}/man1/pcre2grep.1"
FILES_pcre2test = "${bindir}/pcre2test"
FILES_pcre2test-doc = "${mandir}/man1/pcre2test.1"

BBCLASSEXTEND = "native nativesdk"
