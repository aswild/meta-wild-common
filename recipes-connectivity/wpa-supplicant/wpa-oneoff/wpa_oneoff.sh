#!/bin/bash
#
# Copyright 2018 Allen Wild <allenwild93@gmail.com>
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.
#
# wpa_oneoff - a simple script to run wpa_supplicant in the foreground
# Slightly unsafe because the passphrase is passed via the command line and
# therefore visible in ps output.
# Depends on wpa_supplicant and wpa_passphrase

usage() {
    cat <<EOF
Usage: $0 [-i INTERFACE] SSID PASSPHRASE [ARGS...]

    This script should be run as root. If run as a non-root user, it will be
    re-exec'd automatically with sudo.

    If not specified, the interface is guessed as the first interface in
    /sys/class/net/ matching ^wl

    wpa_passphrase is called with SSID and PASSPHRASE to create a temporary
    config file, which is passed on to wpa_supplicant.

    Any additional ARGS are passed on to wpa_supplicant itself.
EOF
}

die() {
    echo "$*" >&2
    exit 1
}

# re-run with sudo
if [[ $EUID != 0 ]]; then
    exec sudo "$0" "$@"
fi

while getopts 'hi:' opt; do
    case $opt in
        h)
            usage
            exit 0
            ;;
        i)
            intf=$OPTARG
            ;;
        \?)
            usage
            exit 1
            ;;
    esac
done
shift $(($OPTIND - 1))

if (( $# < 2 )); then
    echo "Not enough arguments"
    usage
    exit 1
fi

ssid="$1"
pass="$2"
shift 2

[[ -n $intf ]] || intf=$(ls /sys/class/net | sed -n '/^wl/{p;q}')
[[ -n $intf ]] || die "No interface was specified and no default could be found"
[[ -d /sys/class/net/$intf ]] || die "Interface $intf doesn't exist"

conffile=$(mktemp)
trap "rm -f $conffile" EXIT

wpa_passphrase "$ssid" "$pass" >$conffile
[[ -s $conffile ]] || die "Unable to create wpa_supplicant conf file"

wpa_supplicant -i$intf -c$conffile "$@"
