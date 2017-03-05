# both cpio-doc and tar-doc try to install rmt.8, which creates errors during do_rootfs
# Remove the cpio-doc version as a workaround

do_install_append() {
    rm -f ${D}${mandir}/man8/rmt.8
}
