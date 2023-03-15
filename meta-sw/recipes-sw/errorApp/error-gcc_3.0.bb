DESCRIPTION="test custom application"
LICENSE="CLOSED"

SRC_URI ="file://src"
DEPENDS = "error-functions"
S="${WORKDIR}/src"

TARGET_CC_ARCH += "${LDFLAGS}"

# compile with STATIC libraries
do_compile(){
	$CC -o erro-app main.c ${STAGING_LIBDIR}/libgetnum.a ${STAGING_LIBDIR}/liberrorfun.a
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 erro-app ${D}${bindir}
}
