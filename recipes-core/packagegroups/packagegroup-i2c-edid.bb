DESCRIPTION = "Tools to read EDID info over I2C"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    edid-rw \
    i2c-tools \
    python-smbus \
"
