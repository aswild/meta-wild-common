# Make systemd-nsresourced optional. We can't append to the weak-default in oe-core,
# so it will be disabled by default and needs to be explicitly re-enabled.
PACKAGECONFIG[nsresourced] = "-Dnsresourced=true,-Dnsresourced=false"
