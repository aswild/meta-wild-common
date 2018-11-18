SUMMARY = "tmux - the tmux multipliexer"
LICENSE = "ISC"

DEPENDS = "ncurses libevent"
LIC_FILES_CHKSUM = "file://COPYING;md5=f7d9aab84ec6567139a4755c48d147fb"

PV = "2.8"
SRC_URI = "git://github.com/tmux/tmux;tag=${PV}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_compile_prepend() {
    # used by autotools compat functions from AC_REPLACE_FUNCS, but directory
    # isn't created in Yocto automatically for some reason
    install -d ${B}/compat
}
