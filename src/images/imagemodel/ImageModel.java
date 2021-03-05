package images.imagemodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * ImageModel class implements the ImageModelInterface interface that can be
 * used to manipulate images to produce some interesting effects.
 */
public class ImageModel implements ImageModelInterface {

  private int[][][] rgb_buffer;
  private int[][][] rgb_buffer_processed;
  private ArrayList<ImageObserver> imageObservers;

  /**
   * Constructs a ImageModelImp.
   *
   */

  public ImageModel() {
    this.rgb_buffer = null;
    this.rgb_buffer_processed = null;
    imageObservers = new ArrayList<>();
  }

  /**
   * Constructs a Copy constructor for ImageModelImp class.
   * 
   */

  public ImageModel(ImageModel imgModel) {
    if (!Objects.isNull(imgModel.rgb_buffer)) {
      this.rgb_buffer = new int[imgModel.rgb_buffer.length][imgModel.rgb_buffer[0].length][imgModel.rgb_buffer[0][0].length];
      for (int x = 0; x < imgModel.rgb_buffer.length; x++) {
        for (int y = 0; y < imgModel.rgb_buffer[0].length; y++) {
          for (int z = 0; z < imgModel.rgb_buffer[0][0].length; z++) {
            this.rgb_buffer[x][y][z] = imgModel.rgb_buffer[x][y][z];
          }
        }
      }
    } else {
      this.rgb_buffer = null;
    }
    if (!Objects.isNull(imgModel.rgb_buffer_processed)) {
      this.rgb_buffer_processed = new int[imgModel.rgb_buffer_processed.length][imgModel.rgb_buffer_processed[0].length][imgModel.rgb_buffer_processed[0][0].length];
      for (int x = 0; x < imgModel.rgb_buffer_processed.length; x++) {
        for (int y = 0; y < imgModel.rgb_buffer_processed[0].length; y++) {
          for (int z = 0; z < imgModel.rgb_buffer_processed[0][0].length; z++) {
            this.rgb_buffer_processed[x][y][z] = imgModel.rgb_buffer_processed[x][y][z];
          }
        }
      }
    } else {
      this.rgb_buffer_processed = null;
    }

    this.imageObservers = new ArrayList<>();
    for (int i = 0; i < imgModel.imageObservers.size(); i++) {
      this.imageObservers.add(imgModel.imageObservers.get(i));
    }
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
  public ImageModelInterface loadImage(String filepath) throws IOException {
    Objects.requireNonNull(filepath, "Filepath cannot be null");

    int[][][] rgb_buffer = ImageUtilities.readImage(filepath);

    Objects.requireNonNull(rgb_buffer, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface saveImage(String filepath) throws IOException {
    Objects.requireNonNull(filepath, "Filepath cannot be null.");

    ImageModel imgModel = new ImageModel(this);

    ImageUtilities.writeImage(imgModel.rgb_buffer_processed,
        imgModel.rgb_buffer_processed[0].length, imgModel.rgb_buffer_processed.length, filepath);

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface colorTransformation(float[][] colorTransformedMatrix) {
    Objects.requireNonNull(colorTransformedMatrix, "Color Transformed matrix cannot not be null");

    ImageModel imgModel = new ImageModel(this);

    ColorTransformationInterface colorTransformation = new ColorTransformation();

    int[][][] rgb_buffer_processed = colorTransformation.doColorTransformation(imgModel.rgb_buffer,
        colorTransformedMatrix);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    int[][][] rgb_buffer_processed_clamped = Clamping.doClamping(rgb_buffer_processed);

    Objects.requireNonNull(rgb_buffer_processed_clamped, "2 D RGB array value cannot be null.");

    this.rgb_buffer_processed = rgb_buffer_processed_clamped;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface filter(float[][] kernel) {
    Objects.requireNonNull(kernel, "Kernel value cannot not be null.");

    ImageModel imgModel = new ImageModel(this);

    FilterInterface filter = new Filter();

    int[][][] rgb_buffer_processed = filter.doFilter(imgModel.rgb_buffer, kernel);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    int[][][] rgb_buffer_processed_clamped = Clamping.doClamping(rgb_buffer_processed);

    Objects.requireNonNull(rgb_buffer_processed_clamped, "2 D RGB array value cannot be null.");

    this.rgb_buffer_processed = rgb_buffer_processed_clamped;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface reduceColorDensity(int noOfColorsToReduceTo,
      Boolean isDitheringRequired) {
    ImageModel imgModel = new ImageModel(this);

    ReducingColorDensityInterface colorDensity = new ReducingColorDensity();

    int[][][] rgb_buffer_processed = colorDensity.doReduceColorDensity(imgModel.rgb_buffer,
        noOfColorsToReduceTo, isDitheringRequired);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");
    
    int[][][] rgb_buffer_processed_clamped = Clamping.doClamping(rgb_buffer_processed);

    Objects.requireNonNull(rgb_buffer_processed_clamped, "2 D RGB array value cannot be null.");

    this.rgb_buffer_processed = rgb_buffer_processed_clamped;

    return new ImageModel(this);
  }

  @Override
  public void registerImageObserver(ImageObserver imageObserver)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This feature is not available");
  }

  @Override
  public void removeImageObserver(ImageObserver imageObserver)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This feature is not available");
  }

  @Override
  public void notifyImageObservers() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This feature is not available");
  }

}
