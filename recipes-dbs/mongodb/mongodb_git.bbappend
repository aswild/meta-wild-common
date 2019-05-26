# this meta-oe patch breaks gcc builds
SRC_URI_remove = "file://0001-asio-Dont-use-experimental-with-clang.patch"

# scons.bbclass do_configure tries to run scons clean with PREFIX and prefix set wrong,
# mongodb_git in meta-oe overrides scons_do_compile/install to fix those options, but
# not the clean check in do_configure.
# This disables scons.bbclass' clean attempt
CLEANBROKEN = "1"
