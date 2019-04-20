SUMMARY = "zstd compression tools and library"
DESCRIPTION = "A fast lossless compression algorithm, targeting real-time compression \
scenarios at zlib-level and better compression ratios"

LICENSE = "GPLv2 | BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=39bba7d2cf0ba1036f2a6e2be52fe3f0 \
                    file://LICENSE;md5=c7f0b161edbe52f5f345a3d1311d0b32"

PV = "1.4.0"
SRC_URI = "git://github.com/facebook/zstd;tag=v${PV}"
S = "${WORKDIR}/git"

inherit cmake
OECMAKE_SOURCEPATH = "${S}/build/cmake"

PACKAGES =+ "libzstd"
FILES_libzstd = "${libdir}/libzstd${SOLIBS}"

BBCLASSEXTEND = "native"
