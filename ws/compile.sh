#!/bin/bash

if [[ -n "$PATH_TO_FX" ]]; then
    javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.base,javafx.fxml ..src/*.java ..src/*/*.java ..src/*/*/*.java -d ../class
else
  echo "\$PATH_TO_FX n'est pas défini, veuillez le définir dans votre .bashrc"
  echo "Ajoutez cette ligne dans votre .bashrc"
  echo "export PATH_TO_FX=\"Répértoires vers /javafx-sdk-22.0.1/lib\""
fi
