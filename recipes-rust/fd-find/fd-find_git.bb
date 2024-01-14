SUMMARY = "fd is a simple, fast and user-friendly alternative to find (prebuilt binary)"
HOMEPAGE = "https://github.com/aswild/fd"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE-MIT;md5=96713c739748a44f86272791c89ce344 \
    file://LICENSE-APACHE;md5=35be2e0efac68ea4480eac769e7618a2 \
"

PV = "9.0.0+git${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/aswild/fd;branch=master;protocol=https"
S = "${WORKDIR}/git"

BBCLASSEXTEND += "native"
DEPENDS:append:class-target = " ${BPN}-native (= ${PV})"

inherit cargo-wild

FD_EXE = "fd"
FD_EXE:class-native = "${CARGO_RELEASE_DIR}/fd"

do_install() {
    install -Dm755 ${CARGO_RELEASE_DIR}/fd ${D}${bindir}/fd

    install -d ${D}${datadir}/bash-completion/completions
    ${FD_EXE} --gen-completions bash >${D}${datadir}/bash-completion/completions/fd

    install -Dm644 ${S}/contrib/completion/_fd ${D}${datadir}/zsh/site-functions/_fd
    install -Dm644 ${S}/doc/fd.1 ${D}${mandir}/man1/fd.1
}

inherit bash-completion zsh-completion
RRECOMMENDS:${PN} += "${PN}-bash-completion ${PN}-zsh-completion"
