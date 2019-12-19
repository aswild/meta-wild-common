SUMMARY = "tmux - the tmux multipliexer"
LICENSE = "ISC"

DEPENDS = "ncurses libevent bison-native"
LIC_FILES_CHKSUM = "file://COPYING;md5=2b51ca98c264af11cf8d6238df0c8264"

PV = "3.0a"
SRCREV = "0b86467c3ff0ed1893902faf0b3e3d6a260dc73a"
SRC_URI = "git://github.com/tmux/tmux"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_compile_prepend() {
    # used by autotools compat functions from AC_REPLACE_FUNCS, but directory
    # isn't created in Yocto automatically for some reason
    install -d ${B}/compat
}
