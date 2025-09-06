SUMMARY = "Borg Backup"
DESCRIPTION = "Deduplicating backup program with compression and authenticated encryption"
HOMEPAGE = "https://www.borgbackup.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dae7cf0a5cee05ad4ae1b7dde3b66673"

PV = "1.4.1"
SRC_URI = "https://github.com/borgbackup/borg/releases/download/${PV}/${BP}.tar.gz"
SRC_URI[sha256sum] = "b8fbf8f1c19d900b6b32a5a1dc131c5d8665a7c7eea409e9095209100b903839"

DEPENDS = "acl lz4 openssl xxhash xz zstd \
           python3-setuptools-scm-native python3-cython-native python3-pkgconfig-native"

RDEPENDS:${PN} = "python3 python3-modules python3-packaging python3-msgpack"
RRECOMMENDS:${PN} = "openssh-ssh"

inherit python3native setuptools3 pkgconfig
