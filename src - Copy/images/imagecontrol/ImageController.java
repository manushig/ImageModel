package images.imagecontrol;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import images.imagecontrol.commands.Blur;
import images.imagecontrol.commands.Dither;
import images.imagecontrol.commands.GrayScale;
import images.imagecontrol.commands.LoadImage;
import images.imagecontrol.commands.Mosaic;
import images.imagecontrol.commands.Pattern;
import images.imagecontrol.commands.PatternUI;
import images.imagecontrol.commands.Pixelate;
import images.imagecontrol.commands.SaveImage;
import images.imagecontrol.commands.SavePattern;
import images.imagecontrol.commands.Sepia;
import images.imagecontrol.commands.Sharpen;
import images.imagemodel.ImageModelInterface;
import images.imageview.ImageViewInterface;

/**
 * ImageController class implements the ImageControllerInterface interface that
 * can be used to take user inputs and tells model which image manipulations to
 * do on the image.
 *
 */
public class ImageController implements ImageControllerInterface {

  private final Readable in;
  private final Appendable out;

  /**
   * Constructs the ImageController, specifying in and out values.
   * 
   * @param in  It is the input to the controller.
   * @param out It is the output from the controller.
   */
  public ImageController(Readable in, Appendable out) {
    Objects.requireNonNull(in);
    Objects.requireNonNull(out);
    this.in = in;
    this.out = out;
  }

  /**
   * Private Helper method to create all the commands supported by the
   * application.
   * 
   * @return the generated set of commands map.
   */
  private Map<String, Function<Scanner, ImageCommand>> createCommands() {
    Map<String, Function<Scanner, ImageCommand>> knownCommands = new HashMap<>();
    knownCommands.put("load", s -> new LoadImage(s.next()));
    knownCommands.put("save", s -> new SaveImage(s.next()));
    knownCommands.put("blur", s -> new Blur());
    knownCommands.put("sharpen", s -> new Sharpen());
    knownCommands.put("grayscale", s -> new GrayScale());
    knownCommands.put("sepia", s -> new Sepia());
    knownCommands.put("dither", s -> new Dither(s.nextInt()));
    knownCommands.put("mosaic", s -> new Mosaic(s.nextInt()));
    knownCommands.put("pixelate", s -> new Pixelate(s.nextInt()));
    knownCommands.put("pattern", s -> new Pattern());
    knownCommands.put("savePattern", s -> new SavePattern(s.next()));

    return knownCommands;
  }

  @Override
  public void start(ImageModelInterface model) throws IOException {
    Objects.requireNonNull(model);

    Stack<ImageCommand> commands = new Stack<>();

    Map<String, Function<Scanner, ImageCommand>> imageCommands = createCommands();

    Scanner scan = new Scanner(this.in);

    while (scan.hasNext()) {
      ImageCommand givenCommand;
      String input = scan.next();

      Function<Scanner, ImageCommand> cmd = imageCommands.getOrDefault(input, null);

      if (cmd == null) {
        throw new IllegalArgumentException();
      } else {
        givenCommand = cmd.apply(scan);
        commands.add(givenCommand);
        givenCommand.execute(model);
        this.out.append(String.format("%s command is executed.\n", input));
      }
    }

  }

}
