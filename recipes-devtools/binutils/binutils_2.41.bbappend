# gprofng fails to link with ld-is-gold, disable it when that distro feature is enabled
python() {
    if 'ld-is-gold' in d.getVar('DISTRO_FEATURES').split():
        d.appendVar('EXTRA_OECONF', ' --disable-gprofng')
        d.setVar('GPROFNGS', '')
}
