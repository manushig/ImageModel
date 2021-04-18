package images.imagecontrol.commands;

import java.io.IOException;
import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "SaveImage" image operation on the image.
 *
 */
public class SaveImage implements ImageCommand {

  private final String fileName;

  /**
   * Constructs a SaveImage, specifying file name.
   * 
   * @param fileName It is the name of the file where image needs to be saved.
   * @throws IllegalArgumentException if filename is empty.
   */
  public SaveImage(String fileName) throws IllegalArgumentException {
    Objects.requireNonNull(fileName);
    if (fileName.equals("")) {
      throw new IllegalArgumentException("Filename cannot be empty");
    }
    this.fileName = fileName;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.saveImage(this.fileName);
  }

}
