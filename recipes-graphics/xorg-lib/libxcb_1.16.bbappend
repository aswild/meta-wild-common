# the oe-core recipe sets PYTHON = "python3", but that pulls in the system python3
# and attempts to use the Yocto python modules with it. When there's a version mismatch,
# things go badly.
# python3native.bbclass actually sets paths correctly to use Yocto's python.
inherit python3native
