require wireguard.inc
inherit module

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "module"
MODULES_INSTALL_TARGET = "module-install"

RPROVIDES_${PN} = "kernel-module-wireguard"
RRECOMMENDS_${PN} = "wireguard-tools"
