package images.imagecontrol.commands;

import java.io.IOException;
import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "SavePattern" image operation on the image.
 *
 */
public class SavePattern implements ImageCommand {

  private final String fileName;

  /**
   * Constructs a SavePattern, specifying file name.
   * 
   * @param fileName It is the name of the file where pattern needs to be saved.
   * @throws IllegalArgumentException if filename is empty.
   */
  public SavePattern(String fileName) throws IllegalArgumentException {
    Objects.requireNonNull(fileName);
    if (fileName.equals("")) {
      throw new IllegalArgumentException("Filename cannot be empty");
    }
    this.fileName = fileName;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.savePattern(this.fileName);
  }

}
