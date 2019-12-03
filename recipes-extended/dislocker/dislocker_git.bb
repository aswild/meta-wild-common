SUMMARY = "FUSE driver to read/write Windows' BitLocker-ed volumes"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=6aa0d8e41ad2e57bef0712adf0cf5cb5"

PV = "0.7.1"
SRC_URI = "git://github.com/Aorimn/dislocker;tag=v${PV} \
           https://github.com/Aorimn/dislocker/commit/f778706e72d9f7bac84a5a4cd37f0b56cebdef49.patch;name=backport \
           file://no-ruby.patch"

SRC_URI[backport.md5sum] = "f0e209d34db00f2d50dc51454c929544"
SRC_URI[backport.sha256sum] = "ad177e697e84f88ee91f7cb6a9ab3a47100665155107abc8c9e965529bc81cff"

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
