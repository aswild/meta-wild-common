# /etc/ethertypes is installed by netbase, use that version rather than a conflicting iptables one

do_install:append() {
    rm ${D}${sysconfdir}/ethertypes
}

RDEPENDS_${PN} = "netbase"
