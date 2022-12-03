SUMMARY = "Cat clone with syntax highlighting and git integration"
HOMEPAGE = "https://github.com/sharkdp/bat"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE-MIT;md5=1c0f908bd7bc4ba3a90fc3c0ed1037c9 \
    file://LICENSE-APACHE;md5=86d3f3a95c324c9479bd8986968f4327 \
"

PV = "0.22.1"
SRCREV = "e5d95796141a719e208208182a5d3c2090a009c6"
SRC_URI = "git://github.com/sharkdp/bat"
S = "${WORKDIR}/git"

inherit cargo

do_install() {
    install -Dm755 ${CARGO_RELEASE_DIR}/bat ${D}${bindir}/bat
    local out_dir="$(ls -td "${CARGO_RELEASE_DIR}/build/"bat-*/out | head -n1)"
    install -Dm644 $out_dir/assets/completions/bat.zsh ${D}${datadir}/zsh/site-functions/_bat
    install -Dm644 $out_dir/assets/manual/bat.1 ${D}${mandir}/man1/bat.1
}

PACKAGES += "${PN}-zsh-completion"
FILES:${PN}-zsh-completion = "${datadir}/zsh/site-functions"
RDEPENDS:${PN}-zsh-completion = "zsh"

RRECOMMENDS:${PN} += "${PN}-zsh-completion"
