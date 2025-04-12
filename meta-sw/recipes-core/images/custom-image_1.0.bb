SUMMARY = "A console-only image that fully supports the target device \
hardware."

inherit core-image

LICENSE = "MIT"

IMAGE_BOOT_FILES += "bcm2711-rpi-4-b.dtb overlays/*.dtb"
IMAGE_BOOT_FILES:raspberrypi4-64 += "Image"
IMAGE_BOOT_FILES:beaglebone += "zImage" 

IMAGE_INSTALL:append = " openssh \
			error-gcc \
			readapp \
			vsomeip \
			"

IMAGE_FSTYPES:append = " wic tar"
BOOT_SPACE_ALIGNED = "128000"

# you can specify package
# IMAGE_INSTALL_append = "error-gcc-dev"
# PREFERRED_VERSION_error-gcc = "1.0"
