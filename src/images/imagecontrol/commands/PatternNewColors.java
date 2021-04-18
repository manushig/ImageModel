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

  private List<String> selectedColors;

  /**
   * Constructs a PatternAddText, specifying list of dmc codes.
   * 
   * @param selectedColors List of dmc codes.
   */
  public PatternNewColors(List<String> selectedColors) {
    Objects.requireNonNull(selectedColors);
    this.selectedColors = selectedColors;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.patternAddNewColors(this.selectedColors);
  }
}
