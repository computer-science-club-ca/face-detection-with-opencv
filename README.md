# face-detection-with-opencv
Exercice : Application qui détecte les visages sur des photographies à l'aide de la librairie OpenCV : "Open Source Computer Vision" d'Intel. On utilise les règles de détection selon l'algorithme HAAR (Plus robuste, mais plus lent que LBP utilisé sur les mobiles).

## Téléchargement
Vous pouvez téléchargez l'archives .zip du code ou bien utilisez git : 
$ git clone https://github.com/computer-science-club-ca/face-detection-with-opencv.git

## L'environnement
C'est un projet Java.

Installez Java 8 (JDK): http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Installez Eclipse Java EE IDE for Web Developers (Version: Mars, http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/mars2)

Importez le projet Java dans votre espace de travail Eclipse et lancez-le comme une application Java ! :)

### Installation d'OpenCV
Installez OpenCV : https://opencv.org/releases.html

Les fichiers xml de règles de détection sont présents dans le dossier opencv/sources/data/haarcascades/
On utilise le fichier haarcascade_frontalface_alt.xml

#### Configurer la librairie OpenCV dans Eclipse : 
* Ouvrez le menu Project > Properties > Java Build Path > Onglet Libraries Cliquez sur le bouton 'Add External JARs...' 
* Sélectionnez le fichier .jar où OpenCV a été installé ! Dans mon cas : D:\opencv\build\java\opencv-331.jar 
* Confirmez en cliquant sur les boutons OK des fenêtres ouvertes.

Vous devez également déterminer la librairie système : 
* Ouvrez le menu Project > Properties > Java Build Path > Onglet Libraries Parmi les options de l'environnement Java (JRE System Library), sélectionnez l'option "Native Library Location" 
* Cliquez sur le bouton 'Edit...' 
* Sélectionnez le dossier d'OpenCV où se trouvent les librairies Java. Dans mon cas, une architecture 64 bits : D:\opencv\build\java\x64 
* Confirmez en cliquant sur les boutons OK des fenêtres ouvertes.

Note : Je n'ai pas retrouvé de version récente de la librairie dans les dépôts Maven.

Note : L'algorithme ne semble pas fonctionner pour les dessins de visages. Y a-t-il d'autres règles de détection ? Voir le dépôt github d'OpenCV...
