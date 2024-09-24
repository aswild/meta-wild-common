SUMMARY = "The MongoDB Database"
LICENSE = "SSPL-1"
LIC_FILES_CHKSUM = "file://LICENSE-Community.txt;md5=3a865f27f11f43ecbe542d9ea387dcf1"
DEPENDS = " \
    openssl libpcap zlib boost curl libpcre2 python3 \
    python3-setuptools-native \
    python3-pyyaml-native python3-cheetah-native \
    python3-psutil-native python3-regex-native \
    python3-pymongo-native \
"

inherit scons siteinfo python3native systemd useradd

PV = "7.0.14"
SRCREV = "1b488fa20bdc54915b89f3a6ef981742adbe8cb2"
SRC_URI = " \
    git://github.com/mongodb/mongo.git;branch=v7.0;protocol=https \
    file://0001-Tell-scons-to-use-build-settings-from-environment-va.patch \
    file://arm64-support.patch \
    file://mongodb7-disable-tooling-metrics.patch \
    file://mongodb7-python3.12-build.patch \
"

S = "${WORKDIR}/git"

CVE_STATUS[CVE-2014-8180] = "not-applicable-config: Not affecting our configuration."
CVE_STATUS[CVE-2017-2665] = "not-applicable-config: Not affecting our configuration."

COMPATIBLE_HOST ?= '(x86_64|i.86|powerpc64|arm|aarch64).*-linux'

PACKAGECONFIG ??= "tcmalloc nodebug"
# gperftools compilation fails for arm below v7 because of missing support of
# dmb operation. So we use system-allocator instead of tcmalloc
PACKAGECONFIG:remove:armv6 = "tcmalloc"
PACKAGECONFIG:remove:libc-musl = "tcmalloc"
PACKAGECONFIG:remove:riscv64 = "tcmalloc"
PACKAGECONFIG:remove:riscv32 = "tcmalloc"

PACKAGECONFIG[tcmalloc] = "--use-system-tcmalloc,--allocator=system,gperftools,"
PACKAGECONFIG[shell] = ",--js-engine=none,,"
PACKAGECONFIG[mongos] = ""
PACKAGECONFIG[nodebug] = ""

MONGO_ARCH ?= "${HOST_ARCH}"
MONGO_ARCH:powerpc64le = "ppc64le"
WIREDTIGER ?= "off"
WIREDTIGER:x86-64 = "on"
WIREDTIGER:aarch64 = "on"

# Disable building with debug info to save time and disk space
DEBUG_FLAGS:remove = "${@bb.utils.contains('PACKAGECONFIG', 'nodebug', '-g', '', d)}"
DEBUG_FLAGS:prepend = "${@bb.utils.contains('PACKAGECONFIG', 'nodebug', '-g0 ', '', d)}"

EXTRA_OESCONS = " \
    LIBPATH='${STAGING_LIBDIR}' \
    LINKFLAGS='${LDFLAGS}' \
    CXXFLAGS='${CXXFLAGS}' \
    TARGET_ARCH='${MONGO_ARCH}' \
    MONGO_VERSION='${PV}' \
    OBJCOPY='${OBJCOPY}' \
    --ssl \
    --disable-warnings-as-errors \
    --use-system-pcre2 \
    --use-system-zlib \
    --endian=${@oe.utils.conditional('SITEINFO_ENDIANNESS', 'le', 'little', 'big', d)} \
    --use-hardware-crc32=${@bb.utils.contains('TUNE_FEATURES', 'crc', 'on', 'off', d)} \
    --wiredtiger='${WIREDTIGER}' \
    --separate-debug \
    --link-model=static \
    --linker=gold \
    ${PACKAGECONFIG_CONFARGS} \
"

# What build targets we want during do_compile
SCONS_BUILD_TARGETS = " \
    install-mongod \
    ${@bb.utils.contains('PACKAGECONFIG', 'shell', 'install-mongo', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'mongos', 'install-mongos', '', d)} \
"

mozjs_symlink_arm64() {
    ln -sfT aarch64 ${S}/src/third_party/mozjs/platform/arm64
}
do_patch[postfuncs] += "mozjs_symlink_arm64"

scons_do_compile() {
    # like the scons.bbclass version but include "$@" so we can customize build targets
    ${STAGING_BINDIR_NATIVE}/scons --directory=${S} ${PARALLEL_MAKE} \
            PREFIX=${prefix} prefix=${prefix} ${EXTRA_OESCONS} "$@" || \
        die "scons build execution failed."
}

do_compile() {
    scons_do_compile ${SCONS_BUILD_TARGETS}
}

do_install() {
    # install binaries
    install -d ${D}${bindir}
    local i
    for i in mongod mongos mongo; do
        if [ -f ${B}/build/install/${bindir}/$i ]; then
            install -m 0755 ${B}/build/install/${bindir}/$i ${D}${bindir}
        else
            bbnote "$i does not exist"
        fi
    done

    # install config
    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/debian/mongod.conf ${D}${sysconfdir}

    # install systemd service
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/debian/mongod.service ${D}${systemd_system_unitdir}

    # install mongo data folder
    install -m 755 -d ${D}${localstatedir}/lib/mongodb
    chown mongodb:mongodb ${D}${localstatedir}/lib/mongodb

    # Create /var/log/mongodb in runtime.
    if [ "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}" ]; then
        install -d ${D}${nonarch_libdir}/tmpfiles.d
        echo "d ${localstatedir}/log/mongodb 0755 mongodb mongodb -" \
            > ${D}${nonarch_libdir}/tmpfiles.d/mongodb.conf
    fi
    if [ "${@bb.utils.filter('DISTRO_FEATURES', 'sysvinit', d)}" ]; then
        install -d ${D}${sysconfdir}/default/volatiles
        echo "d mongodb mongodb 0755 ${localstatedir}/log/mongodb none" \
            > ${D}${sysconfdir}/default/volatiles/99_mongodb
    fi
}

RDEPENDS:${PN} += "tzdata-core"

PROVIDES += "mongodb"
RPROVIDES:${PN} += "mongodb"
RCONFLICTS:${PN} += "mongodb"

# Package the config and service files separately
PACKAGES =+ "${PN}-service"
RDEPENDS:${PN}-service = "${PN}"
RPROVIDES:${PN}-service = "mongodb-service"
FILES:${PN}-service = " \
    ${sysconfdir} \
    ${nonarch_libdir}/tmpfiles.d \
    ${systemd_system_unitdir} \
    ${localstatedir}/lib/mongodb \
"

USERADD_PACKAGES = "${PN}-service"
USERADD_PARAM:${PN}-service = "--system --no-create-home --home-dir /var/run/mongodb --shell /bin/false --user-group mongodb"
CONFFILES:${PN}-service = "${sysconfdir}/mongod.conf"
SYSTEMD_SERVICE:${PN}-service = "mongod.service"
