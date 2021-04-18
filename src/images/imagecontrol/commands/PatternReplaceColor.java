package images.imagecontrol.commands;

import java.io.IOException;
import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "PatternReplaceColor" pattern operation on the
 * image.
 *
 */
public class PatternReplaceColor implements ImageCommand {

  private int xCordinate;
  private int yCordinate;
  private String dmcCode;

  /**
   * Constructs a PatternReplaceColor, specifying dmc code of the color to be
   * replaced on the pattern at given coordinates.
   * 
   * @param xCordinate x-coordinate of the image clicked.
   * @param yCordinate y-coordinate of the image clicked.
   * @param dmcCode    dmc code color to replace to
   */
  public PatternReplaceColor(int xCordinate, int yCordinate, String dmcCode) {
    Objects.requireNonNull(dmcCode);
    this.xCordinate = xCordinate;
    this.yCordinate = yCordinate;
    this.dmcCode = dmcCode;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.patternReplaceColor(this.xCordinate, this.yCordinate, this.dmcCode);
  }
}