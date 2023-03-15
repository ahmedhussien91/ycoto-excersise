DESCRIPTION = "program that generates an error looking for file aa"
LICENSE = "CLOSED"

SRC_URI = "file://src"
S="${WORKDIR}/src"
LDFLAGS=""

do_compile(){
	$CC -fpic -c -g -Wall get_num.c 
	$CC -shared -Wl,-soname,libgetnum.so -o libgetnum.so.${PV} get_num.o
}

do_install() {
	install -d ${D}${libdir}
    install -d ${D}${includedir}
	oe_soinstall libgetnum.so.${PV} ${D}${libdir}
    install -m 0755 ${WORKDIR}/src/get_num.h ${D}${includedir}
}