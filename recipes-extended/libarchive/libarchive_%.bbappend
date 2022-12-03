# add zstd support
PACKAGECONFIG:append:class-target = " zstd"
PACKAGECONFIG[zstd] = "--with-zstd,--without-zstd,zstd,"
