# missing "typedef" on enum definition, which causes it to get instantiated as a global,
# which then fails to compile because of -fno-common in gcc10
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://typedef-pseudo-access-t.patch"
