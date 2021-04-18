package images.imagecontrol.commands;

import java.io.IOException;
import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

public class PatternRemoveColor implements ImageCommand {

  private String dmcCode;


  public PatternRemoveColor(String dmcCode) {
    Objects.requireNonNull(dmcCode);
    this.dmcCode = dmcCode;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.patternRemoveColor(this.dmcCode);
  }
}