# class to package zsh completions. based on bash-completion.bbclass

PACKAGES += "${PN}-zsh-completion"
FILES:${PN}-zsh-completion = "${datadir}/zsh/site-functions"
RRECOMMENDS:${PN}-zsh-completion:class-target = "zsh"
