SUMMARY = "fd is a simple, fast and user-friendly alternative to find (prebuilt binary)"
HOMEPAGE = "https://github.com/aswild/fd"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE-MIT;md5=d3fa9b754758009ec4c5d192856a528c \
    file://LICENSE-APACHE;md5=35be2e0efac68ea4480eac769e7618a2 \
"

PV = "8.2.1+git${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/aswild/fd"
S = "${WORKDIR}/git"

inherit cargo

do_install() {
    install -Dm755 ${CARGO_RELEASE_DIR}/fd ${D}${bindir}/fd
    local out_dir="$(ls -td "${CARGO_RELEASE_DIR}/build/"fd-find-*/out | head -n1)"
    install -Dm644 $out_dir/fd.bash ${D}${datadir}/bash-completion/completions/fd
    install -Dm644 ${S}/contrib/completion/_fd ${D}${datadir}/zsh/site-functions/_fd
    install -Dm644 ${S}/doc/fd.1 ${D}${mandir}/man1/fd.1
}

inherit bash-completion
PACKAGES += "${PN}-zsh-completion"
FILES:${PN}-zsh-completion = "${datadir}/zsh/site-functions"
RDEPENDS:${PN}-zsh-completion = "zsh"

RRECOMMENDS:${PN} += "${PN}-bash-completion ${PN}-zsh-completion"
