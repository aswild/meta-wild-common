DESCRIPTION = "python tool to read/write EDIDs"
LICENSE = "GPLv3"

# no proper license file...
LIC_FILES_CHKSUM = "file://.gitignore;md5=2af6d4cd4cf108d6c05ecea92050610e"

SRC_URI = "git://github.com/bulletmark/edid-rw.git;branch=master;protocol=https"
SRCREV = "${AUTOREV}"
PV = "1.0-${SRCPV}"

RDEPENDS:${PN} = "python-argparse python-smbus"

S = "${WORKDIR}/git"

do_install() {
    install -D -m 0755 edid-rw ${D}${bindir}/edid-rw
}

inherit allarch
do_configure[noexec] = "1"
do_compile[noexec] = "1"
