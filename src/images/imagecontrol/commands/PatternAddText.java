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
  private final String text;
  private final String dmcCode;

  /**
   * Constructs a PatternAddText, specifying text and color of the text.
   * 
   * @param text    text to be displayed on the pattern.
   * @param dmcCode DMC Code of the color of the text.
   * @throws IllegalArgumentException if text or dmcCode value is empty.
   */
  public PatternAddText(String text, String dmcCode) throws IllegalArgumentException {
    Objects.requireNonNull(text);
    Objects.requireNonNull(dmcCode);
    if (text.equals("") || dmcCode.equals("")) {
      throw new IllegalArgumentException("Value cannot be empty");
    }

    this.text = text;
    this.dmcCode = dmcCode;
  }

  @Override
  public void execute(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);
    model.patternAddText(this.text, this.dmcCode);
  }
}
