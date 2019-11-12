# sa1/sadc files are installed to ${libexecdir}/sa, but the systemd service references ${libdir}
# This should really be fixed upstream in oe-core, but work around by overriding libexecdir for
# this recipe.
libexecdir = "${libdir}"
