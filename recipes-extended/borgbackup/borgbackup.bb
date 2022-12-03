SUMMARY = "Borg Backup"
DESCRIPTION = "Deduplicating backup program with compression and authenticated encryption"
HOMEPAGE = "https://www.borgbackup.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4b81f01bf74e454e57a4097a5ab66874"

PV = "1.2.2"
SRC_URI = "https://github.com/borgbackup/borg/releases/download/${PV}/${BP}.tar.gz"
SRC_URI[sha256sum] = "d730687443f1beb602b1d72bae36318f6f9654818fcdc50458540ec579e57260"

DEPENDS = "acl lz4 openssl xxhash xz zstd python3-setuptools-scm-native python3-cython-native"

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
export BORG_LIBZSTD_PREFIX  = "${STAGING_DIR_TARGET}${prefix}"
export BORG_LIBXXHASH_PREFIX = "${STAGING_DIR_TARGET}${prefix}"
