DESCRIPTION = "Fast and secure kernelspace VPN, kernel module"
HOMEPAGE = "https://www.wireguard.com"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "1.0.20210219"
SRCREV = "8a88669a47a90908e766895f6243ffa6c999293c"

SRC_URI = "git://git.zx2c4.com/wireguard-linux-compat;protocol=https;branch=master"
S = "${WORKDIR}/git/src"

DEPENDS = "bc-native"

EXTRA_OEMAKE = "KERNELDIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "module"
MODULES_INSTALL_TARGET = "module-install"

inherit module

RPROVIDES:${PN} = "kernel-module-wireguard"
RRECOMMENDS:${PN} = "wireguard-tools"
