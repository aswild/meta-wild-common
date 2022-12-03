SUMMARY = "A directory listing program displaying a depth indented list of files"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=393a5ca445f6965873eca0259a17f833"

PV = "2.0.4"
SRC_URI = "http://mama.indstate.edu/users/ice/${BPN}/src/${BP}.tgz"
SRC_URI[sha256sum] = "b0ea92197849579a3f09a50dbefc3d4708caf555d304a830e16e20b73b4ffa74"

SRC_URI += "file://cross-compile.patch"

do_configure[noexec] = "1"
do_install() {
    oe_runmake DESTDIR=${D} install
}
