package images.imagecontrol;

import java.io.IOException;

import images.imagemodel.ImageModelInterface;

/**
 * ImageCommand, it is an interface that gives the various commands to the be
 * performed on the image.
 *
 */

public interface ImageCommand {
  /**
   * This method executes the command.
   * 
   * @param model it is the model
   * @throws IOException if an error generated while model doing I/O operations on
   *                     the file
   */
  void execute(ImageModelInterface model) throws IOException;

}
