DESCRIPTION="test custom application"
LICENSE="CLOSED"

SRC_URI ="file://src"

S="${WORKDIR}/src"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile_append(){
	echo "working directory: $PWD"
	echo "make is called by default......."
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 test ${D}${bindir}
}
