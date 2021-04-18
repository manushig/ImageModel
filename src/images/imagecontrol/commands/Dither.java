package images.imagecontrol.commands;

import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "Dither" image operation on the image.
 *
 */
public class Dither implements ImageCommand {

  private final int noOfColorsToReduceTo;

  /**
   * Constructs a Dither, specifying number of colors to reduce to detail.
   * 
   * @param noOfColorsToReduceTo It is the number of colors to reduce to.
   * @throws IllegalArgumentException if noOfColorsToReduceTo less than 0 or 0.
   */

  public Dither(int noOfColorsToReduceTo) throws IllegalArgumentException {
    if (noOfColorsToReduceTo >= 0) {
      throw new IllegalArgumentException("Value cannot be negative or zero");
    }

    this.noOfColorsToReduceTo = noOfColorsToReduceTo;
  }

  @Override
  public void execute(ImageModelInterface model) {
    Objects.requireNonNull(model);
    model.dither(this.noOfColorsToReduceTo);
  }
}