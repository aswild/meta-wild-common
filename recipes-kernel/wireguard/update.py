#!/usr/bin/env python3

""" Script to update wireguard module and tools versions.
Requires at least python 3.6 """

import os, re, sys
from subprocess import check_output

class RewriteException(Exception):
    pass

def get_latest_version(url):
    """ Gets the latest version and tag rev for WireGuard.
    Gets tags matching v* and gets the latest.
    Returns a 2-tuple (version, rev) with the leading 'v' stripped from
    the version. Returns None if no matching tags found. """
    tags = {}
    output = check_output(['git', 'ls-remote', '--tags', url, 'v*'], universal_newlines=True)
    for line in output.splitlines():
        # skip dererferenced tag objects
        if line.endswith('^{}'):
            continue
        m = re.match(r'^(\S+)\s*refs/tags/v(.*)', line)
        if m:
            tags[m.group(2)] = m.group(1)

    if not tags:
        return None

    # assume WireGuard tags can be lexically sorted to get the latest,
    # might need more intelligent logic here in the future.
    ver = sorted(tags.keys())[-1]
    return (ver, tags[ver])

def update_version(file):
    """ Rewrite a wireguard bb file with a new version """
    with open(file, 'r') as fp:
        bblines = fp.read().splitlines()

    # first, find SRC_URI and old versions
    url = oldver = oldrev = ''
    for line in bblines:
        m = re.match(r'^SRC_URI\s*=\s*"(.*?)(;.*)?"$', line)
        if m:
            url = m.group(1)
            continue
        m = re.match(r'^PV\s*=\s*"(.*)"$', line)
        if m:
            oldver = m.group(1)
            continue
        m = re.match(r'^SRCREV\s*=\s*"(.*)"$', line)
        if m:
            oldrev = m.group(1)
            continue

    if not url:
        raise RewriteException(f"couldn't find SRC_URI in {url}")

    verinfo = get_latest_version(url)
    if verinfo is None:
        raise RewriteException(f"couldn't find latest version of {url}")
    ver, rev = verinfo

    # nothing to do if already up-to-date
    if oldver == ver and oldrev == rev:
        print(f'{file:23} up to date at version {ver} ({rev[:7]})')
        return

    # write to a temporary file then move it in place, so we don't accidentally
    # crash with a half-written file
    tmpfile = f'{file}.tmp'
    if os.path.exists(tmpfile):
        raise RewriteException(f"temp file {tmpfile} already exists")

    with open(tmpfile, 'w') as fp:
        for line in bblines:
            # replace lines that need to be updated
            if line.startswith('PV ='):
                fp.write(f'PV = "{ver}"\n')
            elif line.startswith('SRCREV ='):
                fp.write(f'SRCREV = "{rev}"\n')
            # otherwise update as-is
            else:
                fp.write(f'{line}\n')

    # replace original file
    os.rename(tmpfile, file)
    print(f'{file:23} updated to version {ver} ({rev[:7]})')

def main():
    ret = 0
    for name in ('module', 'tools'):
        file = f'wireguard-{name}_git.bb'
        try:
            update_version(file)
        except RewriteException as e:
            print(f'Error: {e}')
            ret = 1

    return int(ret)

if __name__ == '__main__':
    sys.exit(main())
