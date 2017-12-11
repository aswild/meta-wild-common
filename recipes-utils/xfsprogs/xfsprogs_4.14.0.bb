SUMMARY = "XFS Filesystem Utilities"
HOMEPAGE = "http://oss.sgi.com/projects/xfs"
SECTION = "base"
LICENSE = "GPLv2 & LGPLv2.1"
LICENSE_libhandle = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://doc/COPYING;md5=102f7fec3d53c7c8f0b7baf9bf9d76a8"
DEPENDS = "util-linux util-linux-native"
SRC_URI = "https://www.kernel.org/pub/linux/utils/fs/xfs/xfsprogs/${BP}.tar.xz \
           file://crc32-selftest-extra-includes.patch \
           "
SRC_URI[md5sum] = "2d50e3751cc98e6c9364bc3d2297b9fd"
SRC_URI[sha256sum] = "b1b710b268bc95d6f45eca06e1262c29eb38865a19cd4404e48ba446e043b7ec"

inherit autotools-brokensep
inherit fix-usrmerge

PACKAGES =+ "${PN}-fsck ${PN}-mkfs ${PN}-repair libhandle"

DEPENDS += "util-linux"

RDEPENDS_${PN} = "${PN}-fsck ${PN}-mkfs ${PN}-repair"

FILES_${PN}-fsck = "${base_sbindir}/fsck.xfs"
FILES_${PN}-mkfs = "${base_sbindir}/mkfs.xfs"
FILES_${PN}-repair = "${base_sbindir}/xfs_repair"

FILES_libhandle = "${base_libdir}/libhandle${SOLIBS}"

EXTRA_OECONF = "--enable-gettext=no \
                INSTALL_USER=root \
                INSTALL_GROUP=root \
                ac_cv_header_aio_h=yes \
                ac_cv_lib_rt_lio_listio=yes \
"

DISABLE_STATIC = ""
EXTRA_AUTORECONF += "-I ${S}/m4 --exclude=autoheader"

PACKAGECONFIG ??= "readline blkid"

PACKAGECONFIG[readline] = "--enable-readline=yes,--enable-readline=no,readline"
PACKAGECONFIG[blkid] = "--enable-blkid=yes,--enable-blkid=no,util-linux"

export DEBUG="-DNDEBUG"
export BUILD_VERBOSE="1"
export tagname="CC"

# the CRC32 selftest can't find <linux/fsmap.h> if the build host is on an old kernel,
# so a patch adds the target include directory for this one compilation
STAGING_INCDIR[export] = "1"

EXTRA_OEMAKE = "DIST_ROOT='${D}'"

do_configure_prepend () {
    export BUILD_CC="${BUILD_CC} ${BUILD_CFLAGS}"
    # Prevent Makefile from calling configure without arguments,
    # when do_configure gets called for a second time.
    rm -f ${B}/include/builddefs ${B}/include/platform_defs.h ${B}/configure
    # Recreate configure script.
    oe_runmake configure
}

do_install_append() {
        oe_runmake 'DESTDIR=${D}' install-dev
}
