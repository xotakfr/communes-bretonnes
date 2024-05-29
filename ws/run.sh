#!/bin/bash

CLASSSPATH="../class:../lib/*.jar"

java --module-path ../lib --add-modules javafx.controls,javafx.base,javafx.fxml "$1"
