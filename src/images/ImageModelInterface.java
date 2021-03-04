package images;

import java.io.IOException;

/**
 * ImageModelInterface, it is an interface that can be used to manipulate images
 * to produce some interesting effects.
 * <ul>
 * <li>Loading and Saving Images
 * <li>Filtering Images
 * <li>Color transformations
 * <li>Reducing color density
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
   * This method applies color transformation on the image.
   * 
   * @param colorTransformedMatrix It is the matrix form of the Linear color
   *                               transformations
   * @return an ImageModel object
   */
  public ImageModelInterface colorTransformation(float[][] colorTransformedMatrix);

  public ImageModelInterface filter(float[][] kernel);

  public ImageModelInterface reduceColorDensity(int noOfColorsToReduceTo);

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
