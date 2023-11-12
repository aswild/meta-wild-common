# Recipe for Allen Wild's linuxfiles

DESCRIPTION = "Shell dotfiles"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7be1bf717db056fe85928318a0e68571"

inherit allarch

# gitsm:// fetcher clones submodules
SRC_URI = "gitsm://github.com/aswild/linuxfiles;branch=master;protocol=https"
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"

# where on the rootfs to install to
LINUXFILES_LOC ?= "${ROOT_HOME}/linuxfiles"

# force including all git data too
FILES:${PN} = "${ROOT_HOME}"
ERROR_QA:remove = "file-rdeps"

do_configure() {
    set -x
    # get the correct branch
    git checkout master

    # delete refs/pull/xxx refs that might be fetched from github
    git for-each-ref --format='delete %(refname)' 'refs/pull/**' | git update-ref --stdin
    git gc --aggressive --prune=now

    # same thing for submodules
    git submodule foreach --recursive \
        "git for-each-ref --format='delete %(refname)' 'refs/pull/**' | git update-ref --stdin && git gc --aggressive --prune=now"

    find .git -path '*/objects/info/alternates' -exec rm -v {} +
}

do_install() {
    set -x
    installdir="${D}${LINUXFILES_LOC}"

    install -d $(dirname $installdir)
    cp -rT ${S} $installdir

    cd $installdir
    make DESTDIR="${D}${ROOT_HOME}" SRCDIR="${LINUXFILES_LOC}" links bashrc-append

    # create .bash_profile to source .bashrc
    echo '[[ -f $HOME/.bashrc ]] && . $HOME/.bashrc' >${D}${ROOT_HOME}/.bash_profile
}

do_compile[noexec] = "1"
