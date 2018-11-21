# The xfsprogs configure script and makefiles try to be a bit too clever
# There's no proper configure options for these, but we can override the
# variables at make time.

# don't compress man pages, that's bad on squashfs
EXTRA_OEMAKE += "HAVE_ZIPPED_MANPAGES=false"

# the makefiles try to be clever but don't know about usrmerge
EXTRA_OEMAKE += "PKG_ROOT_SBIN_DIR='${base_sbindir}' PKG_ROOT_LIB_DIR='${base_libdir}'"
