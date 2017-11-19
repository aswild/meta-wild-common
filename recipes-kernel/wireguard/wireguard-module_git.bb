require wireguard.inc

inherit module

EXTRA_OEMAKE += "V=1 KERNELDIR=${STAGING_KERNEL_DIR}"
MAKE_TARGETS = "module"
MODULES_INSTALL_TARGET = "module-install"
