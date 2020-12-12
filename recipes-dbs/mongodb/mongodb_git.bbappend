# don't install a systemd service for mongodb, and sure as hell don't auto-start it
do_install_append() {
    rm -v ${D}${systemd_system_unitdir}/mongod.service
    # removing the service file leaves probably-empty directories that cause package QA errors.
    # this assumes systemd_system_unitdir=${nonarch_base_libdir}/systemd/system
    rmdir ${D}${systemd_system_unitdir}
    rmdir ${D}${nonarch_base_libdir}/systemd
    rmdir ${D}${nonarch_base_libdir} || true
}

SYSTEMD_SERVICE_${PN} = ""
