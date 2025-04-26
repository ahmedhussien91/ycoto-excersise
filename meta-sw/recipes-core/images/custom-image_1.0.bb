SUMMARY = "A console-only image that fully supports the target device \
hardware."

inherit core-image

LICENSE = "MIT"

IMAGE_BOOT_FILES:raspberrypi4-64 += " bcm2711-rpi-4-b.dtb overlays/*.dtb"
IMAGE_BOOT_FILES:raspberrypi4-64 += " Image"
IMAGE_BOOT_FILES:beaglebone += " zImage"

IMAGE_INSTALL:append = " nfs-utils-client"
IMAGE_INSTALL:append = " openssh \
			error-gcc \
			readapp \
			vsomeip \
			"
# Add a few extra packages to the image
IMAGE_INSTALL:append = " \
    packagegroup-core-ssh-openssh \
    packagegroup-core-tools-debug \
    packagegroup-core-tools-profile \
    packagegroup-core-tools-testapps \
    packagegroup-core-x11-base \
"
IMAGE_INSTALL:append = " xkeyboard-config"
IMAGE_INSTALL:append = " cinematicexperience"
IMAGE_INSTALL:append = " x11vnc"
# IMAGE_INSTALL:append = " qt5-qmake qtbase qtdeclarative qtbase-plugins qtdeclarative-plugins qttools"

IMAGE_FSTYPES:append = " tar.gz ext4 wic tar"
INITRAMFS_IMAGE:beaglebone = "initramfs-image"
INITRAMFS_IMAGE_BUNDLE:beaglebone = "1"
# BOOT_SPACE_ALIGNED = "131072" # 128MB 

# you can specify package
# IMAGE_INSTALL_append = "error-gcc-dev"
# PREFERRED_VERSION_error-gcc = "1.0"
