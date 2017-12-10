package FaceDetection;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Panneau qui contient l'image
 *
 */
public class ImagePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel imageLabel;
    private ImageIcon transformedImageIcon;

    public ImagePanel() {
        this.imageLabel = new JLabel();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(MainFrame.IMAGE_LABEL_BORDER, MainFrame.IMAGE_LABEL_BORDER,
                MainFrame.IMAGE_LABEL_BORDER, MainFrame.IMAGE_LABEL_BORDER));
        add(imageLabel, BorderLayout.CENTER);
    }

    public void loadImage(File file) {

        this.transformedImageIcon = new ImageIcon(file.getAbsolutePath());
        Image image = transformedImageIcon.getImage();

        updateImage(image);
    }

    public void updateImage(final Image image) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                imageLabel.setIcon(scaleImage(image));
            }
        });
    }

    private ImageIcon scaleImage(Image image) {
        ImageIcon imageIcon = new ImageIcon(image);
        int height = imageIcon.getIconHeight();
        int width = imageIcon.getIconWidth();
        return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
}
