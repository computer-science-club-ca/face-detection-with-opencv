package FaceDetection;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * À l'aide de la librairie OpenCV d'Intel, détecter la présence de visages sur
 * une photographie.
 * 
 * @see https://docs.opencv.org/master/d9/df8/tutorial_root.html
 */
public class FaceDetection {
    // Règles de détection selon l'algorithme HAAR (Plus robuste, mais plus lent
    // que LBP utilisé pour les mobiles)
    private static final String CASCADE_CLASSIFIER = "D:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml";
    // Charger une description, ex.: les paramètres de recherche d'un visage de
    // face selon un fichier XML répondant à un standard.
    private CascadeClassifier cascadeClassifier;

    /**
     * Constructeur
     */
    public FaceDetection() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.cascadeClassifier = new CascadeClassifier(CASCADE_CLASSIFIER);
    }

    /**
     * Détecter le(les) visage(s) présent(s) dans une photographie.
     * 
     * @param file
     *            (File), fichier de l'image à traiter
     * @param imagePanel
     *            (ImagePanel), l'élémnet qui affiche l'image dans l'interface
     *            utilisateur
     */
    public void detectFaces(File file, ImagePanel imagePanel) {
        // Matrice de l'image/photo couleurs
        Mat image = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_COLOR);
        // Instance du détecteur de visage
        MatOfRect faceDetections = new MatOfRect();
        /*
         * Détecter des objets de tailles différentes dans une image. Les objets
         * détectés sont retournés dans une liste de rectangles.
         * 
         * Paramètres :
         * 
         * image (Mat), Matrice de type CV_8U contenant une image où des objets
         * sont détectés.
         * 
         * objects (MatOfRect), Vecteur de rectangles où chaque rectangle
         * contient un objet détecté.
         * 
         * scaleFactor (double), Paramètre spécifiant combien la taille de
         * l'image est réduite à chaque échelle d'image. Une valeur entre 1.1 et
         * 1.4 favorisée.
         * 
         * minNeighbors (int), Paramètre spécifiant combien de voisins chaque
         * rectangle candidat devrait avoir pour être conservé. Une valeur entre
         * 3 et 6 favorisée.
         * 
         * flags (int), Sorte d'heuristique qui rejete certaines région de
         * l'image lorsqu'elle comporte trop ou pas assez de contours.
         * 
         * minSize (Size), Taille d'objet minimale possible. Les objets plus
         * petits sont ignorés. 30x30px est le standard.
         * 
         * maxSize (Size), Taille d'objet maximale possible. Les objets plus
         * grands sont ignorés.
         */
        cascadeClassifier.detectMultiScale(image, faceDetections, 1.2, 3, 10, new Size(20, 20), new Size(500, 500));
        System.out.println("Nombre de visages détectés : " + faceDetections.toArray().length);
        // Afficher un rectangle vert où les visages sont détectés
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 250, 0), 2);
        }
        // Mettre à jour le panneau de l'interface pour l'affichage
        BufferedImage bufferedImage = convertMatToImage(image);
        imagePanel.updateImage(bufferedImage);
    }

    /**
     * Convertir la matrice (image numérique) en image pour l'affichage.
     * 
     * @param mat
     *            (Mat), matrice d'une image
     * @return image (BufferedImage), l'image pour l'affichage
     */
    public BufferedImage convertMatToImage(Mat mat) {
        // Déterminer si l'image est noir et blance ou en couleur
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        // Calculer la taille du tampon
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] bytes = new byte[bufferSize];
        // obtenir toutes les pixels de l'image
        mat.get(0, 0, bytes);
        // Convertir le tout dans l'image en mémoire
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
        return image;
    }
}