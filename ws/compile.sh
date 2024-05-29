#!/bin/bash

javac --module-path ../lib --add-modules javafx.controls,javafx.base,javafx.fxml ../src/*.java ../src/*/*.java -d ../class
