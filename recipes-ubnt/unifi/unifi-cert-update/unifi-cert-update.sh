#!/bin/bash
# Copyright (c) 2018-2020 Allen Wild
# SPDX-License-Identifier: MIT
#
# based on https://github.com/stevejenkins/unifi-linux-utils/blob/master/unifi_ssl_import.sh
# and https://source.sosdg.org/brielle/lets-encrypt-scripts

set -e

UNIFI_DIR=/usr/lib/unifi
KEYSTORE=$UNIFI_DIR/data/keystore
ROOT_CA=/etc/ssl/certs/ISRG_Root_X1.pem
PASSWORD=aircontrolenterprise

: ${JAVA_HOME:=/usr/lib/jvm/openjre-8}
KEYTOOL=$JAVA_HOME/bin/keytool

if [[ -t 1 ]]; then
    BLD=$'\033[1;37m'
    NC=$'\033[0m'
else
    BLD=
    NC=
fi

msg() {
    echo -e "${BLD}${*}${NC}"
}

vrun() {
    echo "+ $*"
    "$@"
}

pkcstmp=$(mktemp)
trap "rm -f $pkcstmp" EXIT

msg "Creating temporary PKCS12 cert"
vrun openssl pkcs12 -export -passout pass:$PASSWORD \
     -in cert.pem -inkey privkey.pem \
     -out $pkcstmp -name unifi \
     -CAfile $ROOT_CA -caname root

msg "Backing up existing keystore"
vrun cp $KEYSTORE ${KEYSTORE}.bak.$(date "+%Y%m%d%H%M%S")

msg "Removing existing cert from Unifi keystore"
vrun $KEYTOOL -delete -alias unifi -keystore $KEYSTORE \
     -deststorepass $PASSWORD || true

msg "Importing new cert into Unifi keystore"
vrun $KEYTOOL -trustcacerts -importkeystore \
     -deststorepass $PASSWORD -destkeypass $PASSWORD \
     -destkeystore $KEYSTORE -srckeystore $pkcstmp \
     -srcstoretype PKCS12 -srcstorepass $PASSWORD \
     -alias unifi

# not sure what this step does because without it Firefox is happy
# but curl and wget are unhappy. Probably links back to the root
# certs properly and FF already has those or something.
msg "Re-importing the key using ace.jar"
certdir=$PWD
pushd $UNIFI_DIR
# HACK! The unifi controller v5.9.29 chokes on the totally valid
# certs 'java.lang.IllegalArgumentException: Illegal base64 character a'
# As a workaround, remove newlines and it works. Fortunately it can read
# from pipes.
# See https://community.ubnt.com/t5/UniFi-Routing-Switching/SSL-error-when-importing-SSL-certificates/td-p/2491355
echo "+ $JAVA_HOME/bin/java -jar lib/ace.jar import_cert" \
     "$certdir/cert.pem $certdir/chain.pem $ROOT_CA"
vrun $JAVA_HOME/bin/java -jar lib/ace.jar import_cert \
                         <(tr -d '\n' <$certdir/cert.pem) \
                         <(tr -d '\n' <$certdir/chain.pem) \
                         <(tr -d '\n' <$ROOT_CA)

msg "\nDone! You should restart the UniFi controller now"
