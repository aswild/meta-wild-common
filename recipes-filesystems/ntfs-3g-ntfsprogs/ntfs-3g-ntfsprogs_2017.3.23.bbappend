# 0001-Make-build-support-usrmerge.patch in meta-openembedded removes these symlinks in
# hardcoded non-usrmerge dirs, but doesn't do anything to put versions of them in
# the usrmerge'd directories
do_install_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', 'true', 'false', d)}; then
        ln -svT mkntfs ${D}${sbindir}/mkfs.ntfs
        # oe-core/scripts/lnr creates relative symlinks
        lnr ${D}${bindir}/ntfs-3g ${D}${sbindir}/mount.ntfs-3g
        lnr ${D}${bindir}/lowntfs-3g ${D}${sbindir}/mount.lowntfs-3g
    fi
}
