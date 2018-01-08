DESCRIPTION = "script to run wpa_supplicant with ssid/passphrase via the command line"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://wpa_oneoff.sh;endline=21;md5=8241bd9423fb5ab2c43128e588393d3b"

RDEPENDS_${PN} = "bash wpa-supplicant wpa-supplicant-passphrase"
PACKAGE_ARCH = "all"

PV = "1.0"
SRC_URI = "file://wpa_oneoff.sh"
S = "${WORKDIR}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install() {
    install -Dm755 wpa_oneoff.sh ${D}${bindir}/wpa_oneoff
}
