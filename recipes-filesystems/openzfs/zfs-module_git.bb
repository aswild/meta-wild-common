require openzfs.inc

SUMMARY = "OpenZFS for Linux"

inherit autotools module python3native pkgconfig

# OpenZFS builds kernel modules using autoconf. It's both clever and cursed.
EXTRA_OECONF = " \
    --with-config=kernel \
    --with-linux='${STAGING_KERNEL_DIR}' \
    --with-linux-obj='${STAGING_KERNEL_BUILDDIR}' \
"

# This is used by zfs-kernel-ld.patch to force the BFD linker when building
# kernel modules during autoconf tests, since Linux's $(CROSS_COMPILE)ld may default
# to Gold with ld-is-gold distro feature. The actual main build has LD=${KERNEL_LD}
# in module_do_compile.
export KERNEL_LD

# used by module_do_{compile,install}
MODULES_INSTALL_TARGET = "DESTDIR='${D}' install"
MODULES_MODULE_SYMVERS_LOCATION = "build"

# make sure we use the right task functions since both autotools and module export
# do_compile and do_install. It seems to be correct by default but just make sure anyway
do_configure() {
    autotools_do_configure
}

do_compile() {
    module_do_compile
}

do_install() {
    module_do_install

    # not needed
    rm -rf ${D}${prefix}/src
}
