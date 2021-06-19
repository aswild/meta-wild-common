# so symlinks conflict with libxcrypt-dev and break -dev images
# move them to a separate split package instead
FILES_${PN}-dev = ""

# make package name end in "-dev" to avoid [dev-so] QA error
PACKAGES =+ "${PN}-so-dev"
FILES_${PN}-so-dev = "${libdir}/*.so"
