package images.imagemodel;

import java.io.IOException;
import java.util.Objects;

/**
 * Image class implements the ImageInterface interface that can be used to
 * manipulate images to produce some interesting effects.
 */
public class Image implements ImageInterface {
  private int[][][] rgb_buffer;
  private String pattern;

  /**
   * Constructs a Image with rgb_buffer as null, as no image is loaded yet and
   * pattern as null, since no pattern is generated yet.
   *
   */

  public Image() {
    this.rgb_buffer = null;
    this.pattern = null;
  }

  /**
   * Constructs a Copy constructor for ImageModel class.
   * 
   */

  public Image(Image image) {
    if (!Objects.isNull(image.rgb_buffer)) {
      this.rgb_buffer = new int[image.rgb_buffer.length]
          [image.rgb_buffer[0].length][image.rgb_buffer[0][0].length];
      for (int x = 0; x < image.rgb_buffer.length; x++) {
        for (int y = 0; y < image.rgb_buffer[0].length; y++) {
          for (int z = 0; z < image.rgb_buffer[0][0].length; z++) {
            this.rgb_buffer[x][y][z] = image.rgb_buffer[x][y][z];
          }
        }
      }
    } else {
      this.rgb_buffer = null;
    }
    this.pattern = image.pattern;
  }

  @Override
  public String toString() {
    if (Objects.isNull(this.rgb_buffer)) {
      return String.format("No image is loaded.");
    } else {

      return String.format("Image Height - %d and Width - %d", this.rgb_buffer.length,
          this.rgb_buffer[0].length);
    }
  }

  @Override
  public ImageInterface loadImage(String fileName) throws IOException {
    Objects.requireNonNull(fileName, "Filename cannot be null");

    int[][][] rgb_buffer = ImageUtilities.readImage(fileName);

    Objects.requireNonNull(rgb_buffer, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer;

    return new Image(this);
  }

  @Override
  public ImageInterface saveImage(String fileName) throws IOException {
    Objects.requireNonNull(fileName, "Filepath cannot be null.");

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    ImageUtilities.writeImage(image_buffer, image_buffer[0].length, image_buffer.length, fileName);

    return new Image(this);
  }

  @Override
  public ImageInterface colorTransformation(float[][] colorTransformedMatrix) {
    Objects.requireNonNull(colorTransformedMatrix, "Color Transformed matrix cannot not be null");

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    ColorTransformationInterface colorTransformation = new ColorTransformation();

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    int[][][] rgb_buffer_processed = colorTransformation.doColorTransformation(image_buffer,
        colorTransformedMatrix);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    int[][][] rgb_buffer_processed_clamped = Clamping.doClamping(rgb_buffer_processed);

    Objects.requireNonNull(rgb_buffer_processed_clamped, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer_processed_clamped;

    return new Image(this);
  }

  @Override
  public ImageInterface filter(float[][] kernel) {
    Objects.requireNonNull(kernel, "Kernel value cannot not be null.");

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    FilterInterface filter = new Filter();

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    int[][][] rgb_buffer_processed = filter.doFilter(image_buffer, kernel);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    int[][][] rgb_buffer_processed_clamped = Clamping.doClamping(rgb_buffer_processed);

    Objects.requireNonNull(rgb_buffer_processed_clamped, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer_processed_clamped;

    return new Image(this);
  }

  @Override
  public ImageInterface reduceColorDensity(int noOfColorsToReduceTo, Boolean isDitheringRequired) {

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    ReducingColorDensityInterface colorDensity = new ReducingColorDensity();

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    int[][][] rgb_buffer_processed = colorDensity.doReduceColorDensity(image_buffer,
        noOfColorsToReduceTo, isDitheringRequired);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    int[][][] rgb_buffer_processed_clamped = Clamping.doClamping(rgb_buffer_processed);

    Objects.requireNonNull(rgb_buffer_processed_clamped, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer_processed_clamped;

    return new Image(this);
  }

  @Override
  public ImageInterface mosaic(int noOfSeeds) {

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    MosaicInterface mosaic = new Mosaic();

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    int[][][] rgb_buffer_processed = mosaic.doMosaic(image_buffer, noOfSeeds);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer_processed;

    return new Image(this);
  }

  @Override
  public ImageInterface pixelate(int noOfSquaresAcross) {

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    PixelateInterface pixelate = new Pixelate();

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    int[][][] rgb_buffer_processed = pixelate.doPixelate(image_buffer, noOfSquaresAcross);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer_processed;

    return new Image(this);
  }

  @Override
  public ImageInterface pattern() throws IOException {
    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    PixelateInterface pixelate = new Pixelate();

    PatternInterface pattern = new Pattern();

    int noOfSquaresAcross = ImageOperationsUtility.getNoofSquaresAcrossPattern();

    int[][][] rgb_buffer_processed = pixelate.doPixelate(image_buffer, noOfSquaresAcross);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    String generated_pattern = pattern.doPattern(rgb_buffer_processed, noOfSquaresAcross);

    Objects.requireNonNull(generated_pattern, "Generated pattern value cannot be null.");

    this.pattern = generated_pattern;

    return new Image(this);
  }

  @Override
  public ImageInterface savePattern(String fileName) throws IOException {
    Objects.requireNonNull(fileName, "Filepath cannot be null");
    Objects.requireNonNull(this.pattern, "Please generate Pattern first.");

    ImageOperationsUtility.savePattern(this.pattern, fileName);

    return new Image(this);
  }
}
