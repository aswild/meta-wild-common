# fix build with native GCC 10
CFLAGS_append_class-native += " -fcommon"
LDFLAGS_append_class-native += " -fcommon"
