# don't need a test suite installed to the rootfs, and
# some of the binaries break prelinking in dev-image-tstick
do_install_append_class-target() {
    rm -rf ${D}${libdir}/nspr/tests
    # remove parent directory if empty
    rmdir ${D}${libdir}/nspr || true
}
