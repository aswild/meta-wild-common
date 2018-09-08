# https://github.com/kraj/localedef/commit/c328777219ccc480be3112cf807217ca6b570b64
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://define-libio-macros-for-localedef.patch;patchdir=localedef"
