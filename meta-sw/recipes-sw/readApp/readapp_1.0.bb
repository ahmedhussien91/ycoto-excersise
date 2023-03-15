DESCRIPTION = "The software is for testing, it will only print what is passed on stdin, it also has another thread that always prints" 
HOMEPAGE = "https://github.com/ahmedhussien91/posix-app"
# package category (e.g. console/utils), check http://www.embeddedlinux.org.cn/OEManual/section_variable.html
SECTION = "console" 
LICENSE = "MIT"

SRC_URI = "git://github.com/ahmedhussien91/posix-app.git;protcol=https;branch=main"
SRCREV = "77a7457f9ec031b7a0ca2c3862fcae62811c9caa"

S = "${WORKDIR}/git"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
# LIC_FILES_CHKSUM = "file://${WORKDIR}/MIT;md5=5c94767cedb5d6987c902ac850ded2c6" use if the license if on git  

inherit cmake

