SUMMARY = "Cat clone with syntax highlighting and git integration"
HOMEPAGE = "https://github.com/sharkdp/bat"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE-MIT;md5=c46eaa1315aaa0c727a29b157ad9170a \
    file://LICENSE-APACHE;md5=86d3f3a95c324c9479bd8986968f4327 \
"

PV = "0.25.0"
SRCREV = "25f4f96ea3afb6fe44552f3b38ed8b1540ffa1b3"
SRC_URI = "git://github.com/sharkdp/bat;branch=master;protocol=https"
S = "${WORKDIR}/git"

inherit cargo-wild

no_strip() {
    sed -i '/^strip = true/d' ${S}/Cargo.toml
}
do_patch[postfuncs] += "no_strip"

do_install() {
    install -Dm755 ${CARGO_RELEASE_DIR}/bat ${D}${bindir}/bat
    local out_dir="$(ls -td "${CARGO_RELEASE_DIR}/build/"bat-*/out | head -n1)"
    install -Dm644 $out_dir/assets/completions/bat.bash ${D}${datadir}/bash-completion/completions/bat
    install -Dm644 $out_dir/assets/completions/bat.zsh ${D}${datadir}/zsh/site-functions/_bat
    install -Dm644 $out_dir/assets/manual/bat.1 ${D}${mandir}/man1/bat.1
}

inherit bash-completion zsh-completion
RRECOMMENDS:${PN} += "${PN}-bash-completion ${PN}-zsh-completion"

# Don't warn about absolute paths in the binary
WARN_QA:remove = "buildpaths"
