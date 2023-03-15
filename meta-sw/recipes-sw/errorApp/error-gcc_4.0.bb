DESCRIPTION="test custom application"
LICENSE="CLOSED"

SRC_URI ="file://src"
DEPENDS = "error-functions"
S="${WORKDIR}/src"


TARGET_CC_ARCH += "${LDFLAGS}"

# compile with shared libraries
do_compile(){
	$CC -o erro-app main.c -L${STAGING_LIBDIR} -lgetnum -lerrorfun
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 erro-app ${D}${bindir}
}
