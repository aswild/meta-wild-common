SUMMARY = "fd is a simple, fast and user-friendly alternative to find (prebuilt binary)"
HOMEPAGE = "https://github.com/aswild/fd"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://../LICENSE-MIT;md5=e6a265296c45f2670d948a7aa327eb80 \
    file://../LICENSE-APACHE;md5=35be2e0efac68ea4480eac769e7618a2 \
"

PROVIDES = "fd-find"
RPROVIDES_${PN} = "fd-find"

PV = "8.1.0-12-g1565770"
SRC_URI[aarch64.md5sum] = "7276d1e9af0b98150af4ec62f9ce3e57"
SRC_URI[aarch64.sha256sum] = "1f7b4a68a6acbb47ec0a2adda7429b66bc38dc183d89a9694003ddc16330cb70"
SRC_URI[x86_64.md5sum] = "09a8c653a02fb57f9dcd1f3c6b9a710e"
SRC_URI[x86_64.sha256sum] = "3ba10027e873e7f54ec53064869c922297b803a5f810cb5e1befad6469bfbb80"

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
