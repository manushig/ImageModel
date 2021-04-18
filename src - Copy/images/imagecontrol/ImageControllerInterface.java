package images.imagecontrol;

import java.io.IOException;

import images.imagemodel.ImageModelInterface;
import images.imageview.ImageViewInterface;

/**
 * ImageControllerInterface, it is and interface that takes user inputs, tells
 * model which image manipulations to do on the image.
 *
 */
public interface ImageControllerInterface {
  /**
   * This method is the starting point of the controller.
   * 
   * @param model It is the model
   * @throws IOException if an error generated while doing I/O operations on the
   *                     file.
   */
  public void start(ImageModelInterface model) throws IOException;
}
