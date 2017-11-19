require wireguard.inc
inherit module

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "module"
MODULES_INSTALL_TARGET = "module-install"
