SUMMARY = "fd is a simple, fast and user-friendly alternative to find (prebuilt binary)"
HOMEPAGE = "https://github.com/aswild/fd"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://../LICENSE-MIT;md5=e6a265296c45f2670d948a7aa327eb80 \
    file://../LICENSE-APACHE;md5=35be2e0efac68ea4480eac769e7618a2 \
"

PROVIDES = "fd-find"
RPROVIDES_${PN} = "fd-find"

PV = "8.1.1-47-g2dbbd75"
SRC_URI[aarch64.sha256sum] = "47414f2d0c02ec3a009538c3faba5447a78cd56217755c5331156f1d3c5374f5"
SRC_URI[x86_64.sha256sum] = "bdcab0762da1188fe297c476dd9b792357bf8d7d15a4eb9b48b644f06c25b4d8"

SRC_URI = " \
    https://awild.cc/pub/fd/fd-${PV}-${RUSTARCH}.tar.gz;name=${HOST_ARCH} \
    file://LICENSE-MIT \
    file://LICENSE-APACHE \
"

python () {
    host_arch = d.getVar('HOST_ARCH')
    if host_arch in ('aarch64', 'x86_64'):
        d.setVar('RUSTARCH', '%s-unknown-linux-gnu'%host_arch)
    else:
        raise bb.parse.SkipRecipe('incompatible with host arch %s'%host_arch)
}

S = "${WORKDIR}/fd-${PV}-${RUSTARCH}"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${prefix}
    cp -a --no-preserve=ownership ${S}/* ${D}${prefix}
}

inherit bash-completion
PACKAGES += "${PN}-zsh-completion"
FILES_${PN}-zsh-completion = "${datadir}/zsh/site-functions"
RDEPENDS_${PN}-zsh-completion = "zsh"

RRECOMMENDS_${PN} += "${PN}-bash-completion ${PN}-zsh-completion"
INSANE_SKIP_${PN} += "already-stripped"
