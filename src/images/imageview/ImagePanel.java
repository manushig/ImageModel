package images.imageview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import images.imagetestVanc.ScrollablePicture;

/**
 * A panel that can hold a buffered image.
 */
public class ImagePanel extends JPanel {

  /** Generated serial version id. */
  private static final long serialVersionUID = -5921731578486684150L;

  private BufferedImage imageToDisplay;
  private ScrollablePicture picture;

  /** Default constructor. 
   * @throws IOException */
  public ImagePanel() throws IOException {
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

    String directory = new File(".").getCanonicalPath()
        + "\\src\\images\\imagetestVanc\\images\\SnowyTree.png";
    ImageIcon image = null;
    if (!Objects.isNull(imageToDisplay)) {
      image = new ImageIcon(this.imageToDisplay);
    }
    // Set up the scroll pane.
    picture = new ScrollablePicture(image, 10);
    JScrollPane pictureScrollPane = new JScrollPane(picture);
    pictureScrollPane.setPreferredSize(new Dimension(300, 250));
    pictureScrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));

    // Put it in this panel.
    add(pictureScrollPane);
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  /**
   * Setter for the image to display on the panel.
   * 
   * @param image the new image to display
   */
  public void setImage(BufferedImage image) {
    this.imageToDisplay = image;
    
    
  }

  /*
   * @Override protected void paintComponent(Graphics g) {
   * super.paintComponent(g); // g.drawImage(imageToDisplay, 0, 0, 750, 750,
   * this); int x = 0; int y = 0;
   * 
   * if (!Objects.isNull(imageToDisplay)) { // Graphics2D g2d = (Graphics2D) g; x
   * = (this.getWidth() - imageToDisplay.getWidth(null)) / 2; y =
   * (this.getHeight() - imageToDisplay.getHeight(null)) / 2;
   * 
   * System.out.println(x); System.out.println(y);
   * 
   * if (x < 0) { x = 0; } if (y < 0) { y = 0; } Graphics2D g2d = (Graphics2D) g;
   * 
   * System.out.println(this.getWidth()); System.out.println(this.getHeight());
   * System.out.println(imageToDisplay.getWidth(null));
   * System.out.println(imageToDisplay.getHeight(null)); System.out.println(x);
   * System.out.println(y);
   * 
   * g2d.drawImage(imageToDisplay, x, y, this);
   * 
   * } }
   */
}
