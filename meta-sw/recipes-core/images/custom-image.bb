SUMMARY = "A console-only image that fully supports the target device \
hardware."

IMAGE_FEATURES += "splash"

LICENSE = "MIT"

inherit core-image


IMAGE_INSTALL_append = "openssh \
			error-gcc \
			readapp \
			"

IMAGE_FSTYPES_append = " tar"

# you can specify package
# IMAGE_INSTALL_append = "error-gcc-dev"
# PREFERRED_VERSION_error-gcc = "1.0"
