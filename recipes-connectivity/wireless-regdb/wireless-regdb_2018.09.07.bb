# the recipe in meta-openembedded/meta-networking conveniently uses ${BP} in SRC_URI
require recipes-connectivity/wireless-regdb/wireless-regdb_2016.06.10.bb

SRC_URI[md5sum] = "ad9f4e9499ce79b371f010ea5b456fff"
SRC_URI[sha256sum] = "a36b8147f1a3e98e1fd44321a4b8d7ad2f03cac98cdf527ccb1693342f08d65a"

do_install_append() {
    install -d ${D}${nonarch_base_libdir}/firmware
    install -m644 regulatory.db regulatory.db.p7s ${D}${nonarch_base_libdir}/firmware
}
FILES_${PN} += "${nonarch_base_libdir}/firmware"
