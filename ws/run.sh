#!/bin/bash

CLASSSPATH="../class:../lib/*.jar"

if [[ -n "$PATH_TO_FX" ]]; then
    file=$(echo | zenity --entry --title="Entrer le nom de votre fichier à lancer")
    java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.base,javafx.fxml "$file"
else
  echo "\$PATH_TO_FX n'est pas défini, veuillez le définir dans votre .bashrc"
  echo "Ajoutez cette ligne dans votre .bashrc"
  echo "export PATH_TO_FX=\"Répértoires vers /javafx-sdk-22.0.1/lib\""
fi


