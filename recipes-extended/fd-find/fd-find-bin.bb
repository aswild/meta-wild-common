SUMMARY = "fd is a simple, fast and user-friendly alternative to find (prebuilt binary)"
HOMEPAGE = "https://github.com/aswild/fd"
LICENSE = "MIT | Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://../LICENSE-MIT;md5=e6a265296c45f2670d948a7aa327eb80 \
    file://../LICENSE-APACHE;md5=35be2e0efac68ea4480eac769e7618a2 \
"

PROVIDES = "fd-find"
RPROVIDES_${PN} = "fd-find"

PV = "8.1.1-20-g65eddf3"
SRC_URI[aarch64.sha256sum] = "94775a2189e7478ca39bfde167eeef5d89662fec998bc3bde7fd208ed66daf4b"
SRC_URI[x86_64.sha256sum] = "5ee8e107e436592a63279729cc14df109de08390f890aad6a1277ade19b45230"

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
