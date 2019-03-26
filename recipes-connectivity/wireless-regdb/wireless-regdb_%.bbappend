# workaround for dev images: don't have wireless-regdb-dev try to pull in
# wireless-regdb, which conflicts with -static. I can't find a way to actually
# remove the -dev package, so just remove the conflict and dev images will contain
# both the normal and -static packages (which don't really conflict in a package-manager
# sense of the word)
RCONFLICTS_${PN}_remove = "${PN}-static"
