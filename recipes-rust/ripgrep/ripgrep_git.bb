SUMMARY = "ripgrep recursively searches directories for a regex pattern while respecting your gitignore"
HOMEPAGE = "https://github.com/aswild/ripgrep"
LICENSE = "MIT | Unlicense"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=034e2d49ef70c35b64be514bef39415a \
    file://LICENSE-MIT;md5=8d0d0aa488af0ab9aafa3b85a7fc8e12 \
    file://UNLICENSE;md5=7246f848faa4e9c9fc0ea91122d6e680 \
"

PV = "14.1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/aswild/ripgrep;branch=master;protocol=https"
S = "${WORKDIR}/git"

BBCLASSEXTEND += "native"
DEPENDS:append:class-target = " ${BPN}-native (= ${PV})"

inherit cargo-wild

RG_EXE = "rg"
RG_EXE:class-native = "${CARGO_RELEASE_DIR}/rg"

do_install() {
    install -Dm755 ${CARGO_RELEASE_DIR}/rg ${D}${bindir}/rg

    install -d ${D}${mandir}/man1
    ${RG_EXE} --no-config --generate man >${D}${mandir}/man1/rg.1
    install -d ${D}${datadir}/bash-completion/completions
    ${RG_EXE} --no-config --generate complete-bash >${D}${datadir}/bash-completion/completions/rg
    install -d ${D}${datadir}/zsh/site-functions
    ${RG_EXE} --no-config --generate complete-zsh >${D}${datadir}/zsh/site-functions/_rg
}

inherit bash-completion zsh-completion
RRECOMMENDS:${PN} += "${PN}-bash-completion ${PN}-zsh-completion"
