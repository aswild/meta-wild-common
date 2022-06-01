# GCC 12 miscompiles something that causes java to segfault during the build and I have no idea
# why. Searching for patches or bug reports got nowhere, but it seems that simply compiling without
# optimizations is close enough.
#
# I hate icedtea and java so much right now.
CFLAGS:append = " -O0"
CXXFLAGS:append = " -O0"
