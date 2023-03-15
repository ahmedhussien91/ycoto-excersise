DESCRIPTION = "program that generates an error looking for file aa"
LICENSE = "CLOSED"

SRC_URI = "file://src"
S="${WORKDIR}/src"
LDFLAGS=""
DEPENDS = "get-num"
RDEPENDS_${PN} = "get-num"

do_compile(){
	$CC -c -g -Wall error_functions.c -I${STAGING_INCDIR}
	$CC -shared -Wl,-soname,liberrorfun.so -o liberrorfun.so.${PV} error_functions.o
}

do_install() {
	install -d ${D}${libdir}
	install -d ${D}${includedir}
	oe_soinstall liberrorfun.so.${PV} ${D}${libdir}
    install -m 0755 ${WORKDIR}/src/error_functions.h ${D}${includedir}
}