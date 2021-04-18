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

  private String dmcCode;

  /**
   * Constructs a PatternRemoveColor, specifying dmc code of the color to be
   * removed on the pattern.
   * 
   * @param dmcCode dmc code of the color to be replaced on the pattern.
   */
  public PatternRemoveColor(String dmcCode) {
    Objects.requireNonNull(dmcCode);
    this.dmcCode = dmcCode;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.patternRemoveColor(this.dmcCode);
  }
}