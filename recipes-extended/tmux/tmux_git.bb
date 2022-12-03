SUMMARY = "tmux - the tmux multipliexer"
LICENSE = "ISC"

DEPENDS = "ncurses libevent bison-native"
LIC_FILES_CHKSUM = "file://COPYING;md5=2b51ca98c264af11cf8d6238df0c8264"

PV = "3.3a"
SRCREV = "0b355ae8114511e1ff6359272b164f1cdf718e80"
SRC_URI = "git://github.com/tmux/tmux"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_compile:prepend() {
    # used by autotools compat functions from AC_REPLACE_FUNCS, but directory
    # isn't created in Yocto automatically for some reason
    install -d ${B}/compat
}
