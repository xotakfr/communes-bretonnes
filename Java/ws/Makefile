build: clean
	javac -d ../classes --module-path ../lib --add-modules javafx.controls,javafx.base,javafx.fxml $(shell find ../src -name '*.java') -cp ../classes
	cp -r ../src/resources ../classes
	cp ../src/Manifest.txt ../classes/
	cd ../classes && jar cvfm CommunesBretonnesApp.jar Manifest.txt $(find ./ -name "*.classes") resources/
	cp ../classes/CommunesBretonnesApp.jar ./

doc:
	javadoc -private -author -d ../doc $(shell find ../src -name '*.java')

run: build
	java -cp ../classes:../lib --module-path ../lib --add-modules javafx.controls,javafx.base,javafx.fxml -jar CommunesBretonnesApp.jar

clean:
	if [ -d "jar" ]; then rm -rf jar; fi
	if [ -d "../classes" ]; then rm -rf ../classes; fi
