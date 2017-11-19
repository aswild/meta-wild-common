DESCRIPTION = "Fast and secure kernelspace VPN"
HOMEPAGE = "https://www.wireguard.com"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

TAG = "0.0.20171111"
PV = "${TAG}+git${SRCPV}"
SRC_URI = "git://git.zx2c4.com/WireGuard;tag=${TAG}"
S = "${WORKDIR}/git/src"

inherit module
MAKE_TARGETS = "module"
MODULES_INSTALL_TARGET = "module-install"

EXTRA_OEMAKE += "V=1 KERNELDIR=${STAGING_KERNEL_DIR}"

do_compile() {
    module_do_compile
    oe_runmake tools
}

do_install() {
    module_do_install
    oe_runmake DESTDIR=${D} -C tools install
}

PACKAGES += "${PN}-tools"
FILES_${PN}-tools = " \
    ${bindir}/wg ${bindir}/wg-quick \
    ${datadir}/bash-completion/completions/* \
    ${systemd_unitdir}/systemd/wg-quick@.service \
    ${sysconfdir}/wireguard \
"
