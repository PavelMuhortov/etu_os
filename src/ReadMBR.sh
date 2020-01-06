#!/bin/bash

# Exemple:
#		sudo bash ./git/etu_os/src/ReadMBR.sh sda
#		sudo bash ./git/etu_os/src/ReadMBR.sh sdb
#		sudo bash ./git/etu_os/src/ReadMBR.sh nvme0n1

if [ "$EUID" -ne 0 ]
	then
		echo "Для запуска необходимо использовать root или sudo!"
		exit 1
fi

deviceName=${1}
if [ -z "$deviceName" ]
	then
		lsblk -e 7 -d -o NAME,SIZE
		echo -n "С какого устройства прочитать запись MBR:"
		read deviceName
fi



#dd if=/dev/$deviceName bs=1 count=512 conv=swab | hexdump # full MBR
dd if=/dev/$deviceName bs=1 count=512 of=./$deviceName.mbr status=none
dd if=./$deviceName.mbr bs=1 count=446 of=./$deviceName.mbr.loader status=none
dd if=./$deviceName.mbr bs=1 skip=446 count=66 of=./$deviceName.mbr.table status=none

printBlock=${2}
if [[ "$printBlock" == *"f"* ]]
then
	echo -e "\e[33m          Full Master Boot Record \e[0m"	&& hd ./$deviceName.mbr
fi
if [[ "$printBlock" == *"l"* ]]
then
	echo -e "\e[33m          Loader from MBR \e[1;32m"	&& hd ./$deviceName.mbr.loader
fi
if [[ "$printBlock" == *"t"* ]]
then
	echo -e "\e[33m          Table from MBR \e[1;34m"	&& hd ./$deviceName.mbr.table
fi
tput sgr0
exit 0
