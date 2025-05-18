DESCRIPTION = "The software is for testing, it will only print what is passed on stdin, it also has another thread that always prints" 
HOMEPAGE = "https://github.com/ahmedhussien91/posix-app"
# package category (e.g. console/utils), check http://www.embeddedlinux.org.cn/OEManual/section_variable.html
SECTION = "console" 
LICENSE = "MIT"

DEPENDS = "qtbase qtsensors"

SRC_URI = "git://github.com/ahmedhussien91/Qt-app.git;protcol=https;branch=main"
SRCREV = "a0be192bb48437a918bbf139cb698eda55c550dd"
SRC_URI[sha256sum] = "0f6eb0be1da288a9b7e4cabfab1ed59900c67ad8d078a526be42e5d4803ae65f"
S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
# LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
# LIC_FILES_CHKSUM = "file://${WORKDIR}/MIT;;md5=0835ade698e0bcf8506ecda2f7b4f302" use if the license if on git  

inherit qmake5