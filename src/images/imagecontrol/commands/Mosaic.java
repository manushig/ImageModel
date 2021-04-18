package images.imagecontrol.commands;

import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "Mosaic" image operation on the image.
 *
 */
public class Mosaic implements ImageCommand {

  private final int noOfSeeds;

  /**
   * Constructs a Mosaic, specifying number of seeds.
   * 
   * @param noOfSeeds It is the number of seeds to be generated randomly.
   * @throws IllegalArgumentException if noOfSeeds value is zero or less than 0
   */
  public Mosaic(int noOfSeeds) throws IllegalArgumentException {
    if (noOfSeeds <= 0) {
      throw new IllegalArgumentException("Value cannot be negative or zero");
    }
    this.noOfSeeds = noOfSeeds;
  }

  @Override
  public void execute(ImageModelInterface model) {
    Objects.requireNonNull(model);
    model.mosaic(this.noOfSeeds);
  }
}