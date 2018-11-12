require wireguard.inc
inherit bash-completion systemd pkgconfig

DEPENDS = "libmnl"
RDEPENDS_${PN} = "wireguard-module bash"

S = "${WORKDIR}/git/src/tools"
EXTRA_OEMAKE += " \
    V=1 \
    DESTDIR='${D}' PREFIX='${prefix}' BINDIR='${bindir}' LIBDIR='${libdir}' \
    SYSCONFDIR='${sysconfdir}' SYSTEMDUNITDIR='${systemd_unitdir}/system' \
    WITH_SYSTEMDUNITS=${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'yes', 'no', d)} \
    WITH_BASHCOMPLETION=yes WITH_WGQUICK=yes \
"

do_configure[noexec] = "1"
do_install() {
    oe_runmake install
}

FILES_${PN} += "${systemd_unitdir}/system"
