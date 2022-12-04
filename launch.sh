jdkpath=
jfxpath=./javafx-sdk-19/lib
objpath=./bin

java --module-path $jfxpath --add-modules javafx.controls,javafx.graphics,javafx.media -cp $objpath app.App
