package images.imagecontrol.commands;

import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "Dither" image operation on the image.
 *
 */
public class Dither implements ImageCommand {

  private int noOfColorsToReduceTo;

  /**
   * Constructs a Dither, specifying number of colors to reduce to detail.
   * 
   * @param noOfColorsToReduceTo It is the number of colors to reduce to.
   */

  public Dither(int noOfColorsToReduceTo) {
    this.noOfColorsToReduceTo = noOfColorsToReduceTo;
  }

  @Override
  public void execute(ImageModelInterface model) {
    Objects.requireNonNull(model);
    model.dither(this.noOfColorsToReduceTo);
  }
}