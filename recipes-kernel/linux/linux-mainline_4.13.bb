# Mainline Linux 4.13 kernel

LINUX_VERSION = "4.13.15"

KBUILD_DEFCONFIG_x86 = "i386_defconfig"
KBUILD_DEFCONFIG_x86-64 = "x86_64_defconfig"
KCONFIG_MODE = "alldefconfig"

require linux-mainline.inc
