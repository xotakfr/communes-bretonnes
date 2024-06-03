Write-Host "Setting up JavaFX (for Java 17)"
Invoke-WebRequest -Uri "https://download2.gluonhq.com/openjfx/17.0.11/openjfx-17.0.11_windows-x64_bin-sdk.zip" -UseBasicParsing -OutFile openjfx-17.zip
Expand-Archive -Path openjfx-17.zip
Move-Item -Path openjfx-17/javafx-sdk-17.0.11/lib/* -Destination ../lib
Remove-Item -Path ./openjfx-17 -Recurse

Write-Host "Setting up database driver"
Invoke-WebRequest -Uri "https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-8.4.0.zip" -UseBasicParsing -OutFile "mysql-connector-j.zip"
Expand-Archive "mysql-connector-j.zip"
Move-Item -Path "mysql-connector-j/mysql-connector-j-8.4.0/mysql-connector-j-8.4.0.jar" -Destination ../lib
Remove-Item -Path "./mysql-connector-j" -Recurse

Remove-Item -Path ./*.zip

Write-Host "Setup completed"