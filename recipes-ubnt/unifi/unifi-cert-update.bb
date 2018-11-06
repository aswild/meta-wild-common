SUMMARY = "Script to import LetsEncrypt certs into the unifi controller"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://unifi-cert-update.sh;endline=3;md5=55c3ccd75784371e468b72fecc3db25a"

PV = "2"

SRC_URI = "file://unifi-cert-update.sh"
S = "${WORKDIR}"

RDEPENDS_${PN} = "bash unifi"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -Dm755 unifi-cert-update.sh ${D}${bindir}/unifi-cert-update
}
