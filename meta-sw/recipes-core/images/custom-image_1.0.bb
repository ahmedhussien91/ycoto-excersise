SUMMARY = "A console-only image that fully supports the target device \
hardware."

inherit core-image

IMAGE_FEATURES += "splash"
LICENSE = "MIT"


IMAGE_INSTALL_append = " openssh \
			error-gcc \
			readapp \
			"

IMAGE_FSTYPES_append = " wic tar"

# you can specify package
# IMAGE_INSTALL_append = "error-gcc-dev"
# PREFERRED_VERSION_error-gcc = "1.0"
