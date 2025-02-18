LICENSE = "CDDL-1.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=0a85e98a2b5fd8ba70af6007ac2022c7 \
                    file://LICENSE;md5=7087caaf1dc8a2856585619f4a787faa"

PV = "2.3.0"
SRCREV = "bc06d8164b8b6673ca2dc9b36090f91a5d6348b3"

SRC_URI = " \
    git://github.com/openzfs/zfs;nobranch=1;protocol=https \
    file://zfs-remove-unneeded-components.patch \
"

S = "${WORKDIR}/git"
