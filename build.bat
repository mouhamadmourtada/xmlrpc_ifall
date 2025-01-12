@echo off
echo Cleaning bin directory...
if exist bin rmdir /s /q bin
mkdir bin

echo Compiling Java files...
javac -d bin -cp "lib\xmlrpc-2.0.jar;lib\commons-codec-1.16.0.jar" src\client\model\*.java src\client\view\*.java src\client\controller\*.java src\client\*.java

echo Done!
