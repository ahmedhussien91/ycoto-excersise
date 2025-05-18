DESCRIPTION = "My Qt App"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "file://. \
	file://qt-env.sh"

S = "${WORKDIR}"

inherit cmake cmake_qt5

DEPENDS += "qtbase qtdeclarative"

ROOTFS_POSTPROCESS_COMMAND:append = " fix_fonts_symlink; "

fix_fonts_symlink () {
	ln -sf /usr/share/fonts/ttf /usr/lib/fonts
}

do_install:append() {
    install -d ${D}${sysconfdir}/profile.d
    install -m 0755 ${WORKDIR}/qt-env.sh ${D}${sysconfdir}/profile.d/qt-env.sh
}
