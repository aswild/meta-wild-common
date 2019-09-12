DESCRIPTION = "Fast and secure kernelspace VPN"
HOMEPAGE = "https://www.wireguard.com"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

TAG = "0.0.20190905"
PV = "${TAG}+git${SRCPV}"
SRC_URI = "git://github.com/WireGuard/WireGuard;tag=${TAG}"
S = "${WORKDIR}/git/src"
