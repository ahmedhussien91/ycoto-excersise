DESCRIPTION = "program that generates an error looking for file aa"
LICENSE = "CLOSED"

SRC_URI = "file://src"
S="${WORKDIR}/src"
LDFLAGS=""
DEPENDS = "get-num"

do_compile(){
	$CC -c -g -Wall error_functions.c -I${STAGING_INCDIR}
	$AR -crv liberrorfun.a error_functions.o
}

do_install() {
	install -d ${D}${libdir}
	install -m 0755 liberrorfun.a ${D}${libdir}
    install -m 0755 ${WORKDIR}/src/error_functions.h ${D}${includedir}
}