package images.imagecontrol.commands;

import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "Sepia" image operation on the image.
 *
 */
public class Sepia implements ImageCommand {

  @Override
  public void execute(ImageModelInterface model) {
    Objects.requireNonNull(model);
    model.sepia();
  }

}
