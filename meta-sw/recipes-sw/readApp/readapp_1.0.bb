DESCRIPTION = "The software is for testing, it will only print what is passed on stdin, it also has another thread that always prints" 
HOMEPAGE = "https://github.com/ahmedhussien91/posix-app"
# package category (e.g. console/utils), check http://www.embeddedlinux.org.cn/OEManual/section_variable.html
SECTION = "console" 
LICENSE = "MIT"

SRC_URI = "git://github.com/ahmedhussien91/posix-app.git;protcol=https;branch=main"
SRCREV = "693c11bd4844e85f795bd4b960f842a7bdf8a75d"

S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
# LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
# LIC_FILES_CHKSUM = "file://${WORKDIR}/MIT;;md5=0835ade698e0bcf8506ecda2f7b4f302" use if the license if on git  

inherit cmake