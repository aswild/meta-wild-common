DESCRIPTION = "Packages for dev images"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = "\
    packagegroup-core-buildessential \
    packagegroup-wild-python3 \
    gdb \
    kernel-devsrc \
    man \
    strace \
"
