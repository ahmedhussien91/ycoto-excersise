DESCRIPTION = "program that generates an error looking for file aa"
LICENSE = "CLOSED"

SRC_URI = "file://src"
S="${WORKDIR}/src"
LDFLAGS=""

do_compile(){
	$CC -c -g -Wall get_num.c 
	$AR -crv libgetnum.a get_num.o
}

do_install() {
	install -d ${D}${libdir}
    install -d ${D}${includedir}
	install -m 0755 libgetnum.a ${D}${libdir}
    install -m 0755 ${WORKDIR}/src/get_num.h ${D}${includedir}
}