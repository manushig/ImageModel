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

  private String fileName;

  /**
   * Constructs a LoadImage, specifying file name.
   * 
   * @param fileName It is the name of the file from which image needs to be
   *                 loaded.
   */
  public LoadImage(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.loadImage(this.fileName);
  }
}
