DESCRIPTION = "Common packages to install"
LICENSE = "MIT"

inherit packagegroup

PACKAGES += "${PN}-base"
RDEPENDS_${PN}-base = " \
    packagegroup-core-base-utils \
    packagegroup-core-full-cmdline-libs \
    packagegroup-core-full-cmdline-utils \
    packagegroup-core-full-cmdline-sys-services \
    curl \
    dhcp-client \
    glibc-utils \
    iproute2 \
    iputils \
    openssh \
    openssh-sshd \
    openssh-sftp \
    openssh-sftp-server \
    shadow \
    sysstat \
    tzdata \
    vim \
    wget \
"

PACKAGES += "${PN}-core"
RDEPENDS_${PN}-core = " \
    ${PN}-base \
    htop \
    make \
    the-silver-searcher \
    the-silver-searcher-zsh-completion \
    tmux \
    usbutils \
    wild-linuxfiles \
    wireless-regdb-static \
    zsh \
"

PACKAGES += "${PN}-python3"
RDEPENDS_${PN}-python3 = " \
    python3 \
    python3-modules \
    python3-pip \
    python3-setuptools \
    python3-wheel \
"

PACKAGES += "${PN}-devtools"
RDEPENDS_${PN}-devtools = " \
    python \
    python-modules \
    python3 \
    python3-modules \
    squashfs-tools \
"

PACKAGES += "${PN}-utils"
RDEPENDS_${PN}-utils = " \
    dosfstools \
    e2fsprogs \
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    e2fsprogs-tune2fs \
    git \
    git-perltools \
    ldd \
    rsync \
    sshfs-fuse \
    tree \
    util-linux \
    util-linux-blkid \
"

PACKAGES += "${PN}-network-utils"
RDEPENDS_${PN}-network-utils = " \
    bridge-utils \
    dhcpcd \
    ebtables \
    iptables \
    tcpdump \
"

PACKAGES += "${PN}-full-cmdline"
RDEPENDS_${PN}-full-cmdline = " \
    ${PN}-core \
    ${PN}-devtools \
    ${PN}-utils \
"
