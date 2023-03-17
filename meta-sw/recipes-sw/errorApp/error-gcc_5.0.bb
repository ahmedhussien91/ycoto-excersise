DESCRIPTION="test custom application"
LICENSE="CLOSED"

SRC_URI ="file://src"
DEPENDS = "error-functions"
S="${WORKDIR}/src"

inherit  update-rc.d systemd

TARGET_CC_ARCH += "${LDFLAGS}"

# compile with shared libraries, including `STAGING_LIBDIR`
do_compile(){
	$CC -o erro-app main.c -L${STAGING_LIBDIR} -lgetnum -lerrorfun
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 erro-app ${D}${bindir}
	# install systemv init scripts
	install -d ${D}${sysconfdir}/init.d
	install -c -m 755 error-app.sh ${D}${sysconfdir}/init.d/error-app.sh
	# install systemd init scripts
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 error-app.service ${D}${systemd_unitdir}/system
}

INITSCRIPT_NAME = "error-app.sh"
INITSCRIPT_PARAMS = "defaults"
#INITSCRIPT_PARAMS = "start 99 5 2 . stop 20 0 1 6 ."
SYSTEMD_SERVICE = "error-app.service"