SUMMARY = "ripgrep recursively searches directories for a regex pattern while respecting your gitignore"
HOMEPAGE = "https://github.com/aswild/ripgrep"
LICENSE = "MIT | Unlicense"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=034e2d49ef70c35b64be514bef39415a \
    file://LICENSE-MIT;md5=8d0d0aa488af0ab9aafa3b85a7fc8e12 \
    file://UNLICENSE;md5=7246f848faa4e9c9fc0ea91122d6e680 \
"

PV = "13.0.0+git${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/aswild/ripgrep"
S = "${WORKDIR}/git"

inherit cargo
DEPENDS += "asciidoc-native"

do_install() {
    install -Dm755 ${CARGO_RELEASE_DIR}/rg ${D}${bindir}/rg
    local out_dir="$(${S}/ci/cargo-out-dir ${CARGO_RELEASE_DIR})"
    install -Dm644 $out_dir/rg.1 ${D}${mandir}/man1/rg.1
    install -Dm644 $out_dir/rg.bash ${D}${datadir}/bash-completion/completions/rg
    install -Dm644 ${S}/complete/_rg ${D}${datadir}/zsh/site-functions/_rg
}

inherit bash-completion
PACKAGES += "${PN}-zsh-completion"
FILES_${PN}-zsh-completion = "${datadir}/zsh/site-functions"
RDEPENDS_${PN}-zsh-completion = "zsh"

RRECOMMENDS_${PN} += "${PN}-bash-completion ${PN}-zsh-completion"
