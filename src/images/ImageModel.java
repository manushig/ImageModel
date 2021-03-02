package images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * ImageModel class implements the ImageModelInterface interface that can be
 * used to manipulate images to produce some interesting effects.
 */
public class ImageModel implements ImageModelInterface {

  private int[][][] rgb_buffer;
  private int width;
  private int height;
  private ArrayList<ImageObserver> imageObservers;

  /**
   * Constructs a ImageModelImp.
   *
   */

  public ImageModel() {
    this.rgb_buffer = null;
    this.width = 0;
    this.height = 0;
    imageObservers = new ArrayList<ImageObserver>();
  }

  /**
   * Constructs a Copy constructor for ImageModelImp class.
   * 
   */

  public ImageModel(ImageModel imgModel) {
    this.rgb_buffer = imgModel.rgb_buffer;
  }

  @Override
  public String toString() {
    // Fix - rgb buffer printing
    return String.format("Height - %d\nWidth - %d\nBuffered Image Pixels - %s\nObservers - %s",
        this.height, this.width, this.rgb_buffer.toString(), this.imageObservers.toString());

  }

  @Override
  public int[][][] getPixels() {
    ImageModel img = new ImageModel(this);
    return img.rgb_buffer;
  }

  @Override
  public void setPixels(int[][][] rgbBuffer) {
    this.rgb_buffer = rgbBuffer;
  }

  @Override
  public ImageModelInterface loadImage(String filepath) throws IOException {
    Objects.requireNonNull(filepath, "Filepath cannot be null");

    ImageProcessingInterface imgProcessing = new ImageProcessing();

    this.rgb_buffer = imgProcessing.readImage(filepath);
    this.height = imgProcessing.getHeight(filepath);
    this.width = imgProcessing.getWidth(filepath);

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface saveImage(String filepath) throws IOException {
    Objects.requireNonNull(filepath, "Filepath cannot be null");

    ImageProcessingInterface imgProcessing = new ImageProcessing();

    imgProcessing.writeImage(this.rgb_buffer, this.width, this.height, filepath);

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface colorTransformation(float[][] colorTransformedMatrix) {
    Objects.requireNonNull(colorTransformedMatrix, "Color Transformed matrix cannot not be null");

    ColorTransformationInterface colorTransformation = new ColorTransformation();

    this.rgb_buffer = colorTransformation.doColorTransformation(this.height, this.width,
        this.rgb_buffer, colorTransformedMatrix);
    return new ImageModel(this);
  }

  @Override
  public void registerImageObserver(ImageObserver imageObserver) {
    imageObservers.add(imageObserver);
  }

  @Override
  public void removeImageObserver(ImageObserver imageObserver) {
    int i = imageObservers.indexOf(imageObserver);
    if (i >= 0) {
      imageObservers.remove(i);
    }
  }

  @Override
  public void notifyImageObservers() {
    for (int i = 0; i < imageObservers.size(); i++) {
      ImageObserver observer = (ImageObserver) imageObservers.get(i);
      observer.updateImage();
    }
  }

}
