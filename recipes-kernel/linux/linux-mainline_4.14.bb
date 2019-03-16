# Mainline Linux 4.14 kernel

LINUX_VERSION = "4.14.106"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

KBUILD_DEFCONFIG_x86 = "i386_defconfig"
KBUILD_DEFCONFIG_x86-64 = "x86_64_defconfig"
KCONFIG_MODE = "alldefconfig"

require linux-mainline.inc
