package images.imagetestVanc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This is a demonstration of how to put an image in a gui.
 */
public class ImageDemo extends JFrame implements ActionListener {

  /** Generated version number. */
  private static final long serialVersionUID = 9102258060515533855L;

  private ImagePanel imagePanel;

  /**
   * Constructs the image demo frame.
   * 
   * @param title the title
   */
  public ImageDemo(String title) {
    super(title);

    this.setSize(500, 300);
    this.setLocation(200, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    JButton load = new JButton("This is an awesome load button");
    load.addActionListener(this);
    JPanel bottom = new JPanel();
    bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
    bottom.add(load);
    this.add(bottom, BorderLayout.SOUTH);

    imagePanel = new ImagePanel();
    imagePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent event) {
        JOptionPane.showMessageDialog(null,
            "Mouse Clicked: (" + event.getX() + ", " + event.getY() + ")");
      }
    });
    imagePanel.setBackground(Color.DARK_GRAY);
    this.add(imagePanel, BorderLayout.CENTER);

    JPanel right = new JPanel();
    right.setBackground(Color.blue);
    // right.add(load);
    this.add(right, BorderLayout.EAST);
  }

  /**
   * Start the image demo.
   */
  public void start() {
    this.setVisible(true);
  }

  /**
   * Gets the image from a file.
   */
  public void getImage() {
    JFileChooser fc = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "png", "gif",
        "bmp");
    fc.setFileFilter(filter);
    int returnValue = fc.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      // String filename = file.getAbsolutePath();
      try {
        BufferedImage image = ImageIO.read(file);
        imagePanel.setImage(image);
        this.repaint();
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "An error occurred reading the file");
      }
    }

  }

  @Override
  public void actionPerformed(ActionEvent event) {
    this.getImage();
  }
}
