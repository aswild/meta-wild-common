SUMMARY = "mongodb"
LICENSE = "SSPL-1"
LIC_FILES_CHKSUM = "file://LICENSE-Community.txt;md5=3a865f27f11f43ecbe542d9ea387dcf1"
DEPENDS = "openssl libpcap zlib boost curl python3 \
           python3-pip-native \
"
olddepends = "\
           python3-setuptools-native \
           python3-pyyaml-native python3-cheetah-native \
           python3-psutil-native python3-regex-native \
           "

inherit scons dos2unix siteinfo python3native systemd useradd

PV = "7.0.14"
SRCREV = "1b488fa20bdc54915b89f3a6ef981742adbe8cb2"
SRC_URI = "git://github.com/mongodb/mongo.git;branch=v7.0;protocol=https"
S = "${WORKDIR}/git"

CVE_STATUS[CVE-2014-8180] = "not-applicable-config: Not affecting our configuration so it can be safely ignored."
CVE_STATUS[CVE-2017-2665] = "not-applicable-config: Not affecting our configuration so it can be safely ignored."

COMPATIBLE_HOST ?= '(x86_64|i.86|powerpc64|arm|aarch64).*-linux'

PACKAGECONFIG ??= "tcmalloc system-pcre"
# gperftools compilation fails for arm below v7 because of missing support of
# dmb operation. So we use system-allocator instead of tcmalloc
PACKAGECONFIG:remove:armv6 = "tcmalloc"
PACKAGECONFIG:remove:libc-musl = "tcmalloc"
PACKAGECONFIG:remove:riscv64 = "tcmalloc"
PACKAGECONFIG:remove:riscv32 = "tcmalloc"

PACKAGECONFIG[tcmalloc] = "--use-system-tcmalloc,--allocator=system,gperftools,"
PACKAGECONFIG[shell] = ",--js-engine=none,,"
PACKAGECONFIG[system-pcre] = "--use-system-pcre,,libpcre,"

MONGO_ARCH ?= "${HOST_ARCH}"
MONGO_ARCH:powerpc64le = "ppc64le"
WIREDTIGER ?= "off"
WIREDTIGER:x86-64 = "on"
WIREDTIGER:aarch64 = "on"

# ld.gold: fatal error: build/59f4f0dd/mongo/mongod: Structure needs cleaning
LDFLAGS:append:x86:libc-musl = " -fuse-ld=bfd"
LDFLAGS:remove:toolchain-clang = "-fuse-ld=bfd"

EXTRA_OESCONS = "PREFIX=${prefix} \
                 DESTDIR=${D} \
                 MAXLINELENGTH='2097152' \
                 LIBPATH=${STAGING_LIBDIR} \
                 LINKFLAGS='${LDFLAGS}' \
                 CXXFLAGS='${CXXFLAGS}' \
                 TARGET_ARCH=${MONGO_ARCH} \
                 MONGO_VERSION=${PV} \
                 OBJCOPY=${OBJCOPY} \
                 --ssl \
                 --disable-warnings-as-errors \
                 --use-system-zlib \
                 --nostrip \
                 --endian=${@oe.utils.conditional('SITEINFO_ENDIANNESS', 'le', 'little', 'big', d)} \
                 --use-hardware-crc32=${@bb.utils.contains('TUNE_FEATURES', 'crc', 'on', 'off', d)} \
                 --wiredtiger='${WIREDTIGER}' \
                 --separate-debug \
                 ${PACKAGECONFIG_CONFARGS}"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --no-create-home --home-dir /var/run/${BPN} --shell /bin/false --user-group ${BPN}"

do_configure[network] = "1"

do_configure:prepend() {
    #rm -rf ${B}/venv
    mkdir -p ${WORKDIR}/pip-cache

    # TODO MAKE THIS A PATCH
    sed -i '/^setuptools/d' ${S}/etc/pip/components/compile.req

    #${PYTHON} -m venv --system-site-packages ${B}/venv
    #${PYTHON} -m venv ${B}/venv
    #. ${B}/venv/bin/activate
    (
        export AR="${BUILD_AR}"
        export AS="${BUILD_AS}"
        export CC="${BUILD_CC}"
        export CCLD="${BUILD_CCLD}"
        export CPP="${BUILD_CPP}"
        export CXX="${BUILD_CXX}"
        export FC="${BUILD_FC}"
        export LD="${BUILD_LD}"
        export NM="${BUILD_NM}"
        export RANLIB="${BUILD_RANLIB}"
        export STRIP="${BUILD_STRIP}"
        export CFLAGS="${BUILD_CFLAGS}"
        export CPPFLAGS="${BUILD_CPPFLAGS}"
        export CXXFLAGS="${BUILD_CXXFLAGS}"
        export LDFLAGS="${BUILD_LDFLAGS}"
        #which pip
        #which python
        #python -m ensurepip
        pip3 install --cache-dir=${WORKDIR}/pip-cache 'cython<3.0.0' # PyYAML 5.4 doesn't build with cython3
        pip3 install --cache-dir=${WORKDIR}/pip-cache -r ${S}/etc/pip/compile-requirements.txt
    )
}

do_compile() {
    #. ${B}/venv/bin/activate
    scons_do_compile
}

scons_do_compile() {
    ${STAGING_BINDIR_NATIVE}/scons ${PARALLEL_MAKE} ${EXTRA_OESCONS} install-mongod ||
        die "scons build execution failed."
}

scons_do_install() {
    # install binaries
    install -d ${D}${bindir}
    for i in mongod mongos mongo; do
        if [ -f ${B}/build/*/mongo/$i ]; then
            install -m 0755 ${B}/build/*/mongo/$i ${D}${bindir}
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
    install -m 755 -d ${D}${localstatedir}/lib/${BPN}
    chown ${BPN}:${BPN} ${D}${localstatedir}/lib/${BPN}

    # Create /var/log/mongodb in runtime.
    if [ "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}" ]; then
        install -d ${D}${nonarch_libdir}/tmpfiles.d
        echo "d ${localstatedir}/log/${BPN} 0755 ${BPN} ${BPN} -" > ${D}${nonarch_libdir}/tmpfiles.d/${BPN}.conf
    fi
    if [ "${@bb.utils.filter('DISTRO_FEATURES', 'sysvinit', d)}" ]; then
        install -d ${D}${sysconfdir}/default/volatiles
        echo "d ${BPN} ${BPN} 0755 ${localstatedir}/log/${BPN} none" > ${D}${sysconfdir}/default/volatiles/99_${BPN}
    fi
}

CONFFILES:${PN} = "${sysconfdir}/mongod.conf"

SYSTEMD_SERVICE:${PN} = "mongod.service"

FILES:${PN} += "${nonarch_libdir}/tmpfiles.d"

RDEPENDS:${PN} += "tzdata-core"

SKIP_RECIPE[mongodb] ?= "Needs porting to python 3.12"
