build:
	javac -d ../class --module-path ../lib --add-modules javafx.controls,javafx.base,javafx.fxml $(shell find ../src -name '*.java') -cp ../class
	
doc:
	javadoc -private -author -d ../doc $(shell find ../src -name '*.java')