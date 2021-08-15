SUMMARY = "Cat clone with syntax highlighting and git integration"
HOMEPAGE = "https://github.com/sharkdp/bat"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE-MIT;md5=1c0f908bd7bc4ba3a90fc3c0ed1037c9 \
    file://LICENSE-APACHE;md5=86d3f3a95c324c9479bd8986968f4327 \
"

PV = "0.18.2"
SRCREV = "589df6792fe406f91b00db5e24d35393c6527e6b"
SRC_URI = "git://github.com/sharkdp/bat \
           file://0001-Update-git2-dependency-to-fix-incompatibility-with-R.patch"
S = "${WORKDIR}/git"

inherit cargo

do_install() {
    install -Dm755 ${CARGO_RELEASE_DIR}/bat ${D}${bindir}/bat
    local out_dir="$(ls -td "${CARGO_RELEASE_DIR}/build/"bat-*/out | head -n1)"
    install -Dm644 $out_dir/assets/completions/bat.zsh ${D}${datadir}/zsh/site-functions/_bat
    install -Dm644 $out_dir/assets/manual/bat.1 ${D}${mandir}/man1/bat.1
}

PACKAGES += "${PN}-zsh-completion"
FILES_${PN}-zsh-completion = "${datadir}/zsh/site-functions"
RDEPENDS_${PN}-zsh-completion = "zsh"

RRECOMMENDS_${PN} += "${PN}-zsh-completion"
