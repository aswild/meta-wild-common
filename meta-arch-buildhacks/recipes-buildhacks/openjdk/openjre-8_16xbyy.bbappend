CFLAGS_append = " -Wno-error=stringop-overflow -Wno-error=return-type"

EXTRA_OECONF_remove = "--with-tools-dir=${STAGING_DIR_NATIVE}"
EXTRA_OECONF_remove = "--with-boot-jdk=${STAGING_LIBDIR_NATIVE}/jvm/openjdk-8-native"
EXTRA_OECONF_remove = "--with-cacerts-file=${STAGING_LIBDIR_NATIVE}/jvm/openjdk-8-native/jre/lib/security/cacerts"

EXTRA_OECONF_append = " --with-tools-dir=/usr \
                        --with-boot-jdk=/usr/lib/jvm/java-8-openjdk \
                        --with-cacerts-file=/usr/lib/jvm/java-8-openjdk/jre/lib/security/cacerts"
