# Enable systemd
DISTRO_FEATURES_BACKFILL_CONSIDERED_append = " sysvinit"
DISTRO_FEATURES_append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"
VIRTUAL-RUNTIME_login_manager = "systemd"

# locales (which I think are needed for systemd stuff to build right)
GLIBC_GENERATE_LOCALES = "en_US.UTF-8"
IMAGE_LINGUAS = "en-us"
