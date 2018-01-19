# Use ABI version 6 (with extended mouse support)
EXTRA_OECONF_remove = "--with-abi-version=5"
EXTRA_OECONF += "--with-abi-version=6"

do_install_append() {
    # update the linker scripts in the .so files to use libncurses.so.6
    sed -i 's/\.so\.5/.so.6/g' ${D}${libdir}/libncurses.so ${D}${libdir}/libncursesw.so

    # fix libtinfo symlink (check if it exists first, in case of usrmere)
    [ -e ${D}${libdir}/libtinfo.so ] || lnr ${D}${base_libdir}/libtinfo.so.6 ${D}${libdir}/libtinfo.so
}
