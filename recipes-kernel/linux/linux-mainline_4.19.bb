# Mainline Linux 4.19 kernel

LINUX_VERSION = "4.19.157"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

KBUILD_DEFCONFIG:x86 = "i386_defconfig"
KBUILD_DEFCONFIG:x86-64 = "x86_64_defconfig"
KCONFIG_MODE = "alldefconfig"

require linux-mainline.inc
