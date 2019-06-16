# overlay rngd.service with tweaks based on Arch's version (but with systemd-udev-settle.service
# added based on oe-core's rngd.service)
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# disable jitterentropy random source, it's just a bunch of extra unneeded CPU work
PACKAGECONFIG_remove = "libjitterentropy"
