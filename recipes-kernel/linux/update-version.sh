#!/bin/bash

if [[ $1 == -c || $1 == --commit ]]; then
    COMMIT=y
    shift
fi

kvers=${1:-4.19}
commitmsg='linux-mainline: bump to '
bbfiles_commit=()

for kver in $kvers; do
    linux_version=$(./get-latest-version.py $kver)

    bbfile=linux-mainline_${kver}.bb
    oldversion=$(perl -ne 'print if s/^LINUX_VERSION\s*=\s*"(.*)"/\1/' $bbfile)
    if [[ $oldversion != $linux_version ]]; then
        commitmsg+="$linux_version, "
        bbfiles_commit+=($bbfile)
    fi

    sed -i -e "s/^LINUX_VERSION.*/LINUX_VERSION = \"${linux_version}\"/" \
           -e "s/^SRCREV.*/SRCREV = \"${rev}\"/" \
           $bbfile

    echo "set version to ${linux_version}"
done

if [[ $COMMIT == y && -n $bbfiles_commit ]]; then
    commitmsg="${commitmsg%, }"
    git commit -m "$commitmsg"  "${bbfiles_commit[@]}"
fi
