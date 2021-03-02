package images;

import java.io.IOException;
import java.util.Objects;

/**
 * ImageProcessing, implements an interface ImageProcessingInterface that has
 * methods to read an image from file and write to a file.
 *
 */
public class ImageProcessing implements ImageProcessingInterface {

  @Override
  public int[][][] readImage(String filepath) throws IOException {
    Objects.requireNonNull(filepath, "Filename cannot be null");

    return ImageUtilities.readImage(filepath);
  }

  @Override
  public void writeImage(int[][][] rgbBuffer, int width, int height, String filepath)
      throws IOException {
    Objects.requireNonNull(rgbBuffer, "Pixels value cannot be null.");
    Objects.requireNonNull(filepath, "Filename cannot be null.");

    ImageUtilities.writeImage(rgbBuffer, width, height, filepath);
  }

  @Override
  public int getWidth(String filename) throws IOException {
    Objects.requireNonNull(filename, "Filename cannot be null");
    return ImageUtilities.getWidth(filename);
  }

  @Override
  public int getHeight(String filename) throws IOException {
    Objects.requireNonNull(filename, "Filename cannot be null");
    return ImageUtilities.getHeight(filename);
  }

}
