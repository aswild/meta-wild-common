# Your Own Doc Language - used for building zsh docs

DESCRIPTION = "Yodl documentation language"
LICENSE = "GPL"

# there's no actual license text in the source...
ERROR_QA_remove = "license-checksum"
WARN_QA_remove = "already-stripped"

SRC_URI = "git://github.com/fbb-git/yodl"
SRCREV = "547a648c0ba13de0f000af2675003261e3c29bd1"
PV = "3.08.01"
S = "${WORKDIR}/git"

DEPENDS = "icmake-native"

inherit native

do_configure[noexec] = "1"

export ICMAKE_CMD = "icmake -qt/tmp/yodl build"

do_compile() {
    cd ${S}/yodl
    ${ICMAKE_CMD} programs
    ${ICMAKE_CMD} macros
}

do_install() {
    cd ${S}/yodl
    ${ICMAKE_CMD} install programs ${D}${STAGING_DIR_NATIVE}
    ${ICMAKE_CMD} install macros ${D}${STAGING_DIR_NATIVE}
}
