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
 * � l'aide de la librairie OpenCV d'Intel, d�tecter la pr�sence de visages sur
 * une photographie.
 * 
 * @see https://docs.opencv.org/master/d9/df8/tutorial_root.html
 */
public class FaceDetection {
    // R�gles de d�tection selon l'algorithme HAAR (Plus robuste, mais plus lent
    // que LBP utilis� pour les mobiles)
    private static final String CASCADE_CLASSIFIER = "D:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml";
    // Charger une description, ex.: les param�tres de recherche d'un visage de
    // face selon un fichier XML r�pondant � un standard.
    private CascadeClassifier cascadeClassifier;

    /**
     * Constructeur
     */
    public FaceDetection() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.cascadeClassifier = new CascadeClassifier(CASCADE_CLASSIFIER);
    }

    /**
     * D�tecter le(les) visage(s) pr�sent(s) dans une photographie.
     * 
     * @param file
     *            (File), fichier de l'image � traiter
     * @param imagePanel
     *            (ImagePanel), l'�l�mnet qui affiche l'image dans l'interface
     *            utilisateur
     */
    public void detectFaces(File file, ImagePanel imagePanel) {
        // Matrice de l'image/photo couleurs
        Mat image = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_COLOR);
        // Instance du d�tecteur de visage
        MatOfRect faceDetections = new MatOfRect();
        /*
         * D�tecter des objets de tailles diff�rentes dans une image. Les objets
         * d�tect�s sont retourn�s dans une liste de rectangles.
         * 
         * Param�tres :
         * 
         * image (Mat), Matrice de type CV_8U contenant une image o� des objets
         * sont d�tect�s.
         * 
         * objects (MatOfRect), Vecteur de rectangles o� chaque rectangle
         * contient un objet d�tect�.
         * 
         * scaleFactor (double), Param�tre sp�cifiant combien la taille de
         * l'image est r�duite � chaque �chelle d'image. Une valeur entre 1.1 et
         * 1.4 favoris�e.
         * 
         * minNeighbors (int), Param�tre sp�cifiant combien de voisins chaque
         * rectangle candidat devrait avoir pour �tre conserv�. Une valeur entre
         * 3 et 6 favoris�e.
         * 
         * flags (int), Sorte d'heuristique qui rejete certaines r�gion de
         * l'image lorsqu'elle comporte trop ou pas assez de contours.
         * 
         * minSize (Size), Taille d'objet minimale possible. Les objets plus
         * petits sont ignor�s. 30x30px est le standard.
         * 
         * maxSize (Size), Taille d'objet maximale possible. Les objets plus
         * grands sont ignor�s.
         */
        cascadeClassifier.detectMultiScale(image, faceDetections, 1.2, 3, 10, new Size(20, 20), new Size(500, 500));
        System.out.println("Nombre de visages d�tect�s : " + faceDetections.toArray().length);
        // Afficher un rectangle vert o� les visages sont d�tect�s
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 250, 0), 2);
        }
        // Mettre � jour le panneau de l'interface pour l'affichage
        BufferedImage bufferedImage = convertMatToImage(image);
        imagePanel.updateImage(bufferedImage);
    }

    /**
     * Convertir la matrice (image num�rique) en image pour l'affichage.
     * 
     * @param mat
     *            (Mat), matrice d'une image
     * @return image (BufferedImage), l'image pour l'affichage
     */
    public BufferedImage convertMatToImage(Mat mat) {
        // D�terminer si l'image est noir et blance ou en couleur
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        // Calculer la taille du tampon
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] bytes = new byte[bufferSize];
        // obtenir toutes les pixels de l'image
        mat.get(0, 0, bytes);
        // Convertir le tout dans l'image en m�moire
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
        return image;
    }
}