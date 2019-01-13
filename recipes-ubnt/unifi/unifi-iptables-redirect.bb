SUMMARY = "iptables redirect port 80/443 to 8080/8443"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://iptables-redirect.sh;endline=3;md5=45241351314756142d5834ab9cd90119"

PV = "1"

SRC_URI = "file://${PN}.service \
           file://iptables-redirect.sh"
S = "${WORKDIR}"

RDEPENDS_${PN} = "iptables"
RRECOMMENDS_${PN} = "kernel-module-iptable-nat"

inherit systemd
SYSTEMD_SERVICE_${PN} = "${PN}.service"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -Dm755 iptables-redirect.sh ${D}${libdir}/unifi/bin/iptables-redirect.sh
    install -Dm644 ${PN}.service ${D}${systemd_unitdir}/system/${PN}.service
}

FILES_${PN} = "${libdir}/unifi/* ${systemd_unitdir}/system/*"
