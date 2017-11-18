# Mainline linux kernel

require recipes-kernel/linux/linux-yocto.inc

DESCRIPTION = "Linux Kernel (mainline)"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRCPV := "${@d.getVar('SRCREV')[:10]}"
PV = "${LINUX_VERSION}+git${SRCPV}"
LINUX_VERSION_EXTENSION ?= ""

KCONFIG_MODE ?= "allnoconfig"
KCONFIG_CONFIG_COMMAND ?= "nconfig"

# have to manually expand ${PN} because immediate expansion when parsing the inc file will yield 'defaultpkgname'
# LINUX_VERSION must be set before including this file
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-mainline-${@'.'.join(d.getVar('LINUX_VERSION').split('.')[:2])}:"

# add the available features from this layer so they can be enabled with KERNEL_FEATURES
# kernel-yocto.bbclass needs destsuffix to be set (thinking that the features are in a separate git repo)
# but the file:// fetcher doesn't care about destsuffix, so the names below have to match
# and type must be kmeta
SRC_URI_append = " file://wild-common-kmeta;type=kmeta;destsuffix=wild-common-kmeta"
