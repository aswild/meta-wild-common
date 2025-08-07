# un-skip this recipe, python 3.12 issues are patched here
SKIP_RECIPE[mongodb] = ""

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

# Patch out some extremely noisy compiler warnings, and remove hard-coded -ggdb
SRC_URI += "file://wild-build-flags.patch"
# Fix moduleconfig.py for python 3.12
SRC_URI += "file://wild-python3.12-build.patch"
# Fix build with scons 4.9, call= in CheckLibWithHeader should always have been a kwarg
SRC_URI += "file://wild-scons-4.9.patch"

# Compiling without debug info (-g0) saves ~25% build time and ~8.5GB of build objects.
# DEBUG_LEVELFLAG -> FULL_OPTIMIZATION -> SELECTED_OPTIMIZATION -> TARGET_C(XX)FLAGS
DEBUG_LEVELFLAG = "-g0"

# only build mongod, not mongos
scons_do_compile() {
    ${STAGING_BINDIR_NATIVE}/scons ${PARALLEL_MAKE} ${EXTRA_OESCONS} install-mongod || \
        die "scons build execution failed."
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

# Don't complain about absolute paths in the binary
WARN_QA:remove = "buildpaths"
ERROR_QA:remove = "buildpaths"
