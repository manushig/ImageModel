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

  private String fileName;

  /**
   * Constructs a SavePattern, specifying file name.
   * 
   * @param fileName It is the name of the file where pattern needs to be saved.
   */
  public SavePattern(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.savePattern(this.fileName);
  }

}
