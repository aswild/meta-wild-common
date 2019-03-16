SUMMARY = "Borg Backup"
DESCRIPTION = "Deduplicating backup program with compression and authenticated encryption"
HOMEPAGE = "https://www.borgbackup.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=05e264dfb41374bd0f5c4c602e039705"

SRC_URI = "https://github.com/borgbackup/borg/release/download/${PV}/${BP}.tar.gz \
           file://cross-compile.patch"

PV = "1.1.9"
SRC_URI[md5sum] = "0fda2c1f636754d0748569bff67a6836"
SRC_URI[sha256sum] = "7d0ff84e64c4be35c43ae2c047bb521a94f15b278c2fe63b43950c4836b42575"

DEPENDS = "acl lz4 openssl xz python3-setuptools-scm-native python3-cython-native"

# borg has quite a few implicit dependencies, so to be safe we'll just include the
# full standard library (python3-modules) rather than trying to figure out which
# individual modules are needed.
# setuptools is needed at runtime for the launcher scripts that get installed to
# bindir.
# The borg.testsuite module can't be removed because normal borg commands import it.
RDEPENDS_${PN} = "python3 python3-modules python3-setuptools python3-msgpack"
RRECOMMENDS_${PN} = "openssh-ssh"

inherit python3native setuptools3

# setup.py uses these environment variables to find C libraries.
# we patch out the default /usr/... search paths, so other libraries
# won't be found and borg's internal implementation will be used.
export BORG_OPENSSL_PREFIX = "${STAGING_DIR_TARGET}${prefix}"
export BORG_LIBLZ4_PREFIX  = "${STAGING_DIR_TARGET}${prefix}"
