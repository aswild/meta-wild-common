# Mainline Linux 4.9 kernel

LINUX_VERSION = "4.9.67"

KBUILD_DEFCONFIG_x86 = "i386_defconfig"
KBUILD_DEFCONFIG_x86-64 = "x86_64_defconfig"
KCONFIG_MODE = "alldefconfig"

require linux-mainline.inc
