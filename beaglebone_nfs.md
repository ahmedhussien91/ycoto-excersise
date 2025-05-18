# NFS 

### copying output of yocto

 ```sh
 sudo rm -rf /srv/nfs4/bb_sysv/* && sudo tar -xzvf bb-build-sysv/deploy-ti/images/beaglebone/custom-image-beaglebone.rootfs.tar.gz -C /srv/nfs4/bb_sysv/
 sudo cp bb-build-sysv/deploy-ti/images/beaglebone/zImage bb-build-sysv/deploy-ti/images/beaglebone/am335x-boneblack.dtb /srv/tftp/
 ```

#### wic

```sh
sudo apt install kpartx 
sudo kpartx -av bb-build-sysv/deploy-ti/images/beaglebone/custom-image-beaglebone.rootfs.wic # loop device
sudo mkdir -p /mnt/wic_root
LOOP_DEVICE=$(sudo kpartx -av bb-build-sysv/deploy-ti/images/beaglebone/custom-image-beaglebone.rootfs.wic | grep -o 'loop[0-9]*p2' | head -n 1)
sudo mount /dev/mapper/$LOOP_DEVICE /mnt/wic_root # dynamically determine loop device
sudo cp -a /mnt/wic_root/* /srv/nfs4/bb_sysv/
```



### setting up the bootloader 

```sh
setenv bootfile zImage # set image name
setenv fdtfile am335x-boneblack.dtb # set dtb name
dhcp # Initiates a DHCP request to obtain network configuration
setenv serverip 192.168.1.11
setenv ipaddr 192.168.1.107
tftpboot ${fdtaddr} ${fdtfile}
tftpboot ${loadaddr} ${bootfile}
bootz ${loadaddr} - ${fdtaddr}
setenv bootargs "console=ttyO0,115200 root=/dev/nfs nfsroot=192.168.1.11:/srv/nfs4/bb_sysv,nfsvers=3 rw ip=dhcp"
```





# VNC 

### Not confirmed 

conf kernel -> 

```sh
CONFIG_FB_SIMPLE=y
CONFIG_FB_OMAP2=y         # required for OMAP/AM335x (BeagleBone)
CONFIG_DRM_TILCDC=y       # required for tilcdc DRM
CONFIG_DRM_FBDEV_EMULATION=y
```

disable `lcdc` from dtb -> status OK     



### Beaglebone

```sh
xinit /usr/bin/matchbox-terminal -- :0
x11vnc -display :0 -forever -nopw -shared
```



### PC 

```sh
vncviewer 192.168.1.107:0
```



Qt application startup envs:

```sh
export DISPLAY=:0
export QT_QPA_PLATFORM=xcb
export QT_PLUGIN_PATH=/usr/lib/qt5/plugins
export QT_QPA_FONTDIR=/usr/share/fonts/ttf
```

add environement vars to `qt-env.sh` file 

then add this file to yocto install it in the `/etc/profile.d` folder

`/etc/profile.d/` is a **standard directory in Linux systems** used to store **shell initialization scripts** that are automatically **sourced (executed)** when a user logs in via a **login shell** (e.g., bash, sh, etc.).

```sh
# qt-example.bb
...
do_install:append() {
    install -d ${D}${sysconfdir}/profile.d
    install -m 0755 ${WORKDIR}/qt-env.sh ${D}${sysconfdir}/profile.d/qt-env.sh
}
...
```



yocto add post process root file system commands

```sh
# qt-example.bb
...
ROOTFS_POSTPROCESS_COMMAND:append = " fix_fonts_symlink; "

fix_fonts_symlink () {
	ln -sf /usr/share/fonts/ttf /usr/lib/fonts
}
...
```



