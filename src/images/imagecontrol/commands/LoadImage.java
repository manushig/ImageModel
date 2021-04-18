package images.imagecontrol.commands;

import java.io.IOException;
import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "Load" image operation on the image.
 *
 */
public class LoadImage implements ImageCommand {

  private final String fileName;

  /**
   * Constructs a LoadImage, specifying file name.
   * 
   * @param fileName It is the name of the file from which image needs to be
   *                 loaded.
   * @throws IllegalArgumentException if filename is empty.
   */
  public LoadImage(String fileName) throws IllegalArgumentException {
    Objects.requireNonNull(fileName);
    if (fileName.equals("")) {
      throw new IllegalArgumentException("Filename cannot be empty");
    }
    this.fileName = fileName;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.loadImage(this.fileName);
  }
}
