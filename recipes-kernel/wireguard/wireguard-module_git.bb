DESCRIPTION = "Fast and secure kernelspace VPN, kernel module"
HOMEPAGE = "https://www.wireguard.com"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "1.0.20200729"
SRCREV = "9bd0abe2cd51beaa607d60805fec18cbeff4738a"

SRC_URI = "git://git.zx2c4.com/wireguard-linux-compat;protocol=https"
S = "${WORKDIR}/git/src"

DEPENDS = "bc-native"

EXTRA_OEMAKE = "KERNELDIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "module"
MODULES_INSTALL_TARGET = "module-install"

inherit module

RPROVIDES_${PN} = "kernel-module-wireguard"
RRECOMMENDS_${PN} = "wireguard-tools"
