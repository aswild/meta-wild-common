SUMMARY = "The MongoDB Database"
LICENSE = "SSPL-1"
LIC_FILES_CHKSUM = "file://LICENSE-Community.txt;md5=3a865f27f11f43ecbe542d9ea387dcf1"
DEPENDS = " \
    boost \
    curl \
    libpcap \
    libpcre2 \
    openssl \
    python3 \
    python3-cheetah-native \
    python3-git-native \
    python3-psutil-native \
    python3-pymongo-native \
    python3-pyyaml-native \
    python3-regex-native \
    python3-setuptools-native \
    zlib \
"

inherit dos2unix scons siteinfo python3native systemd useradd

PV = "8.0.4"
SRCREV = "3921bf537153dbdd3f9181aceb61ea0e4a057471"
SRC_URI = " \
    git://github.com/mongodb/mongo.git;branch=v8.0;protocol=https \
    file://0001-Tell-scons-to-use-build-settings-from-environment-va.patch \
    file://arm64-support.patch \
    file://mongodb8-python3.12-build.patch \
    file://mongodb8-disable-unneeded-build-cruft.patch \
"

S = "${WORKDIR}/git"

COMPATIBLE_HOST ?= '(x86_64|i.86|powerpc64|arm|aarch64).*-linux'

PACKAGECONFIG ??= "tcmalloc nodebug"
# gperftools compilation fails for arm below v7 because of missing support of
# dmb operation. So we use system-allocator instead of tcmalloc
PACKAGECONFIG:remove:armv6 = "tcmalloc"
PACKAGECONFIG:remove:libc-musl = "tcmalloc"
PACKAGECONFIG:remove:riscv64 = "tcmalloc"
PACKAGECONFIG:remove:riscv32 = "tcmalloc"

PACKAGECONFIG[tcmalloc] = "--use-system-tcmalloc-gperf,--allocator=system,gperftools,"
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

# Suppress some noisy warnings
CXXFLAGS += "-Wno-interference-size -Wno-attributes"

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

# Skip cleaning in do_configure. Cleaning isn't actually broken, but builds take so long that we
# want to be able to do them incrementally after e.g. fixing patches.
CLEANBROKEN = "1"

# What build targets we want during do_compile
SCONS_BUILD_TARGETS = " \
    install-mongod \
    ${@bb.utils.contains('PACKAGECONFIG', 'shell', 'install-mongo', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'mongos', 'install-mongos', '', d)} \
"

symlink_arm64() {
    ln -sfT aarch64 ${S}/src/third_party/mozjs/platform/arm64
    ln -sfT build_linux_aarch64 ${S}/src/third_party/snappy/platform/build_linux_arm64
}
do_patch[postfuncs] += "symlink_arm64"

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

# Don't complain about absolute paths in the binary
WARN_QA:remove = "buildpaths"
ERROR_QA:remove = "buildpaths"
