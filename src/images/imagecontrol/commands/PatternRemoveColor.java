package images.imagecontrol.commands;

import java.io.IOException;
import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "PatternRemoveColor" pattern operation on the
 * image.
 *
 */
public class PatternRemoveColor implements ImageCommand {

  private final String dmcCode;

  /**
   * Constructs a PatternRemoveColor, specifying DMC code of the color to be
   * removed on the pattern.
   * 
   * @param dmcCode DMC code of the color to be replaced on the pattern.
   * @throws IllegalArgumentException if dmcCode value is empty.
   */
  public PatternRemoveColor(String dmcCode) throws IllegalArgumentException {
    Objects.requireNonNull(dmcCode);
    if (dmcCode.equals("")) {
      throw new IllegalArgumentException("Value cannot be empty");
    }
    this.dmcCode = dmcCode;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.patternRemoveColor(this.dmcCode);
  }
}