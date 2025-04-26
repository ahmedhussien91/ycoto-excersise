# NFS 

### copying output of yocto

 ```sh
 sudo tar -xzvf bb-build-sysv/deploy-ti/images/beaglebone/custom-image-beaglebone.rootfs-20250420043609.tar.gz -C /srv/nfs4/bb_sysv/
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

