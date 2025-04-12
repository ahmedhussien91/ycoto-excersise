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
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9741c346eef56131163e13b9db1241b3"
SRC_URI = "git://github.com/COVESA/vsomeip.git;protocol=https;branch=master "
#           file://0001-fix-compilation-issue.patch "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "1df6987710999c41e37499af09f31c08a1eb5982"

S = "${WORKDIR}/git"

# NOTE: unable to map the following CMake package dependencies: Doxygen benchmark
# NOTE: unable to map the following pkg-config dependencies: libsystemd
#       (this is based on recipes that have previously been built and packaged)
DEPENDS = "boost"

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = ""


# add files to vsomeip package 
FILES:${PN} += "/usr/etc \
  /usr/etc/vsomeip \
  /usr/etc/vsomeip/vsomeip-udp-service.json \
  /usr/etc/vsomeip/vsomeip-local.json \
  /usr/etc/vsomeip/vsomeip.json \
  /usr/etc/vsomeip/vsomeip-tcp-client.json \
  /usr/etc/vsomeip/vsomeip-tcp-service.json \
  /usr/etc/vsomeip/vsomeip-udp-client.json \
  ${bindir}/ \
  ${libdir}/ \
  ${sysconfdir}/ \
"
