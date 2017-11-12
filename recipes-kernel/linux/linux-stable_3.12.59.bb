require linux-stable.inc

LINUX_VERSION = "3.12.59"

KBUILD_DEFCONFIG_x86 = "i386_defconfig"
KBUILD_DEFCONFIG_x86-64 = "x86_64_defconfig"

KCONFIG_MODE = "--alldefconfig"

SRC_URI += " \
    file://tweaks.cfg \
    file://tweaks-qemu.cfg \
    file://disable-x86-pkg-temp-thermal.cfg \
"
