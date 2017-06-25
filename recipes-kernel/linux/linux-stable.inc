SUMMARY = "Mainline Linux Kernel (stable branch)"
SECTION = "kernel"
LICENSE = "GPLv2"

require recipes-kernel/linux/linux-yocto.inc

def get_linux_rev(d):
    lv = d.getVar('LINUX_VERSION')
    if not lv:
        lv = d.getVar('PV')
    lvs = lv.split('.')
    major = lvs[0]
    if major == '3' or major == '4':
        minor = lvs[1]
    else:
        # 2.6.x.y
        minor = lvs[2] + '.' + lvs[3]
    branch = 'linux-{}.{}.y'.format(major, minor)
    tag = 'v{}'.format(lv)
    return (branch, tag)

LINUX_BRANCH ?= "${@get_linux_rev(d)[0]}"
LINUX_TAG ?= "${@get_linux_rev(d)[1]}"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=${LINUX_BRANCH};tag=${LINUX_TAG};protocol=git;name=machine"

PV = "${LINUX_VERSION}+git${SRCPV}"
