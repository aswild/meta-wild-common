FILES_${PN} += "${sysconfdir}/profile.d/lang.sh"

do_install_append() {
    install -d ${D}${sysconfdir}/profile.d
    cat >${D}${sysconfdir}/profile.d/lang.sh <<_EOF
export LANG=en_US.UTF-8
export LC_ALL=en_US.UTF-8
_EOF
}
