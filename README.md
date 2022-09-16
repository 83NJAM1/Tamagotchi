# Tamagotchi
Jeu d'animaux de compagnie virtuels

# Import du projet Git avec Eclipse

## 1 NOTE
Si eclipse import les projets git dans `/home/nom_utilisateur/git`
alors changer le workspace par ce même repertoire

## 2 Option workspace
1. Aller dans **fichier -> importer -> git -> projet git classic**
2. Entrez vos identifiants github
3. Choisir import with wizard projet puis créer un projet java avec le meme nom que celui du git soit `Tamagotchi` -> valider

## 3 OpenJFX
1. Ensuite il faut télécharger JavaFX ou l'installer via votre distribution linux

## 4 Option project
1. Dans eclipse -> **projet -> propreties -> java build path -> selectionner classpath -> add library**
2. Selectionner user **library -> user libraries -> new -> NOM_LIB -> selectionner NOM_LIB -> add externals** jars
3. Selectionner tous les jars de javafx dans `/chemin/du/repertoire/java-xx-openjfx/lib` **-> apply**
4. Selectionner NOM_LIB comme library pour le projet

## 5 Option run
1. **Dans Run -> Run configuration -> java application (double clique)** -> Entrer les champs:
  - Name : NOM_RUN
  - Projcet name : Tamagotchi
  - Main class : test.Test

2. Allez dans l'onglet -> **Arguments -> VM Arguments**
  `--module-path /chemin/du/repertoire/java-xx-openjfx/lib --add-modules javafx.controls`
3. Allez dans l'onglet -> **Common -> Display in favorites menus -> selectionner Run**
5. Lancer le programme avec NOM_RUN
