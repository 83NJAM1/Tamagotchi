# Tamagotchi
Jeu d'animaux de compagnie virtuel

# Import du projet Git avec Eclipse

## 1
Si eclipse import les projet git dans /home/nom_utilisateur/git
alors changer le workspace par ce même repertoire

## 2
Aller dans fichier -> importer -> git -> projet git classic
entrez vos identifiant github
Choisir import with wizard projet puis créer un projet java avec le meme nom que celui du git donc Tamagotchi valider

## 3
Ensuite il faut télécharger JavaFX ou l'installer via votre distribution linux

## 4
Dans eclipse -> projet -> propreties -> java build path -> (selectionner classpath) -> add library
selectionner user library -> user libraries -> new -> NOM_LIB -> selectionner NOM_LIB -> add externals jars
selectionner tous les jars de javafx/openjfx /chemin/du/repertoire/java-xx-openjfx/lib -> apply
maintenant selectionne comme library pour le projet NOM_LIB

## 5
Configurer le Run dans Run -> Run configuration -> java application -> (double clique) -> Entrer les champs -> 
nom:NOM_RUN, projet name : Tamagotchi
Allez dans l'onglet -> Arguments : ajouter dans VM Arguments --module-path /chemin/du/repertoire/java-xx-openjfx/lib --add-modules javafx.controls
Lancer le programme avec NOM_RUN
