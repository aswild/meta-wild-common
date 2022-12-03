# fix build with native GCC 10
CFLAGS:append:class-native = " -fcommon"
LDFLAGS:append:class-native = " -fcommon"
