jdkpath=
jfxpath=./javafx-sdk-19/lib

"${jdkpath}java" --module-path $jfxpath --add-modules javafx.controls,javafx.graphics,javafx.media,javafx.swing -jar Tamagotchi.jar
