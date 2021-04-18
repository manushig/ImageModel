package images.imagecontrol.commands;

import java.io.IOException;
import java.util.Objects;

import images.imagecontrol.ImageCommand;
import images.imagemodel.ImageModelInterface;

/**
 * This command implements the "PatternAddText" pattern operation on the image.
 *
 */
public class PatternAddText implements ImageCommand {
  private String text;
  private String dmcCode;

  /**
   * Constructs a PatternAddText, specifying text and color of the text.
   * 
   * @param text    text to be displayed on the pattern.
   * @param dmcCode dmc Code of the color of the text.
   */
  public PatternAddText(String text, String dmcCode) {
    Objects.requireNonNull(text);
    Objects.requireNonNull(dmcCode);
    this.text = text;
    this.dmcCode = dmcCode;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.patternAddText(this.text, this.dmcCode);
  }
}
