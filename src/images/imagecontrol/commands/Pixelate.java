package images.imagecontrol.commands;

import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "Pixelate" image operation on the image.
 *
 */
public class Pixelate implements ImageCommand {

  private final int noOfSquaresAcross;

  /**
   * Constructs a Pixelate, specifying number of squares across the width of the
   * image.
   * 
   * @param noOfSquaresAcross It is the number of squares across the width of the
   *                          image.
   * @throws IllegalArgumentException if noOfSquaresAcross less than 0 or 0.
   */
  public Pixelate(int noOfSquaresAcross) throws IllegalArgumentException {
    if (noOfSquaresAcross <= 0) {
      throw new IllegalArgumentException("Value cannot be negative or zero");
    }
    this.noOfSquaresAcross = noOfSquaresAcross;
  }

  @Override
  public void execute(ImageModelInterface model) {
    Objects.requireNonNull(model);
    model.pixelate(this.noOfSquaresAcross);
  }
}
