require openzfs.inc

inherit autotools python3native pkgconfig
DEPENDS += "zlib util-linux libtirpc openssl"

EXTRA_OECONF = " \
    --with-config=user \
    --with-mounthelperdir=${sbindir} \
    --with-udevdir=${nonarch_base_libdir}/udev \
    --disable-pam \
"

PACKAGECONFIG ?= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"

FILES:${PN} += "${datadir}/zfs ${systemd_unitdir}"
RRECOMMENDS:${PN} = "kernel-module-zfs"

do_install:append() {
    rm -rf ${D}${libdir}/modules-load.d
}
