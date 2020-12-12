# don't install a systemd service for mongodb, and sure as hell don't auto-start it
do_install_append() {
    rm -v ${D}${systemd_system_unitdir}/mongod.service
}

SYSTEMD_SERVICE_${PN} = ""
