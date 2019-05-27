# remove some inetutils packages, they're duplicated by iputils and net-tools
RDEPENDS_${PN}_remove = "inetutils inetutils-ping"

# add some other inetutils packages I do want
RDEPENDS_${PN}_append = " inetutils-ftp"
