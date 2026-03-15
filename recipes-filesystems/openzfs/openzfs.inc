LICENSE = "CDDL-1.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=0a85e98a2b5fd8ba70af6007ac2022c7 \
                    file://LICENSE;md5=7087caaf1dc8a2856585619f4a787faa"

PV = "2.4.1"
SRCREV = "11fc9d206b854cec1d2e7a004a64d41fcce18a0e"

SRC_URI = " \
    git://github.com/openzfs/zfs;nobranch=1;protocol=https \
    file://zfs-remove-unneeded-components.patch \
"

