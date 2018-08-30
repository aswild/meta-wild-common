# UniFi Controller recipe - based on the Arch Linux "unifi" (AUR) PKGBUILD
SUMMARY = "UniFi SDN Controller"
PV = "5.8.28"

# The package doesn't ship with a license file
LICENSE = "UBIQUITI"
LIC_FILES_CHKSUM = "file://../LICENSE.ubnt;md5=d0b4702d3eb46e97d80d14dd16338b76"
NO_GENERIC_LICENSE[UBIQUITI] = "../LICENSE.ubnt"

SRC_URI = "https://dl.ubnt.com/unifi/${PV}/UniFi.unix.zip \
           file://LICENSE.ubnt \
           file://unifi.service \
"
SRC_URI[md5sum] = "2a9a20e704d80e94f92949a14e1ea517"
SRC_URI[sha256sum] = "236732b433d93c279835a33a3a72b2dcff1dad0adbe3ccb91401e537137f6c6b"

# read-write directory to store data and logs
UNIFI_HOMEDIR ?= "/home/${PN}"

# no package splitting
PACKAGES = "${PN}"
FILES_${PN} = "${libdir}/${PN} \
               ${systemd_unitdir}/system \
               ${UNIFI_HOMEDIR}"

# unifi controller includes precompiled binaries
INSANE_SKIP_${PN} = "already-stripped"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# precompiled library links with libsystemd which generates a warning if
# systemd isn't in DEPENDS, but we don't really need that dependency.
WARN_QA_remove = "build-deps"
ERROR_QA_remove = "build-deps"

# use rsync to install because, unlike cp, it supports --exclude
DEPENDS = "rsync-native"

RDEPENDS_${PN} = "openjre-8 mongodb"

inherit systemd
SYSTEMD_SERVICE_${PN} = "unifi.service"

inherit useradd
GROUPADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system --gid 862 ${PN}"
USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --uid 862 --gid ${PN} --home-dir ${UNIFI_HOMEDIR} ${PN}"

# arch for prebuilt libraries
NATIVEARCH = "invalid"
NATIVEARCH_armv7a = "armv7"
NATIVEARCH_armv7ve = "armv7"
NATIVEARCH_aarch64 = "aarch64"
NATIVEARCH_x86-64 = "x86_64"

S = "${WORKDIR}/UniFi"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    bbnote "Installing base files"
    installdir="${D}${libdir}/${PN}"
    install -d $installdir
    rsync -rptlv --exclude 'lib/native' bin dl lib webapps $installdir/

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

    bbnote "Installing systemd service"
    install -Dm644 ${WORKDIR}/unifi.service ${D}${systemd_unitdir}/system/unifi.service
}
