SUMMARY = "tmux - the tmux multipliexer"
LICENSE = "ISC"

DEPENDS = "ncurses libevent"
LIC_FILES_CHKSUM = "file://COPYING;md5=f7d9aab84ec6567139a4755c48d147fb"

SRC_URI = "git://github.com/tmux/tmux"
SRCREV = "caa90735cf0b20a7110d25596140f553e2d05bff"
PV = "2.5+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

FILES_${PN} = " \
    ${bindir}/tmux \
    ${mandir}/man1/tmux.1 \
"

inherit autotools-brokensep pkgconfig
