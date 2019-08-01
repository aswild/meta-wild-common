SUMMARY = "FUSE driver to read/write Windows' BitLocker-ed volumes"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=6aa0d8e41ad2e57bef0712adf0cf5cb5"

PV = "0.7.1"
SRC_URI = "git://github.com/Aorimn/dislocker;tag=v${PV} \
           file://no-ruby.patch"

DEPENDS = "mbedtls fuse"

S = "${WORKDIR}/git"

inherit cmake
# disable Ruby bindings
EXTRA_OECMAKE = "-DWITH_RUBY=OFF"
# dislocker doesn't use standard CMAKE_INSTALL_*DIR variables
EXTRA_OECMAKE += " \
    -Dsharedir='${datadir}' \
    -Dmandir='${mandir}' \
    -Dlibdir='${libdir}' \
    -Dbindir='${bindir}' \
"
