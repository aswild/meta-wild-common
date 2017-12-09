# for packages which don't honor usrmerge properly

fix_usrmerge() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'usrmerge', 'true', 'false', d)}; then
        for dir in bin sbin lib; do
            [ -d ${D}/$dir ] || continue
            bbnote "moving files from /$dir to /usr/$dir"
            mkdir -p ${D}/usr/$dir
            for file in ${D}/$dir/*; do
                # if the /dir/file is a symlink and /usr/dir/file exists, remove it
                # (assumption is that /dir/file points to /usr/dir file)
                if [ -L $file -a -e ${D}/usr/$dir/`basename $file` ]; then
                    rm -v $file
                else
                    mv -vf $file ${D}/usr/$dir/
                fi
            done
            rmdir ${D}/$dir
        done
    fi
}

do_install[postfuncs] += "fix_usrmerge"
