DESCRIPTION = "Fast and secure kernelspace VPN, kernel module"
HOMEPAGE = "https://www.wireguard.com"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "0.0.20200215"
SRCREV = "d75295ad2a4186ea2f258f606de9abd73bf25fd5"

SRC_URI = "git://git.zx2c4.com/wireguard-linux-compat;protocol=https"
S = "${WORKDIR}/git/src"

DEPENDS = "bc-native"

EXTRA_OEMAKE = "KERNELDIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "module"
MODULES_INSTALL_TARGET = "module-install"

inherit module

RPROVIDES_${PN} = "kernel-module-wireguard"
RRECOMMENDS_${PN} = "wireguard-tools"
