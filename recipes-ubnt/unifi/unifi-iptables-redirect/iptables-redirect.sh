#!/bin/sh
# Copyright (c) 2018 Allen Wild
# SPDX-License-Identifier: MIT

case "$1" in
    start)  action='-A' ;;
    stop)   action='-D' ;;
    *)
        echo >&2 "Usage: $0 {start|stop}"
        exit 1
esac

for port in 80:8080 443:8443; do
    iptables -t nat $action PREROUTING -p tcp --dport ${port%:*} -j REDIRECT --to-port ${port#*:}
    iptables -t nat $action OUTPUT -o lo -p tcp --dport ${port%:*} -j REDIRECT --to-port ${port#*:}
done
