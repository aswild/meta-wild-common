# un-skip this recipe, python 3.12 issues are patched here
SKIP_RECIPE[mongodb] = ""

# Patch out -Wredundant-move, this warning is triggered all over the place and clutters up the log,
# making it hard to find real errors. Like, literally half of log.do_compile is g++ complaining
# about -Wredundant-move.
# Also compiling without debug info (-g0) saves ~7 minutes of build time and ~12GB of build objects
FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI += " \
    file://wild-build-flags.patch \
    file://wild-python3.12-build.patch \
"

# DEBUG_FLAGS propagates through various yocto variables into CFLAGS, CXXFLAGS, LDFLAGS, etc.
DEBUG_FLAGS:remove = "-g"
DEBUG_FLAGS:prepend = "-g0 "

# Don't build mongos
SCONS_BUILD_TARGETS = "install-mongod"

scons_do_compile() {
    # like the scons.bbclass version but include "$@" so we can customize build targets
    ${STAGING_BINDIR_NATIVE}/scons --directory=${S} ${PARALLEL_MAKE} \
            PREFIX=${prefix} prefix=${prefix} ${EXTRA_OESCONS} "$@" || \
        die "scons build execution failed."
}

do_compile() {
    scons_do_compile ${SCONS_BUILD_TARGETS}
}

# package service and conf files into ${PN}-service, leaving only core binaies in the main package
PACKAGES =+ "${PN}-service"
RDEPENDS:${PN}-service = "${PN}"
FILES:${PN}-service = " \
    ${sysconfdir} \
    ${nonarch_libdir}/tmpfiles.d \
    ${systemd_system_unitdir} \
    ${localstatedir}/lib/${BPN} \
"

FILES:${PN}:remove = "${nonarch_libdir}/tmpfiles.d"

USERADD_PACKAGES = "${PN}-service"
USERADD_PARAM:${PN}-service = "${USERADD_PARAM:${PN}}"

CONFFILES:${PN} = ""
CONFFILES:${PN}-service = "${sysconfdir}/mongod.conf"

SYSTEMD_SERVICE:${PN} = ""
SYSTEMD_SERVICE:${PN}-service = "mongod.service"

# Don't warn about absolute paths in the binary
WARN_QA:remove = "buildpaths"
