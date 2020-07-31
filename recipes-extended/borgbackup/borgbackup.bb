SUMMARY = "Borg Backup"
DESCRIPTION = "Deduplicating backup program with compression and authenticated encryption"
HOMEPAGE = "https://www.borgbackup.org"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=05e264dfb41374bd0f5c4c602e039705"

SRC_URI = "https://github.com/borgbackup/borg/releases/download/${PV}/${BP}.tar.gz \
           file://cross-compile.patch"

PV = "1.1.13"
SRC_URI[md5sum] = "a82d2bdf61bfaeebb90ac8ab1347657a"
SRC_URI[sha256sum] = "164a8666a61071ce2fa6c60627c7646f12e3a8e74cd38f046be72f5ea91b3821"

DEPENDS = "acl lz4 openssl xz zstd python3-setuptools-scm-native python3-cython-native"

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
