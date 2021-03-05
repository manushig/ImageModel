package images.imagemodel;

import java.io.IOException;

/**
 * ImageModelInterface, it is an interface that can be used to manipulate images
 * to produce some interesting effects.
 * 
 * <ul>
 * <li>Loading and Saving Images - It allow to read and write actual image files
 * in a variety of standard image formats (jpg, bmp, png, etc) using 3D arrays.
 * <li>Filtering Images - Given a pixel in the image and a channel, the result
 * of the filter can be computed for that pixel and channel.
 * <li>Color transformations - A color transformation modifies the color of a
 * pixel based on its own color.
 * <li>Reducing color density - To transform the colors in an image is to reduce
 * the number of colors in the image.
 * </ul>
 * 
 */
public interface ImageModelInterface {

  /**
   * This method loads the image from the given file path.
   * 
   * @param filepath It is the file path from where image needs to be loaded
   * @return an ImageModel object
   * @throws IOException if there is an error reading the file
   */
  public ImageModelInterface loadImage(String filepath) throws IOException;

  /**
   * This method saves the image at the given file path.
   * 
   * @param filepath It is the file path from where image needs to be saved
   * @return an ImageModel object
   * @throws IOException IOException if file/directory not found
   */
  public ImageModelInterface saveImage(String filepath) throws IOException;

  /**
   * This method applies color transformation on the image. It modifies the color
   * of a pixel based on its own color.
   * 
   * @param colorTransformedMatrix It is the matrix form of the Linear color
   *                               transformations
   * @return an ImageModel object
   */
  public ImageModelInterface colorTransformation(float[][] colorTransformedMatrix);

  /**
   * This method applies filtering on the image. It computes the filter algorithm
   * to every channel of every pixel.
   * 
   * @param kernel It is a 2D array of numbers, having odd dimensions
   * @return an ImageModel object
   */
  public ImageModelInterface filter(float[][] kernel);

  /**
   * This method reduces the color density on the image. It transforms the colors
   * in an image is to reduce the number of colors in the image.
   * 
   * @param noOfColorsToReduceTo It is the number of colors to be reduced to
   * @param ditheringRequired    It is a check whether dithering to be performed
   *                             or not.
   * @return an ImageModel object
   * 
   */
  public ImageModelInterface reduceColorDensity(int noOfColorsToReduceTo,
      Boolean isDitheringRequired);

  /**
   * This method allows objects to register as observer for state changes.
   * 
   * @param imageObserver It is an objects which wants to register for state
   *                      changes.
   */
  public void registerImageObserver(ImageObserver imageObserver);

  /**
   * This method allows objects to be removed as observer.
   * 
   * @param imageObserver It is an objects which wants to remove as observer
   */
  public void removeImageObserver(ImageObserver imageObserver);

  /**
   * This method notifies observers if there is any state change.
   */
  public void notifyImageObservers();
}
