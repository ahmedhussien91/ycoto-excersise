DESCRIPTION = "program that generates an error looking for file aa"
LICENSE = "CLOSED"

SRC_URI = "file://src"
S="${WORKDIR}/src"
LDFLAGS=""

do_compile(){
	$CC -c -g -Wall error_functions.c 
	$CC -c -g -Wall get_num.c 
	$CC -c -g -Wall main.c 
	$CC -o test error_functions.o get_num.o main.o	
}

do_install () {
	mkdir -p ${D}${bindir}
	cp ${S}/test ${D}${bindir}
	chmod -R 0777 ${D}
}

