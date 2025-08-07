# gperftools (pprof) requires binutils and perl, which aren't needed by just
# libtcmalloc, so package libtcmalloc(_debug) separately just like
# libtcmalloc-minimal already is.

PACKAGE_BEFORE_PN += "libtcmalloc"
FILES:libtcmalloc = "${libdir}/libtcmalloc${SOLIBS} ${libdir}/libtcmalloc_debug${SOLIBS}"
