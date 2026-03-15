SUMMARY = "tmux - the tmux multipliexer"
LICENSE = "ISC"

DEPENDS = "ncurses libevent bison-native"
LIC_FILES_CHKSUM = "file://COPYING;md5=2b51ca98c264af11cf8d6238df0c8264"

PV = "3.6a"
SRCREV = "cc117b5048f77a4842820f8ebbe3a86e5c077224"
SRC_URI = "git://github.com/tmux/tmux;nobranch=1;protocol=https"

inherit autotools pkgconfig

do_compile:prepend() {
    # used by autotools compat functions from AC_REPLACE_FUNCS, but directory
    # isn't created in Yocto automatically for some reason
    install -d ${B}/compat
}
