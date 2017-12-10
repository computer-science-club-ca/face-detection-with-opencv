package FaceDetection;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Fenêtre principale de l'application
 *
 */
public class MainFrame extends JFrame {
    public static final String APPLICATION_NAME = "Détecteur de visage(s)";
    public static final String EXIT_WARNING = "Voulez-vous quitter ?";
    public static final int FRAME_WIDTH = 800;
    public static final int IMAGE_LABEL_BORDER = 30;
    public static final int FRAME_HEIGHT = 600;
    private static final long serialVersionUID = 1L;
    private ImagePanel imagePanel;
    private JFileChooser fileChooser;
    private FaceDetection faceDetection;
    private File file;

    public MainFrame() {
        super(APPLICATION_NAME);
        setJMenuBar(createMenuBar());
        this.imagePanel = new ImagePanel();
        this.fileChooser = new JFileChooser();
        this.faceDetection = new FaceDetection();
        add(imagePanel, BorderLayout.CENTER);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Fichier");
        JMenuItem loadMenuItem = new JMenuItem("Choisir une photo...");
        JMenuItem detectMenuItem = new JMenuItem("Détecter le(s) visage(s)");
        JMenuItem exitMenuItem = new JMenuItem("Quitter");
        fileMenu.add(loadMenuItem);
        fileMenu.add(detectMenuItem);
        fileMenu.add(exitMenuItem);
        loadMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    MainFrame.this.file = fileChooser.getSelectedFile();
                    MainFrame.this.imagePanel.loadImage(MainFrame.this.file);
                }
            }
        });
        detectMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.faceDetection.detectFaces(MainFrame.this.file, MainFrame.this.imagePanel);
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.gc();
                System.exit(0);
            }
        });
        menuBar.add(fileMenu);
        return menuBar;
    }
}
