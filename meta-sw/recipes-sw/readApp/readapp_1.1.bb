DESCRIPTION = "The software is for testing, it will only print what is passed on stdin, it also has another thread that always prints" 
HOMEPAGE = "https://github.com/ahmedhussien91/posix-app"
# package category (e.g. console/utils), check http://www.embeddedlinux.org.cn/OEManual/section_variable.html
SECTION = "console" 
LICENSE = "MIT"

SRC_URI = "git://github.com/ahmedhussien91/posix-app.git;protcol=https;branch=main"
SRCREV = "cf5efd3ffc709cc37b1db5abc44144329b0334c9"

S = "${WORKDIR}/git"

# MIT License is provided in repo 
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"  

inherit cmake

# to add definitions to cmake, "HASHDEFINE=1" we use this variable 
EXTRA_OECMAKE = " -DHASHDEFINE=1"
