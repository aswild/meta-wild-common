# differences from the meta-oe recipe: uses cmake rather than flat makefile
# (better parallel builds), libzstd split package, no legacy support

SUMMARY = "Zstandard - Fast real-time compression algorithm"
DESCRIPTION = "Zstandard is a fast lossless compression algorithm, targeting \
real-time compression scenarios at zlib-level and better compression ratios. \
It's backed by a very fast entropy stage, provided by Huff0 and FSE library."
HOMEPAGE = "http://www.zstd.net/"
SECTION = "console/utils"

LICENSE = "BSD-3-Clause & GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c7f0b161edbe52f5f345a3d1311d0b32 \
                    file://COPYING;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0"

PV = "1.5.2"
SRCREV = "e47e674cd09583ff0503f0f6defd6d23d8b718d3"
SRC_URI = "git://github.com/facebook/zstd;nobranch=1"
S = "${WORKDIR}/git"

inherit cmake
OECMAKE_SOURCEPATH = "${S}/build/cmake"

PACKAGES =+ "libzstd"
FILES:libzstd = "${libdir}/libzstd${SOLIBS}"

BBCLASSEXTEND = "native nativesdk"
