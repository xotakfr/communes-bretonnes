#!/bin/bash

if ! command -v wget &> /dev/null
then
	echo "wget est introuvable, veuillez l'installer"
	exit 1
fi
if ! command -v unzip &> /dev/null
then
	echo "unzip est introuvable, veuillez l'installer"
	exit 1
fi

echo "Setting up JavaFX (for Java 17)"
wget "https://download2.gluonhq.com/openjfx/17.0.11/openjfx-17.0.11_linux-x64_bin-sdk.zip"
unzip openjfx-17.0.11_linux-x64_bin-sdk.zip "javafx-sdk-17.0.11/lib/*"
mv ./javafx-sdk-17.0.11/lib/* ../lib
rm -rf javafx-sdk-17.0.11

echo "Setting up database driver"
wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-8.4.0.zip
unzip mysql-connector-j-8.4.0.zip "mysql-connector-j-8.4.0/mysql-connector-j-8.4.0.jar"
mv ./mysql-connector-j-8.4.0/mysql-connector-j-8.4.0.jar ../lib
rm -rf mysql-connector-j-8.4.0

rm *.zip

echo "Setup completed"
