SUMMARY = "tmux - the tmux multipliexer"
LICENSE = "ISC"

DEPENDS = "ncurses libevent"
LIC_FILES_CHKSUM = "file://COPYING;md5=f7d9aab84ec6567139a4755c48d147fb"

SRC_URI = "git://github.com/tmux/tmux"
SRCREV = "52869ed182482c26163799a7215139f4d81b6fca"
PV = "2.3"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

FILES_${PN} = " \
    ${bindir}/tmux \
    ${mandir}/man1/tmux.1 \
"

inherit autotools pkgconfig

### OLD STUFF BELOW, looks like it's not really needed ###

# --with-libtool-sysroot is broken in tmux, so prepend staging dir
# to all paths manually
#python __anonymous() {
#    opts = d.getVar("CONFIGUREOPTS", True).split()
#    prefix = d.getVar("D", True)
#    if prefix[-1] == "/":
#        prefix = prefix[:-1]
#
#    for i in range(len(opts)):
#        opt = opts[i].split("=")
#        if opt[0][-3:] == "dir":
#            newval = prefix + opt[1]
#            opts[i] = "%s=%s"%(opt[0], newval)
#
#    d.setVar("CONFIGUREOPTS", " ".join(opts))
#}

#do_configure() {
#    cd ${S}
#    libtoolize --automake
#    ./autogen.sh || die "autogen.sh failed"
#
#    set -x
#    oe_runconf
#}
#
#do_compile() {
#    cd ${S}
#    oe_runmake
#}
#
#do_install() {
#    cd ${S}
#    oe_runmake install
#}
