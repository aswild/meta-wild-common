# Python build scripts need to explicitly #! python2 to build on systems where python3 is default

def is_python3_default():
    import subprocess
    return 'Python 3' in str(subprocess.check_output(['/usr/bin/env', 'python', '--version']))

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "${@'file://1000-explicit-python2.patch' if is_python3_default() else ''}"
