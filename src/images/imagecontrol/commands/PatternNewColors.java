package images.imagecontrol.commands;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "PatternNewColors" pattern operation on the
 * image.
 *
 */
public class PatternNewColors implements ImageCommand {

  private final List<String> selectedColors;

  /**
   * Constructs a PatternAddText, specifying list of DMC codes.
   * 
   * @param selectedColors List of DMC codes.
   * @throws IllegalArgumentException if no colors are provided.
   */
  public PatternNewColors(List<String> selectedColors) throws IllegalArgumentException {
    Objects.requireNonNull(selectedColors);
    if (selectedColors.size() == 0) {
      throw new IllegalArgumentException("Atleast one color be there.");
    }
    this.selectedColors = selectedColors;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.patternAddNewColors(this.selectedColors);
  }
}
