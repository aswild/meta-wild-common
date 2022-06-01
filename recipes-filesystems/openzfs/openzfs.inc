LICENSE = "CDDL-1.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=0a85e98a2b5fd8ba70af6007ac2022c7 \
                    file://LICENSE;md5=7087caaf1dc8a2856585619f4a787faa"

PV = "2.1.4"
SRCREV = "ad6bd2a2c7f666729a9320a5f540ac9b2ed11d16"

SRC_URI = " \
    git://github.com/openzfs/zfs;nobranch=1 \
    file://zfs-remove-unneeded-components.patch \
"

S = "${WORKDIR}/git"
