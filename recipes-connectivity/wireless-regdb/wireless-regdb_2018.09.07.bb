# the recipe in meta-openembedded/meta-networking conveniently uses ${BP} in SRC_URI
require recipes-connectivity/wireless-regdb/wireless-regdb_2018.05.31.bb

SRC_URI[md5sum] = "ad9f4e9499ce79b371f010ea5b456fff"
SRC_URI[sha256sum] = "a36b8147f1a3e98e1fd44321a4b8d7ad2f03cac98cdf527ccb1693342f08d65a"

# I need to support linux <= 4.14 and >= 4.15, so make the static package recommended instead of conflicted
RCONFLICTS_${PN}_remove = "${PN}-static"
RRECOMMENDS_${PN} += "${PN}-static"
