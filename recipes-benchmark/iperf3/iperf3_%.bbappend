# staticly link iperf3 to libiperf so that the binary is more portable,
# and split libiperf into its own package
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://iperf3-libiperf-static.patch"

PACKAGES =+ "libiperf"
FILES:libiperf = "${libdir}/libiperf${SOLIBS}"
