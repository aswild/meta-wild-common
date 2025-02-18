FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI += " \
    file://0001-Revert-Mark-authorization-completed-on-driver-indica.patch \
    file://0002-wpa_supplicant@.service.patch \
"

RRECOMMENDS:${PN} += "wpa-oneoff"
