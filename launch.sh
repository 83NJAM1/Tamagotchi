jdkpath=
jfxpath=./javafx-sdk-19/lib
objpath=./bin

"${jdkpath}java" --module-path $jfxpath --add-modules javafx.controls,javafx.graphics,javafx.media,javafx.swing -cp $objpath app.App
