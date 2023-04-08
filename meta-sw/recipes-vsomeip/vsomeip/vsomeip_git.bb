# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
#   LICENSE_boost
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9741c346eef56131163e13b9db1241b3 \
                    file://LICENSE_boost;md5=55a0dc970982f51bfe9b4c6ae0a68d1e"

SRC_URI = "git://github.com/COVESA/vsomeip.git;protocol=https;branch=master \
           file://0001-fix-compilation-issue.patch \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "331b71e6ea0241f51e80f9ba5c3fbe917c731dba"

S = "${WORKDIR}/git"

# NOTE: unable to map the following CMake package dependencies: Doxygen benchmark
# NOTE: unable to map the following pkg-config dependencies: libsystemd
#       (this is based on recipes that have previously been built and packaged)
DEPENDS = "boost"

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = ""


# add files to vsomeip package 
FILES_${PN} += "/usr/etc \
  /usr/etc/vsomeip \
  /usr/etc/vsomeip/vsomeip-udp-service.json \
  /usr/etc/vsomeip/vsomeip-local.json \
  /usr/etc/vsomeip/vsomeip.json \
  /usr/etc/vsomeip/vsomeip-tcp-client.json \
  /usr/etc/vsomeip/vsomeip-tcp-service.json \
  /usr/etc/vsomeip/vsomeip-udp-client.json \
"
