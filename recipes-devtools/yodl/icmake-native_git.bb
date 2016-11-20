# icmake, used to build yodl, used to build zsh docs

DESCRIPTION = "Crazy-ass custom make system"
LICENSE = "GPL"

# there's no actual license text in the source...
ERROR_QA_remove = "license-checksum"
WARN_QA_remove = "already-stripped"

SRC_URI = "git://github.com/fbb-git/icmake"
SRCREV = "600be0d7305d9ac8bb1541f8dccd5d02a9ae17f8"
PV = "9.01.00"
S = "${WORKDIR}/git"

inherit native

FILES_${PN} = " \
    /usr/* \
    /etc/* \
"

do_configure[noexec] = "1"

do_compile(){
    cd ${S}/icmake
    ./icm_prepare ${D}${STAGING_DIR_NATIVE}
    ./icm_bootstrap x
}

do_install() {
    cd ${S}/icmake
    ./icm_install all /
}
