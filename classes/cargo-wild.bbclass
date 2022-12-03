# inherit the base cargo class, and also rust so that RUSTC gets set
inherit cargo rust

# I'm not going write a hundred recipes for every dependency of every package
# that would be madness. Instead re-enable network access during do_compile and
# disable the bitbake vendoring (which replaces/overlays the crates.io index)
# so that we let cargo fetch dependencies normally.
CARGO_DISABLE_BITBAKE_VENDORING = "1"

# Bitbake disables network access on some tasks when the kernel supports it.
# Nice feature, but not what we want here.
do_compile[network] = "1"

# compatibility with meta-rust-bin
CARGO_RELEASE_DIR = "${B}/target/${CARGO_TARGET_SUBDIR}"

# TODO - CARGO_HOME is unique per recipe in ${WORKDIR}/cargo_home, which means
# every recipe fetches its own copy of the crates.io index. Can this be shared
# somehow? CARGO_HOME/config needs to be unique because it's got all the recipe
# sysroot paths hardcoded in, but if I just make CARGO_HOME/registry a common
# symlink will that cause race conditions? Needs experimentation, but for now
# just deal with the build time hit of re-fetching the crates.io index in every
# recipe.
