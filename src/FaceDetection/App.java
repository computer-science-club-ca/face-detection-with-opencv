package FaceDetection;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Exercice : Application qui d�tecte les visages sur des photographies On
 * utilise la librairie OpenCV : Open Source Computer Vision d'Intel avec des
 * r�gles de d�tection selon l'algorithme HAAR (Plus robuste, mais plus lent que
 * LBP utilis� sur les mobiles)
 * 
 * Installez OpenCV : https://opencv.org/releases.html
 * 
 * Les fichiers xml de r�gles de d�tection sont pr�sents dans le dossier
 * opencv/sources/data/haarcascades/
 * 
 * Configurer la librairie OpenCV dans Eclipse : Ouvrez le menu Project >
 * Properties > Java Build Path > Onglet Libraries Cliquez sur le bouton 'Add
 * External JARs...' S�lectionnez le fichier .jar o� OpenCV a �t� install� !
 * Dans mon cas : D:\opencv\build\java\opencv-331.jar Confirmez en cliquant sur
 * les boutons OK des fen�tres ouvertes.
 * 
 * Vous devez �galement d�terminer la librairie syst�me : Ouvrez le menu Project
 * > Properties > Java Build Path > Onglet Libraries Parmi les options de
 * l'environnement Java (JRE System Library), s�lectionnez l'option
 * "Native Library Location" Cliquez sur le bouton 'Edit...' S�lectionnez le
 * dossier d'OpenCV o� se trouvent les librairies Java. Dans mon cas, une
 * architecture 64 bits : D:\opencv\build\java\x64 Confirmez en cliquant sur les
 * boutons OK des fen�tres ouvertes.
 * 
 * Note : Je n'ai pas retrouv� de version r�cente de la librairie dans les
 * d�p�ts Maven.
 * 
 * Note : L'algorithme ne semble pas fonctionner pour les dessins de visages. Y
 * a-t-il d'autres r�gles de d�tection ? Voir le d�p�t github d'OpenCV...
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
