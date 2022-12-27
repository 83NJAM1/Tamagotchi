jdkpath=
jfxpath=./javafx-sdk-19/lib
srcpath=./src
objpath=./bin

if [ -e bin ]; then
	rm -r bin
fi

mkdir bin
cp -r ./res ./bin/res
cp ./res/language.properties ./bin/language.properties
cp ./res/language_en.properties ./bin/language_en.properties

javac -d $objpath --module-path $jfxpath --add-modules javafx.controls,javafx.graphics,javafx.media,javafx.swing -cp $srcpath $srcpath/app/App.java
