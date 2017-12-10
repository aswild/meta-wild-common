FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://dhcpcd@.service"

inherit systemd
FILES_${PN} += "${systemd_unitdir}/system/dhcpcd@.service"

do_install_append() {
    # this service isn't listed in SYSTEMD_SERVICE because we don't want to auto-enable it
    install -Dm644 ${WORKDIR}/dhcpcd@.service ${D}${systemd_unitdir}/system/dhcpcd@.service
}
