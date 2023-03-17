include custom-image.inc

IMAGE_INSTALL_append = " openssh \
						 error-gcc \
						 read-app \
						 "
IMAGE_FSTYPES_append = " tar"

# PREFERRED_VERSION_error-gcc = "1.0"