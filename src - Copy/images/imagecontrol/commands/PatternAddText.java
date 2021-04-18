package images.imagecontrol.commands;

import java.io.IOException;
import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

public class PatternAddText implements ImageCommand {
  private String text;
  private String dmcCode;

  public PatternAddText(String text, String dmcCode) {
    this.text = text;
    this.dmcCode = dmcCode;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.patternAddText(this.text, this.dmcCode);
  }
}
