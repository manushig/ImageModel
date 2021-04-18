package images.imagecontrol.commands;

import java.io.IOException;
import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "Pattern" image operation on the image.
 *
 */
public class Pattern implements ImageCommand {

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.pattern();
  }
}
