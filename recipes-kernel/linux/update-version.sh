#!/bin/bash

MAJVER=4.14

ver=$(./get-latest-version.py $MAJVER)

sed -i "s|^LINUX_VERSION.*|LINUX_VERSION = \"$ver\"|" linux-mainline_${MAJVER}.bb
