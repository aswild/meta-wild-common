# remove some useless test binaries from packaging,
# for some reason /usr/bin/conflict likes to cause prelink
# to crash in dev-image-tstick.

do_install_append_class-target() {
    for f in baddbdir conflict dertimetest encodeinttest nonspr10 \
        remtest secmodtest; do
        rm -f ${D}${bindir}/$f
    done
}
