#!/usr/bin/env python

from __future__ import print_function
import re, subprocess, sys

if len(sys.argv) > 1:
    majver = sys.argv[1]
else:
    majver = "4.14"

url = "https://kernel.googlesource.com/pub/scm/linux/kernel/git/stable/linux-stable.git"
regex = r"v(%s\.\d+)$"%majver.replace(".", "\\.")

versions = []
output = subprocess.check_output(["git", "ls-remote", "--tags", url], universal_newlines=True)
for line in output.splitlines():
    m = re.search(regex, line)
    if m:
        versions.append(tuple(int(v) for v in m.group(1).split(".")))

print(".".join(str(x) for x in sorted(versions)[-1]))
