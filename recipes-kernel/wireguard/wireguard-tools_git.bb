require wireguard.inc
inherit bash-completion systemd pkgconfig

DEPENDS = "wireguard-module libmnl"

do_compile() {
    oe_runmake V=1 -C tools
}

do_install() {
    oe_runmake DESTDIR="${D}" PREFIX="${prefix}" SYSCONFDIR="${sysconfdir}" \
        SYSTEMDUNITDIR="${systemd_unitdir}" \
        WITH_SYSTEMDUNITS=${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'yes', '', d)} \
        WITH_BASHCOMPLETION=yes \
        WITH_WGQUICK=yes \
        V=1 -C tools install
}

FILES_${PN} = " \
    ${sysconfdir} \
    ${systemd_unitdir} \
    ${bindir} \
"

RDEPENDS_${PN} = "wireguard-module bash"
