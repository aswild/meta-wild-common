#!/bin/bash

die() {
    echo "FATAL: $*"
    exit 1
}
run() {
    echo "run: $*"
    "$@"
}

gettop() {
    local repodir=$PWD
    while true; do
        if [[ -d $repodir/.repo ]]; then
            echo "Found TOP dir $repodir" >&2
            echo "$repodir"
            return 0
        elif [[ $repodir == / ]]; then
            echo "Unable to set TOP automatically: No .repo found" >&2
            return 1
        fi
        repodir=$(dirname $repodir)
    done
}

[[ -d $TOP ]] || TOP="$(gettop)" || die "Please set TOP first"
thisdir=$(dirname $0)
branchname=wild

for dir in $(find "$thisdir" -mindepth 1 -type d -printf '%P\n'); do
    sdir=$(readlink -e $thisdir/$dir)
    pdir="$TOP/$dir"
    if [[ ! -d "$pdir" ]]; then
        echo "ERROR: $pdir does not exist"
        continue
    fi
    if git -C $pdir show-ref --heads | grep -q " refs/heads/$branchname\$"; then
        echo "WARNING: branch $branchname already exists in $pdir"
        if [[ -n "$(git -C $pdir status --porcelain)" ]]; then
            echo "ERROR: uncommited changes in $pdir. Skipping..."
            continue
        fi
        echo -n "reset branch $branchname? (y/N) "
        read answer
        if [[ $answer == [yY]* ]]; then
            repo sync -ld $pdir
            git -C $pdir branch -D $branchname
        else
            echo "Skipping $pdir..."
            continue
        fi
    fi
    repo start $branchname $pdir || die "unable to create branch $branchname in $pdir"
    echo "Created branch $branchname in $pdir"
    for patch in "$sdir"/*.patch; do
        echo "Applying $(basename $patch)"
        git -C "$pdir" am -q --whitespace=nowarn "$patch" || die "failed to apply patch $patch"
    done
done
