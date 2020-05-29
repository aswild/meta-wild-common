DESCRIPTION = "Fast and secure kernelspace VPN, userspace tools"
HOMEPAGE = "https://www.wireguard.com"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://../COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "1.0.20200513"
SRCREV = "2d3356a8be5e1ebee9a841a20aa2abdb9c273d19"

SRC_URI = "git://git.zx2c4.com/wireguard-tools;protocol=https"
S = "${WORKDIR}/git/src"

EXTRA_OEMAKE = " \
    V=1 \
    DESTDIR='${D}' PREFIX='${prefix}' BINDIR='${bindir}' LIBDIR='${libdir}' \
    MANDIR='${mandir}' SYSCONFDIR='${sysconfdir}' SYSTEMDUNITDIR='${systemd_unitdir}/system' \
    BASHCOMPDIR='${datadir}/bash-completion/completions' \
    WITH_SYSTEMDUNITS=${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'yes', 'no', d)} \
    WITH_BASHCOMPLETION=yes WITH_WGQUICK=yes \
"

inherit bash-completion systemd pkgconfig

do_configure[noexec] = "1"
do_install() {
    oe_runmake install
}

RDEPENDS_${PN} = "bash"
RRECOMMENDS_${PN} = "kernel-module-wireguard"
FILES_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_unitdir}/system', '', d)}"
