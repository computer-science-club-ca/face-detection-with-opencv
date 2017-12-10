package FaceDetection;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Exercice : Application qui détecte les visages sur des photographies On
 * utilise la librairie OpenCV : Open Source Computer Vision d'Intel avec des
 * règles de détection selon l'algorithme HAAR (Plus robuste, mais plus lent que
 * LBP utilisé sur les mobiles)
 * 
 * Installez OpenCV : https://opencv.org/releases.html
 * 
 * Les fichiers xml de règles de détection sont présents dans le dossier
 * opencv/sources/data/haarcascades/
 * 
 * Configurer la librairie OpenCV dans Eclipse : Ouvrez le menu Project >
 * Properties > Java Build Path > Onglet Libraries Cliquez sur le bouton 'Add
 * External JARs...' Sélectionnez le fichier .jar où OpenCV a été installé !
 * Dans mon cas : D:\opencv\build\java\opencv-331.jar Confirmez en cliquant sur
 * les boutons OK des fenêtres ouvertes.
 * 
 * Vous devez également déterminer la librairie système : Ouvrez le menu Project
 * > Properties > Java Build Path > Onglet Libraries Parmi les options de
 * l'environnement Java (JRE System Library), sélectionnez l'option
 * "Native Library Location" Cliquez sur le bouton 'Edit...' Sélectionnez le
 * dossier d'OpenCV où se trouvent les librairies Java. Dans mon cas, une
 * architecture 64 bits : D:\opencv\build\java\x64 Confirmez en cliquant sur les
 * boutons OK des fenêtres ouvertes.
 * 
 * Note : Je n'ai pas retrouvé de version récente de la librairie dans les
 * dépôts Maven.
 * 
 * Note : L'algorithme ne semble pas fonctionner pour les dessins de visages. Y
 * a-t-il d'autres règles de détection ? Voir le dépôt github d'OpenCV...
 * 
 */
public class App {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
