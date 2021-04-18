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
   */
  public Pixelate(int noOfSquaresAcross) {
    this.noOfSquaresAcross = noOfSquaresAcross;
  }

  @Override
  public void execute(ImageModelInterface model) {
    Objects.requireNonNull(model);
    model.pixelate(this.noOfSquaresAcross);
  }
}
