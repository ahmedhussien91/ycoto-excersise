include custom-image.inc

IMAGE_INSTALL_append = " openssh \
						 error-gcc \
						 read-app \
						 "
IMAGE_FSTYPES_append = " tar"

# PREFERRED_VERSION_error-gcc = "1.0"

# add systemd support
# add systemd to distro features
DISTRO_FEATURES_append = " systemd"   
# remove the systemv from distro features
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit" 
# assign init_manager as systemd
VIRTUAL-RUNTIME_init_manager = "systemd" 
# assign initscripts to systemd-compact-units, allow us to use `systemctl`
VIRTUAL-RUNTIME_initscripts = "systemd-compat-units" 