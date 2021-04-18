package images.imagecontrol.commands;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

public class PatternNewColors implements ImageCommand {

  private List<String> selectedColors;

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
