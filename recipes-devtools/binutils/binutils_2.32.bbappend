# fix build with GCC 10

CFLAGS_append_class-native = " -fcommon"
CXXFLAGS_append_class-native = " -fcommon"
LDFLAGS_append_class-native = " -fcommon"

CFLAGS_append_class-cross = " -fcommon"
CXXFLAGS_append_class-cross = " -fcommon"
LDFLAGS_append_class-cross = " -fcommon"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://gold-errors-include-string.patch"
