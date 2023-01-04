cd bin
jar -c -e app.App -f Tamagotchi.jar app/*.class app/controller/*.class app/model/*class app/view/*.class ./*.properties res/*
#zip release -r bin res launch.sh
