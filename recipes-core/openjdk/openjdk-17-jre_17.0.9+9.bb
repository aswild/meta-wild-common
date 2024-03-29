# SPDX-License-Identifier: GPL-2.0-or-later
# Copyright (C) 2023 Lucimber UG
# Modified by Allen Wild
#
# Adapted from the recipes in https://github.com/lucimber/meta-openjdk-temurin

SUMMARY = "Prebuilt OpenJDK JRE for Java 17 offered by Adoptium."
HOMEPAGE = "https://adoptium.net"
LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-with-classpath-exception;md5=6133e6794362eff6641708cfcc075b80"

COMPATIBLE_HOST = "(x86_64|arm|aarch64).*-linux"

X11_RDEPENDS = " \
  libx11 (>= 1.7) \
  libxext (>= 1.3) \
  libxi (>= 1.8) \
  libxrender (>= 0.9) \
  libxtst (>= 1.2) \
"

JVM_CHECKSUM:aarch64 = "05b192f81ed478178ba953a2a779b67fc5a810acadb633ad69f8c4412399edb8"
JVM_RDEPENDS:aarch64 = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.11) \
  glibc (>= 2.17) \
  zlib (>= 1.2) \
  ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '${X11_RDEPENDS}', '', d)} \
"
JVM_CHECKSUM:arm = "5ae1f8cae358e41083a6b44f53c6f0daeb657f83c293da6c8733f68278e13703"
JVM_RDEPENDS:arm = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.11) \
  glibc (>= 2.17) \
  libatomic (>= 1.0) \
  zlib (>= 1.2) \
  ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '${X11_RDEPENDS}', '', d)} \
"
JVM_CHECKSUM:x86-64 = "c37f729200b572884b8f8e157852c739be728d61d9a1da0f920104876d324733"
JVM_RDEPENDS:x86-64 = " \
  alsa-lib (>= 0.9) \
  freetype (>= 2.11) \
  glibc (>= 2.17) \
  zlib (>= 1.2) \
  ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '${X11_RDEPENDS}', '', d)} \
"

RDEPENDS:${PN} = "${JVM_RDEPENDS}"

API_RELEASE_NAME = "jdk-${PV}"
API_OS = "linux"
API_ARCH:aarch64 = "aarch64"
API_ARCH:arm = "arm"
API_ARCH:x86-64 = "x64"
API_IMAGE_TYPE = "jre"
API_JVM_IMPL = "hotspot"
API_HEAP_SIZE ?= "normal"
API_VENDOR = "eclipse"

SRC_URI = "https://api.adoptium.net/v3/binary/version/${API_RELEASE_NAME}/${API_OS}/${API_ARCH}/${API_IMAGE_TYPE}/${API_JVM_IMPL}/${API_HEAP_SIZE}/${API_VENDOR};downloadfilename=${BPN}-${API_ARCH}-${PV}.tar.gz;subdir=${BPN}-${PV};striplevel=1"
SRC_URI[sha256sum] = "${JVM_CHECKSUM}"

libdir_jre = "${libdir}/jvm/openjdk-17-jre"

# Prevent the packaging task from stripping out
# debugging symbols, since there are none.
INSANE_SKIP:${PN} = "ldflags"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# Package unversioned libraries
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

# Ignore QA Issue: non -dev/-dbg/nativesdk- package
INSANE_SKIP:${PN}:append = " dev-so"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install() {
  install -d ${D}${libdir_jre}
  cp -R --no-dereference --preserve=mode,links -v ${S}/* ${D}${libdir_jre}

  if [ "${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)}" != "x11" ]; then
      bbnote "X11 disabled, removing native libraries"
      for name in splashscreen awt_xawt jawt; do
          rm -v ${D}${libdir_jre}/lib/lib${name}.so
      done
  fi
}

RPROVIDES:${PN} = "java2-runtime"
FILES:${PN} = "${libdir_jre}"

inherit update-alternatives
ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE:${PN} = "java keytool"
ALTERNATIVE_LINK_NAME[java] = "${bindir}/java"
ALTERNATIVE_TARGET[java] = "${libdir_jre}/bin/java"
ALTERNATIVE_LINK_NAME[keytool] = "${bindir}/keytool"
ALTERNATIVE_TARGET[keytool] = "${libdir_jre}/bin/keytool"
