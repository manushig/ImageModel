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

  private String fileName;

  /**
   * Constructs a SaveImage, specifying file name.
   * 
   * @param fileName It is the name of the file where image needs to be saved.
   */
  public SaveImage(String fileName) {
    Objects.requireNonNull(fileName);
    this.fileName = fileName;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.saveImage(this.fileName);
  }

}
