# SSH needs xauth to be able to do X11 forwarding
RRECOMMENDS:${PN}-sshd += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xauth', '', d)}"
