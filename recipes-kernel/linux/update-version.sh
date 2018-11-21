#!/bin/bash


for MAJVER in 4.14 4.19; do
    ver=$(./get-latest-version.py $MAJVER)
    echo "$ver"
    sed -i "s|^LINUX_VERSION.*|LINUX_VERSION = \"$ver\"|" linux-mainline_${MAJVER}.bb
done
