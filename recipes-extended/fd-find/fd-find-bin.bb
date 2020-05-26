SUMMARY = "fd is a simple, fast and user-friendly alternative to find (prebuilt binary)"
HOMEPAGE = "https://github.com/aswild/fd"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://../LICENSE-MIT;md5=e6a265296c45f2670d948a7aa327eb80 \
    file://../LICENSE-APACHE;md5=35be2e0efac68ea4480eac769e7618a2 \
"

PROVIDES = "fd-find"
RPROVIDES_${PN} = "fd-find"

PV = "8.1.1-14-g1f4884a"
SRC_URI[aarch64.md5sum] = "d2ed7cb63344ffbdd65d555b789034eb"
SRC_URI[aarch64.sha256sum] = "c435f0a4b4c467aca2b8347472f97d6607de735511c1f181c82731f1200f0c87"
SRC_URI[x86_64.md5sum] = "ffc8f5948351c412a54608d227e53a1b"
SRC_URI[x86_64.sha256sum] = "92d39e88bf7fa9057024139b168dc1326010428cdd2ff5cc5b32e95b99fa9ef1"

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
    cp -r ${S}/* ${D}${prefix}
}

inherit bash-completion
PACKAGES += "${PN}-zsh-completion"
FILES_${PN}-zsh-completion = "${datadir}/zsh/site-functions"
RDEPENDS_${PN}-zsh-completion = "zsh"

RRECOMMENDS_${PN} += "${PN}-bash-completion ${PN}-zsh-completion"
INSANE_SKIP_${PN} += "already-stripped"
