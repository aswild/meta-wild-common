SUMMARY = "fd is a simple, fast and user-friendly alternative to find."
HOMEPAGE = "https://github.com/aswild/fd"
LICENSE = "MIT | Apache-2.0"

LIC_FILES_CHKSUM = " \
    file://LICENSE-MIT;md5=e6a265296c45f2670d948a7aa327eb80 \
    file://LICENSE-APACHE;md5=35be2e0efac68ea4480eac769e7618a2 \
"


SRC_URI += "git://github.com/aswild/fd;protocol=https"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"
#PV = "8.0.0+git${SRCPV}"
PV = "8.0.0+git"

inherit cargo
CARGO_SRC_DIR = ""

SRC_URI += " \
    crate://crates.io/aho-corasick/0.7.10 \
    crate://crates.io/ansi_term/0.11.0 \
    crate://crates.io/ansi_term/0.12.1 \
    crate://crates.io/anyhow/1.0.28 \
    crate://crates.io/atty/0.2.14 \
    crate://crates.io/autocfg/1.0.0 \
    crate://crates.io/bitflags/1.2.1 \
    crate://crates.io/bstr/0.2.12 \
    crate://crates.io/cc/1.0.50 \
    crate://crates.io/cfg-if/0.1.10 \
    crate://crates.io/clap/2.33.0 \
    crate://crates.io/crossbeam-channel/0.4.2 \
    crate://crates.io/crossbeam-utils/0.7.2 \
    crate://crates.io/ctrlc/3.1.4 \
    crate://crates.io/diff/0.1.12 \
    crate://crates.io/filetime/0.2.9 \
    crate://crates.io/fnv/1.0.6 \
    crate://crates.io/fs_extra/1.1.0 \
    crate://crates.io/fuchsia-cprng/0.1.1 \
    crate://crates.io/globset/0.4.5 \
    crate://crates.io/hermit-abi/0.1.11 \
    crate://crates.io/humantime/2.0.0 \
    crate://crates.io/ignore/0.4.14 \
    crate://crates.io/jemalloc-sys/0.3.2 \
    crate://crates.io/jemallocator/0.3.2 \
    crate://crates.io/kernel32-sys/0.2.2 \
    crate://crates.io/lazy_static/1.4.0 \
    crate://crates.io/libc/0.2.69 \
    crate://crates.io/log/0.4.8 \
    crate://crates.io/lscolors/0.7.0 \
    crate://crates.io/maybe-uninit/2.0.0 \
    crate://crates.io/memchr/2.3.3 \
    crate://crates.io/nix/0.17.0 \
    crate://crates.io/num_cpus/1.13.0 \
    crate://crates.io/rand/0.4.6 \
    crate://crates.io/rand_core/0.3.1 \
    crate://crates.io/rand_core/0.4.2 \
    crate://crates.io/rdrand/0.4.0 \
    crate://crates.io/redox_syscall/0.1.56 \
    crate://crates.io/regex-syntax/0.6.17 \
    crate://crates.io/regex/1.3.6 \
    crate://crates.io/remove_dir_all/0.5.2 \
    crate://crates.io/same-file/1.0.6 \
    crate://crates.io/strsim/0.8.0 \
    crate://crates.io/tempdir/0.3.7 \
    crate://crates.io/term_size/0.3.1 \
    crate://crates.io/textwrap/0.11.0 \
    crate://crates.io/thread_local/1.0.1 \
    crate://crates.io/unicode-width/0.1.7 \
    crate://crates.io/vec_map/0.8.1 \
    crate://crates.io/version_check/0.9.1 \
    crate://crates.io/void/1.0.2 \
    crate://crates.io/walkdir/2.3.1 \
    crate://crates.io/winapi-build/0.1.1 \
    crate://crates.io/winapi-i686-pc-windows-gnu/0.4.0 \
    crate://crates.io/winapi-util/0.1.4 \
    crate://crates.io/winapi-x86_64-pc-windows-gnu/0.4.0 \
    crate://crates.io/winapi/0.2.8 \
    crate://crates.io/winapi/0.3.8 \
"
