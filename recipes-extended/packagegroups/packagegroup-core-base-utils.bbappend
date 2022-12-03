# remove some inetutils packages, they're duplicated by iputils and net-tools
RDEPENDS:${PN}:remove = "inetutils inetutils-ping"

# add some other inetutils packages I do want
RDEPENDS:${PN}:append = " inetutils-ftp"
