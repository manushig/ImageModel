package images.imagecontrol.commands;

import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "Mosaic" image operation on the image.
 *
 */
public class Mosaic implements ImageCommand {

  private int noOfSeeds;

  /**
   * Constructs a Mosaic, specifying number of seeds.
   * 
   * @param noOfSeeds It is the number of seeds to be generated randomly.
   */
  public Mosaic(int noOfSeeds) {
    this.noOfSeeds = noOfSeeds;
  }

  @Override
  public void execute(ImageModelInterface model) {
    Objects.requireNonNull(model);
    model.mosaic(this.noOfSeeds);
  }
}