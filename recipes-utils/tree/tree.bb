SUMMARY = "A directory listing program displaying a depth indented list of files"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=393a5ca445f6965873eca0259a17f833"

PV = "1.8.0"
SRC_URI = "http://mama.indstate.edu/users/ice/${PN}/src/${BP}.tgz"
SRC_URI[md5sum] = "715191c7f369be377fc7cc8ce0ccd835"
SRC_URI[sha256sum] = "715d5d4b434321ce74706d0dd067505bb60c5ea83b5f0b3655dae40aa6f9b7c2"

SRC_URI += "file://cross-compile.patch"

do_configure[noexec] = "1"
do_install() {
    oe_runmake DESTDIR=${D} install
}
