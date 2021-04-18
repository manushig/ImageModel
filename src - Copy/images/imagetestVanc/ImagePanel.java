package images.imagetestVanc;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * A panel that can hold a buffered image.
 */
public class ImagePanel extends JPanel {

  /** Generated serial version id. */
  private static final long serialVersionUID = -5921731578486684150L;
  
  private BufferedImage image;

  /** Default constructor. */
  public ImagePanel() {
    image = null; // means that there is no image to display
  }
  
  /**
   * Setter for the image to display on the panel.
   * @param image the new image to display
   */
  public void setImage(BufferedImage image) {
    this.image = image;
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      g.drawImage(image, 0, 0, this);
    }
  }  
}
