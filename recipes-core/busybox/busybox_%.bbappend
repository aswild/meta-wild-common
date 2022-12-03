# disable syslogd/klogd by default
BUSYBOX_NO_SYSLOG ?= "1"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://extra-features.cfg \
            ${@bb.utils.contains('BUSYBOX_NO_SYSLOG', '1', 'file://no-syslog.cfg', '', d)}"

SYSTEMD_PACKAGES:remove = "${@bb.utils.contains('BUSYBOX_NO_SYSLOG', '1', '${PN}-syslog', '', d)}"
