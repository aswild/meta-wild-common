do_install:append() {
    # remove init script, systemd's sys-fs-fuse-connections.mount handles it
    rm -rf ${D}${sysconfdir}/init.d
}
