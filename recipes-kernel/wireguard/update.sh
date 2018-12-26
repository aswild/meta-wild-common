#!/bin/bash

set -e

url='https://github.com/WireGuard/WireGuard'
tag="$(git ls-remote --tags $url | grep -v '\^{}$' | awk -F/ '{print $NF}' | sort | tail -n1)"

sed -i "s/^TAG.*/TAG = \"$tag\"/" wireguard.inc
echo "Updated to tag $tag"
