# Patch out -Wredundant-move, this warning is triggered all over the place and clutters up the log,
# making it hard to find real errors. Like, literally half of log.do_compile is g++ complaining
# about -Wredundant-move.
# Also compiling without debug info (-g0) saves ~7 minutes of build time and ~12GB of build objects
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += " \
    file://wild-build-flags.patch \
    file://wild-python3.12-build.patch \
"

# DEBUG_FLAGS propagates through various yocto variables into CFLAGS, CXXFLAGS, LDFLAGS, etc.
DEBUG_FLAGS:remove = "-g"
DEBUG_FLAGS:prepend = "-g0 "

# don't install a systemd service for mongodb, and sure as hell don't auto-start it
do_install:append() {
    rm -v ${D}${systemd_system_unitdir}/mongod.service
    # removing the service file leaves probably-empty directories that cause package QA errors.
    # this assumes systemd_system_unitdir=${nonarch_base_libdir}/systemd/system
    rmdir ${D}${systemd_system_unitdir}
    rmdir ${D}${nonarch_base_libdir}/systemd
    rmdir ${D}${nonarch_base_libdir} || true
}

SYSTEMD_SERVICE:${PN} = ""

# Don't warn about absolute paths in the binary
WARN_QA:remove = "buildpaths"

# un-skip this recipe, python 3.12 issues are patched here
SKIP_RECIPE[mongodb] = ""
