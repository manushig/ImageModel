package images.imagemodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * ImageModel class implements the ImageModelInterface interface that can be
 * used to manipulate images to produce some interesting effects.
 */
public class ImageModel implements ImageModelInterface {

  private ImageInterface imageObj;
  private ArrayList<ImageObserver> imageObservers;

  /**
   * Constructs a ImageModel.
   *
   */

  public ImageModel() {
    this.imageObj = new Image();
    imageObservers = new ArrayList<>();
  }

  /**
   * Constructs a Copy constructor for ImageModelImp class.
   * 
   */

  public ImageModel(ImageModel imgModel) {
    this.imageObj = imgModel.imageObj;

    this.imageObservers = new ArrayList<>();
    for (int i = 0; i < imgModel.imageObservers.size(); i++) {
      this.imageObservers.add(imgModel.imageObservers.get(i));
    }
  }

  @Override
  public ImageModelInterface loadImage(String filename) throws IOException {
    Objects.requireNonNull(filename, "Filepath cannot be null");

    ImageInterface imageData = this.imageObj.loadImage(filename);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface saveImage(String filename) throws IOException {
    Objects.requireNonNull(filename, "Filepath cannot be null.");

    ImageInterface imageData = this.imageObj.saveImage(filename);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface grayScale() {
    float[][] colorTransformedMatrix = ImageOperationsUtility.getGrayScaleMatrix();

    Objects.requireNonNull(colorTransformedMatrix, "Color Transformed matrix cannot not be null");

    ImageInterface imageData = this.imageObj.colorTransformation(colorTransformedMatrix);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface sepia() {
    float[][] colorTransformedMatrix = ImageOperationsUtility.getSepiaToneMatrix();

    Objects.requireNonNull(colorTransformedMatrix, "Color Transformed matrix cannot not be null");

    ImageInterface imageData = this.imageObj.colorTransformation(colorTransformedMatrix);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface blur() {
    float[][] kernel = ImageOperationsUtility.getBlurKernel();

    Objects.requireNonNull(kernel, "Kernel value cannot not be null.");

    ImageInterface imageData = this.imageObj.filter(kernel);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface sharpen() {
    float[][] kernel = ImageOperationsUtility.getSharpKernel();

    Objects.requireNonNull(kernel, "Kernel value cannot not be null.");

    ImageInterface imageData = this.imageObj.filter(kernel);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface dither(int noOfColorsToReduceTo) {

    ImageInterface imageData = this.imageObj.reduceColorDensity(noOfColorsToReduceTo, true);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

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

  @Override
  public ImageModelInterface mosaic(int noOfSeeds) {
    ImageInterface imageData = this.imageObj.mosaic(noOfSeeds);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface pixelate(int noOfSquaresAcross) {
    ImageInterface imageData = this.imageObj.pixelate(noOfSquaresAcross);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface pattern() throws IOException {
    ImageInterface imageData = this.imageObj.pattern();

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface savePattern(String fileName) throws IOException {

    Objects.requireNonNull(fileName, "Filepath cannot be null.");

    ImageInterface imageData = this.imageObj.savePattern(fileName);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

}
