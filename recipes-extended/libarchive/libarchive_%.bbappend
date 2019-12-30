# add zstd support
PACKAGECONFIG_append_class-target = " zstd"
PACKAGECONFIG[zstd] = "--with-zstd,--without-zstd,zstd,"
