# Octoprint recipe

PROVIDES = "octoprint"
RPROVIDES_${PN} = "octoprint"

# setup.py runs some pip commands
DEPENDS += "python3-pip-native"

# pipoe doesn't figure out all the correct dependencies, mainly python3 specific ones are excluded
RDEPENDS_${PN} += " \
    python3-babel \
    python3-blinker \
    python3-cachelib \
    python3-certifi \
    python3-charset-normalizer \
    python3-click \
    python3-colorlog \
    python3-emoji \
    python3-feedparser \
    python3-filetype \
    python3-flask \
    python3-flask-assets \
    python3-flask-babel \
    python3-flask-login \
    python3-future \
    python3-idna \
    python3-ifaddr \
    python3-immutabledict \
    python3-itsdangerous \
    python3-jinja2 \
    python3-markdown \
    python3-markupsafe \
    python3-netaddr \
    python3-netifaces \
    python3-octoprint-filecheck \
    python3-octoprint-firmwarecheck \
    python3-octoprint-pisupport \
    python3-pathtools \
    python3-pathvalidate \
    python3-pkginfo \
    python3-psutil \
    python3-pylru \
    python3-pyserial \
    python3-pytz \
    python3-pyyaml \
    python3-regex \
    python3-requests \
    python3-sarge \
    python3-semantic-version \
    python3-sentry-sdk \
    python3-sgmllib3k \
    python3-six \
    python3-tornado \
    python3-unidecode \
    python3-urllib3 \
    python3-watchdog \
    python3-webassets \
    python3-websocket-client \
    python3-werkzeug \
    python3-wrapt \
    python3-zeroconf \
    python3-zipstream-new \
"

# octoprint also needs pkg_resources from setuptools
#RDEPENDS_${PN} += "python3-pkg-resources"
RDEPENDS_${PN} += "python3-setuptools python3-pip"
