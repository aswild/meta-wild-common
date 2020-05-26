DESCRIPTION = "Packages for dev images"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = "\
    ${PN}-python3 \
    packagegroup-core-buildessential \
    gdb \
    kernel-devsrc \
    man \
    strace \
"
