# gprofng libraries fail to link, I think because of ld-is-gold causing problems again.
# Just disable the offending parts for now
EXTRA_OECONF += "--disable-gprofng"
PACKAGES:remove = "gprofng"
GPROFNGS = ""
