# disable jitterentropy random source, it's just a bunch of extra unneeded CPU work
PACKAGECONFIG_remove = "libjitterentropy"
