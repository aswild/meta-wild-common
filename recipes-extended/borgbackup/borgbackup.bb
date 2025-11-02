SUMMARY = "Borg Backup"
DESCRIPTION = "Deduplicating backup program with compression and authenticated encryption"
HOMEPAGE = "https://www.borgbackup.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dae7cf0a5cee05ad4ae1b7dde3b66673"

PV = "1.4.2"
SRC_URI = "https://github.com/borgbackup/borg/releases/download/${PV}/${BP}.tar.gz"
SRC_URI[sha256sum] = "8923f5e953205d81138d1d7276c8a1c864215e230994d620c397635568ed376f"

SRC_URI += "file://0001-Revert-pyproject.toml-SPDX-expression-for-license-ad.patch"

inherit python_setuptools_build_meta pkgconfig

DEPENDS += "acl lz4 openssl xxhash xz zstd \
            python3-setuptools-scm-native python3-cython-native python3-pkgconfig-native"

RDEPENDS:${PN} = "python3 python3-modules python3-packaging python3-msgpack"
RRECOMMENDS:${PN} = "openssh-ssh"
