#!/bin/bash

die() {
    echo "FATAL: $*"
    exit 1
}
run() {
    echo "run: $*"
    "$@"
}

[[ -d $TOP ]] || die "Please set TOP first"
thisdir=$(dirname $0)
branchname=wild

while read -r dir; do
    sdir=$(readlink -e $thisdir/$dir)
    pdir="$TOP/$dir"
    if [[ ! -d "$pdir" ]]; then
        echo "ERROR: $pdir does not exist"
        continue
    fi
    if git -C $pdir show-ref --heads | grep -q " refs/heads/$branchname\$"; then
        echo "ERROR: branch $branchname already exists in $pdir"
        continue
    fi
    repo start $branchname $pdir || die "unable to create branch $branchname in $pdir"
    echo "Created branch $branchname in $pdir"
    for patch in "$sdir"/*.patch; do
        echo "Applying $(basename $patch)"
        git -C "$pdir" am -q --whitespace=nowarn "$patch" || die "failed to apply patch $patch"
    done
done < <(find "$thisdir" -mindepth 1 -type d -printf '%P\n')
