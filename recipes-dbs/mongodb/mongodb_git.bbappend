# this meta-oe patch breaks gcc builds
SRC_URI_remove = "file://0001-asio-Dont-use-experimental-with-clang.patch"

# scons.bbclass do_configure tries to run scons clean with PREFIX and prefix set wrong,
# mongodb_git in meta-oe overrides scons_do_compile/install to fix those options, but
# not the clean check in do_configure.
# This disables scons.bbclass' clean attempt
CLEANBROKEN = "1"

# build the mongo client for connecting to and managing things

# use the mozjs engine, needed for the mongo client
EXTRA_OESCONS_remove = "--js-engine=none"
EXTRA_OESCONS_append = " --js-engine=mozjs mongo"

# mozjs-45 contains "aarch64" but Mongo's build system adds
# "arm64" to the the compilers include dirs
fix_mozjs_platform() {
    rm -f ${S}/src/third_party/mozjs-45/platform/arm64
    ln -vsfT aarch64 ${S}/src/third_party/mozjs-45/platform/arm64
}
do_patch[postfuncs] += "fix_mozjs_platform"

# split mongo and install_compass into their own packages.
# recommend mongo (but not install_compass) by default
PACKAGES =+ "${PN}-mongo ${PN}-install-compass"
RRECOMMENDS_${PN} = "${PN}-mongo"
FILES_${PN}-mongo = "${bindir}/mongo"
FILES_${PN}-install-compass = "${bindir}/install_compass"
RDEPENDS_${PN}-install-compass = "python"
