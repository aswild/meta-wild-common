# Fix for building against glusterfs version 6.
# Patches are from upstream, found via redhat bug
# https://bugzilla.redhat.com/show_bug.cgi?id=1684298
# Use _append so this comes after all of oe-core's patches
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = "file://glusterfs-6.patch"
