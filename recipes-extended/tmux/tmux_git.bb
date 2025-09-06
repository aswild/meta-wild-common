SUMMARY = "tmux - the tmux multipliexer"
LICENSE = "ISC"

DEPENDS = "ncurses libevent bison-native"
LIC_FILES_CHKSUM = "file://COPYING;md5=2b51ca98c264af11cf8d6238df0c8264"

PV = "3.5a"
SRCREV = "201478321a4623df80bb864884e8a8f536d3d53f"
SRC_URI = "git://github.com/tmux/tmux;nobranch=1;protocol=https"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_compile:prepend() {
    # used by autotools compat functions from AC_REPLACE_FUNCS, but directory
    # isn't created in Yocto automatically for some reason
    install -d ${B}/compat
}
