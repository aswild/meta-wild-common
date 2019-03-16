SUMMARY = "Borg Backup"
DESCRIPTION = "Deduplicating backup program with compression and authenticated encryption"
HOMEPAGE = "https://www.borgbackup.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=05e264dfb41374bd0f5c4c602e039705"

SRC_URI = "https://github.com/borgbackup/borg/releases/download/${PV}/${BP}.tar.gz \
           file://cross-compile.patch"

SRC_URI[md5sum] = "0fda2c1f636754d0748569bff67a6836"
SRC_URI[sha256sum] = "7d0ff84e64c4be35c43ae2c047bb521a94f15b278c2fe63b43950c4836b42575"

DEPENDS = "acl lz4 openssl xz \
           python3-setuptools-scm-native python3-cython-native"

# setuptools needed for pkg_resources in launcher script, could probably
# be replaced with a custom simpler launcher instead of what's generated
# by setuptools.
RDEPENDS_${PN} += "python3-fcntl python3-misc python3-mmap python3-json \
                   python3-msgpack python3-setuptools"
RRECOMMENDS_${PN} = "openssh-ssh"

inherit python3native setuptools3

export BORG_OPENSSL_PREFIX = "${STAGING_DIR_TARGET}${prefix}"
export BORG_LIBLZ4_PREFIX  = "${STAGING_DIR_TARGET}${prefix}"
