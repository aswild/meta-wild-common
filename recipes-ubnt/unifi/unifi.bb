# UniFi Controller recipe - based on the Arch Linux "unifi" (AUR) PKGBUILD
SUMMARY = "UniFi SDN Controller"

# The package doesn't ship with a license file
LICENSE = "UBIQUITI"
LIC_FILES_CHKSUM = "file://../sources-unpack/LICENSE.ubnt;md5=d0b4702d3eb46e97d80d14dd16338b76"
NO_GENERIC_LICENSE[UBIQUITI] = "../sources-unpack/LICENSE.ubnt"

SRC_URI = "https://dl.ubnt.com/unifi/${PV}/UniFi.unix.zip;downloadfilename=UniFi-${PV}.unix.zip \
           file://LICENSE.ubnt \
           file://ace.sh.in \
           file://unifi.service.in \
           file://unifi.env \
"

PV = "9.0.114"
SRC_URI[sha256sum] = "e34c28a430fb2b36cb1be70b78a52109d1bf559f64ae02f38c341e5c620f6787"

# Unifi controller Linux and group
UNIFI_USER  ?= "${PN}"
UNIFI_GROUP ?= "${UNIFI_USER}"
UNIFI_UID   ?= "862"
UNIFI_GID   ?= "${UNIFI_UID}"

# read-write directory to store data and logs
UNIFI_HOMEDIR ?= "/home/${UNIFI_USER}"

# no package splitting
FILES:${PN} = "${libdir}/${PN} \
               ${systemd_unitdir}/system \
               ${sysconfdir}/default/unifi.env \
               ${UNIFI_HOMEDIR}"

# unifi controller includes precompiled binaries
INSANE_SKIP:${PN} = "already-stripped"

# precompiled library links with libsystemd which generates a warning if
# systemd isn't in DEPENDS, but we don't really need that dependency.
WARN_QA:remove = "build-deps"
ERROR_QA:remove = "build-deps"

# use rsync to install because, unlike cp, it supports --exclude
DEPENDS = "rsync-native"

RDEPENDS:${PN} = "openjdk-21-jre mongodb bash libsystemd"
RRECOMMENDS:${PN} = "unifi-cert-update"

inherit systemd
SYSTEMD_SERVICE:${PN} = "unifi.service"

inherit useradd
GROUPADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system --gid ${UNIFI_GID} ${UNIFI_USER}"
USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --uid ${UNIFI_UID} --gid ${UNIFI_GID} --home-dir ${UNIFI_HOMEDIR} ${UNIFI_USER}"

# arch for prebuilt libraries
NATIVEARCH = "invalid"
NATIVEARCH:armv7a = "armv7"
NATIVEARCH:armv7ve = "armv7"
NATIVEARCH:aarch64 = "aarch64"
NATIVEARCH:x86-64 = "x86_64"

S = "${WORKDIR}/UniFi"

do_configure[noexec] = "1"

do_compile() {
    for file in ace.sh unifi.service; do
        sed -e "s|@base_bindir@|${base_bindir}|g" \
            -e "s|@bindir@|${bindir}|g" \
            -e "s|@libdir@|${libdir}|g" \
            -e "s|@sysconfdir@|${sysconfdir}|g" \
            -e "s|@UNIFI_USER@|${UNIFI_USER}|g" \
            ${UNPACKDIR}/$file.in >${UNPACKDIR}/$file
    done
}

do_install() {
    bbnote "Installing base files"
    installdir="${D}${libdir}/${PN}"
    install -d $installdir
    rsync -rptlv --exclude 'lib/native' bin dl lib webapps $installdir/
    echo '${PV}' >$installdir/version

    if [ "${NATIVEARCH}" = "invalid" ]; then
        bbfatal "Sorry, the target architecture '${TARGET_ARCH}' is not supported"
    else
        bbnote "Installing native files for ${NATIVEARCH}"
        install -d $installdir/lib/native/Linux
        rsync -rptlv lib/native/Linux/${NATIVEARCH} $installdir/lib/native/Linux
    fi

    bbnote "Creating data directories/symlinks"
    install -dm750 ${D}${UNIFI_HOMEDIR}
    for _d in data logs run work; do
        install -dm750 ${D}${UNIFI_HOMEDIR}/$_d
        ln -sv ${UNIFI_HOMEDIR}/$_d $installdir/$_d
    done

    bbnote "Replacing mongod wrapper"
    ln -sfvT ${bindir}/mongod ${D}${libdir}/${PN}/bin/mongod

    # somehow I got an image where most of the files had mode 600 and owned by root.
    # Make sure the unifi user can read everything. Use find for only files and directories,
    # because 'chmod -R' chokes on broken symlinks (probably pseudo related).
    bbnote "Fixing permissions"
    find ${D}${libdir}/${PN} \( -type f -o -type d \) -exec chmod ugo+rX {} +

    bbnote "Installing systemd service"
    install -Dm755 ${UNPACKDIR}/ace.sh ${D}${libdir}/unifi/bin/ace.sh
    install -Dm644 ${UNPACKDIR}/unifi.service ${D}${systemd_unitdir}/system/unifi.service
    install -Dm644 ${UNPACKDIR}/unifi.env ${D}${sysconfdir}/default/unifi.env
}
