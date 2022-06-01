require openzfs.inc

SUMMARY = "OpenZFS for Linux"

inherit autotools module python3native pkgconfig

# OpenZFS builds kernel modules using autoconf. It's both clever and cursed.
EXTRA_OECONF = " \
    KERNEL_CC='${B}/kernel-cc-wrapper' \
    KERNEL_LD='${B}/kernel-ld-wrapper' \
    --with-config=kernel \
    --with-linux='${STAGING_KERNEL_DIR}' \
    --with-linux-obj='${STAGING_KERNEL_BUILDDIR}' \
"

# used by module_do_{compile,install}
MODULES_INSTALL_TARGET = "DESTDIR='${D}' install"
MODULES_MODULE_SYMVERS_LOCATION = "build"

do_configure() {
    # OpenZFS 2.1.4 allows us to set KERNEL_CC and KERNEL_LD as autoconf args, but it uses them
    # unquoted so things break if either variable contains spaces (which they do). I could patch
    # it, but instead just make wrapper scripts rather than patch files.
    install -m755 /dev/stdin ${B}/kernel-cc-wrapper <<'EOF'
#!/bin/sh
exec ${KERNEL_CC} "$@"
EOF

    install -m755 /dev/stdin ${B}/kernel-ld-wrapper <<'EOF'
#!/bin/sh
exec ${KERNEL_LD} "$@"
EOF

    autotools_do_configure
}

do_compile() {
    module_do_compile
}

do_install() {
    # hack around usermerge issues in zfs' modules_install-Linux.
    # It runs find on INSTALL_MOD_PATH/lib/modules/@LINUX_VERSION@ but we might have actually
    # installed to INSTALL_MOD_PATH/usr/lib/modules/...
    if ${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', 'true', 'false', d)}; then
        ln -s usr/lib ${D}/lib
    fi

    module_do_install

    # not needed
    rm -rf ${D}${prefix}/src

    if ${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', 'true', 'false', d)}; then
        rm ${D}/lib
    fi
}
