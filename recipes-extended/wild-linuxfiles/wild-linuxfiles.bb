# Recipe for Allen Wild's linuxfiles

DESCRIPTION = "Shell dotfiles"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=873a8e3c61b23b718b4594fa0a3e7449"

PACKAGE_ARCH = "all"

# gitsm:// fetcher clones submodules
SRC_URI = "gitsm://github.com/aswild/linuxfiles;branch=master"
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"

# where on the rootfs to install to
LINUXFILES_LOC ?= "${ROOT_HOME}/linuxfiles"

# force including all git data too
FILES_${PN} = "${ROOT_HOME}"
ERROR_QA_remove = "file-rdeps"

do_install() {
    set -x
    installdir="${D}${LINUXFILES_LOC}"

    install -d $(dirname $installdir)
    cp -rvT ${S} $installdir

    cd $installdir
    # leave detached HEAD, and make sure we get all the objects in the installdir
    git checkout master
    git repack -ad
    rm -f .git/objects/info/alternates
    make DESTDIR="${D}${ROOT_HOME}" SRCDIR="${LINUXFILES_LOC}" install

    # create .bash_profile to source .bashrc
    echo '[[ -f $HOME/.bashrc ]] && . $HOME/.bashrc' >${D}${ROOT_HOME}/.bash_profile
}

do_configure[noexec] = "1"
do_compile[noexec] = "1"
