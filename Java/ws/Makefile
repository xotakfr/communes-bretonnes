build: clean
	javac -d ../class --module-path ../lib --add-modules javafx.controls,javafx.base,javafx.fxml $(shell find ../src -name '*.java') -cp ../class
	cp -r ../src/resources ../class
	cp ../src/Manifest.txt ../class/
	cd ../class && jar cfm Manifest.txt $(shell find ../src -name '*.class')

doc:
	javadoc -private -author -d ../doc $(shell find ../src -name '*.java')

run: build
	java -cp ../class:../FXML:../lib --module-path ../lib --add-modules javafx.controls,javafx.base,javafx.fxml CommunesBretonnesApp

clean:
	if [ -d "jar" ]; then rm -rf jar; fi
	if [ -d "../class" ]; then rm -rf ../class; fi
