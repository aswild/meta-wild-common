LICENSE = "CDDL-1.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=0a85e98a2b5fd8ba70af6007ac2022c7 \
                    file://LICENSE;md5=7087caaf1dc8a2856585619f4a787faa"

PV = "2.1.2"
SRCREV = "1e1887a369db0af11fe3be393ff3447d6aa79f4c"

SRC_URI = " \
    git://github.com/openzfs/zfs;nobranch=1 \
    file://zfs-kernel-ld.patch \
    file://zfs-remove-unneeded-components.patch \
"

S = "${WORKDIR}/git"
