# base class for customized images

inherit core-image

# enable BCJ filters for squashfs xz
IMAGE_CMD_squashfs-xz_append_x86 = " -Xbcj x86"
IMAGE_CMD_squashfs-xz_append_x86-64 = " -Xbcj x86"
IMAGE_CMD_squashfs-xz_append_arm = " -Xbcj arm,armthumb"
IMAGE_CMD_squashfs-xz_append_aarch64 = " -Xbcj arm"

# common image postprocess commands

copy_ssh_host_keys() {
    install -d ${IMAGE_ROOTFS}${sysconfdir}/ssh
    if [ -n "${SSH_HOST_KEYS}" ]; then
        for key in ${SSH_HOST_KEYS}; do
            cp -vf ${key} ${IMAGE_ROOTFS}${sysconfdir}/ssh/
        done
    else
        # If an SSH key path isn't set, create empty files anyway so
        # newbs-nvram can bind-mount over them
        for key in ssh_host_{dsa,ecdsa,ed25519,rsa}_key{,.pub}; do
            echo "Creating empty ${IMAGE_ROOTFS}${sysconfdir}/ssh/${key}"
            touch ${IMAGE_ROOTFS}${sysconfdir}/ssh/${key}
        done
    fi
}
# prepend this command so that it runs before read_only_rootfs_hook
ROOTFS_POSTPROCESS_COMMAND_prepend = "copy_ssh_host_keys; "

wild_rootfs_postprocess() {
    # Yocto will install the kernel image to /boot, but we don't want that because
    # the boot partition will be mounted in /boot (by fstab in base-files)
    rm -rfv ${IMAGE_ROOTFS}/boot/*

    # Mark the rootfs as rw in fstab. This gives us rw when booting ext4,
    # and squashfs will mount ro always anyway
    sed -i '/\/dev\/root/s/\bro\b/rw/' ${IMAGE_ROOTFS}/etc/fstab

    # set timezone to match the build host
    localtime_file=${sysconfdir}/localtime
    if [ -L $localtime_file ]; then
        host_tz=`readlink -f $localtime_file`
        if [ -n ${host_tz} ] && [ -e ${IMAGE_ROOTFS}${host_tz} ]; then
            bbnote "Setting timezone to ${host_tz}"
            ln -sfv ${host_tz} ${IMAGE_ROOTFS}${localtime_file}
        fi
    fi

    # make zsh or bash the default shell
    if [ -x ${IMAGE_ROOTFS}/bin/zsh ]; then
        bbnote "Setting /bin/zsh as default root shell"
        sed -i '/^root/s|/bin/sh$|/bin/zsh|' ${IMAGE_ROOTFS}/etc/passwd
    elif [ -x ${IMAGE_ROOTFS}/bin/bash -o -L ${IMAGE_ROOTFS}/bin/bash ]; then
        # /bin/bash might be a symlink to /bin/bash.bash
        bbnote "Setting /bin/bash as default root shell"
        sed -i '/^root/s|/bin/sh$|/bin/bash|' ${IMAGE_ROOTFS}/etc/passwd
    fi

    # Use systemd-networkd and systemd-resolvd
    rm -rvf ${IMAGE_ROOTFS}${sysconfdir}network

    # use systemd-resolved for /etc/resolv.conf
    if [ -f ${IMAGE_ROOTFS}/lib/systemd/systemd-resolved ]; then
        rm -f ${IMAGE_ROOTFS}/etc/resolv.conf
        ln -s /run/systemd/resolve/stub-resolv.conf ${IMAGE_ROOTFS}/etc/resolv.conf
    fi

    # systemd units that use StateDirectory= need /var/lib to be writable. We have mount-copybind
    # to do that (via var-volatile-lib.service which comes before local-fs.target), but
    # some of these units (like systemd-rfkill) use DefaultDependencies=no, so we need
    # an explicit dependency.
    if [ -d ${IMAGE_ROOTFS}${systemd_unitdir} ] && \
       ${@bb.utils.contains('IMAGE_FEATURES', 'read-only-rootfs', 'true', 'false', d)}; then
        local service
        find ${IMAGE_ROOTFS}${systemd_unitdir} -name '*.service' -type f | while read service; do
            if grep -q '^StateDirectory' $service; then
                install -d ${service}.d
                echo -e '[Unit]\nAfter=var-volatile-lib.service' >${service}.d/var-volatile-lib.conf
            fi
        done
    fi

    # Make /media a symlink to /run/media
    rm -rf ${IMAGE_ROOTFS}/media
    ln -sfv run/media ${IMAGE_ROOTFS}/media

    # base-files_3.0.14.bbappend in meta-java sets JAVA_HOME in /etc/profile
    # but does so assuming that /usr/lib/jvm actually has stuff in it. If we don't
    # have a JVM installed, every login prints an error
    #    /etc/profile:36: no matches found: /usr/lib/jvm/*
    # work around this by installing /usr/lib/jvm/dummy if necessary
    if grep -qF 'for dir in ${libdir}/jvm/*' ${IMAGE_ROOTFS}${sysconfdir}/profile \
       && [ ! -d ${IMAGE_ROOTFS}${libdir}/jvm ]; then
        install -d ${IMAGE_ROOTFS}${libdir}/jvm
        touch ${IMAGE_ROOTFS}${libdir}/jvm/dummy
    fi
}
ROOTFS_POSTPROCESS_COMMAND_append = " wild_rootfs_postprocess;"

# Don't spam DEPLOYDIR with testdata.json files. Unfortunately for us,
# rootfs-postcommands.bbclass adds "write_image_test_data ;" and that space makes it so
# that typical _remove syntax doesn't work right
python __anonymous() {
    import re
    cmd = d.getVar('ROOTFS_POSTPROCESS_COMMAND')
    cmd = re.sub(r'write_image_test_data\s*;', '', cmd)
    d.setVar('ROOTFS_POSTPROCESS_COMMAND', cmd)
}