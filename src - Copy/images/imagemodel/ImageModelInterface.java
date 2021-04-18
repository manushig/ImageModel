package images.imagemodel;

import java.io.IOException;
import java.util.List;

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
 * <li>Image Chunking - A image can be "broken down" into mosaic shapes, by
 * choosing a set of points in the image (called seeds). Another way is chunking
 * the image into regular squares across the rows and columns.This chunking
 * method produces what many think of as an equivalent to pixelating the image.
 * <li>Pattern Generation - It generates the chunked image to makes it possible
 * to convert an image that has many pixels into one that has fewer pixels
 * without actually changing the number of colors that the image uses. It
 * generates the cross-stitch pattern from an image.
 * </ul>
 * 
 */
public interface ImageModelInterface extends ImageSubject, PatternSubject{

  /**
   * This method loads the image from the given file path.
   * 
   * @param filename It is the file name which needs to be loaded
   * @return an ImageModel object
   * @throws IOException if there is an error reading the file
   */
  public ImageModelInterface loadImage(String filename) throws IOException;

  /**
   * This method saves the image at the given file path.
   * 
   * @param filename It is the file name which needs to be saved
   * @return an ImageModelInterface object
   * @throws IOException IOException if file/directory not found
   */
  public ImageModelInterface saveImage(String filename) throws IOException;

  /**
   * This method applies gray scale color transformation on the image. It modifies
   * the color of a pixel based on its own color.
   * 
   * @return an ImageModelInterface object
   */
  public ImageModelInterface grayScale();

  /**
   * This method applies sepia color transformation on the image. It modifies the
   * color of a pixel based on its own color.
   * 
   * @return an ImageModel object
   */
  public ImageModelInterface sepia();

  /**
   * This method applies blur filtering on the image. It computes the filter
   * algorithm to every channel of every pixel.
   * 
   * @return an ImageModelInterface object
   */
  public ImageModelInterface blur();

  /**
   * This method applies sharpen filtering on the image. It computes the filter
   * algorithm to every channel of every pixel.
   * 
   * @return an ImageModelInterface object
   */
  public ImageModelInterface sharpen();

  /**
   * This method reduces the color density on the image. It transforms the colors
   * in an image is to reduce the number of colors in the image.
   * 
   * @param noOfColorsToReduceTo It is the number of colors to be reduced to
   * @return an ImageModelInterface object
   * 
   */
  public ImageModelInterface dither(int noOfColorsToReduceTo);

  /**
   * This method simulates the mosaics on the image. It chooses a set of points in
   * the image (called seeds). Each pixel in the image is then paired to the seed
   * that is closest to it (by distance). This creates a cluster of pixels for
   * each seed. The color of each pixel in the image is replaced with the color of
   * its seed pixel.
   * 
   * @param noOfSeeds It is the number of seeds i.e set of points to be selected
   * @return an ImageModelInterface object
   * 
   */
  public ImageModelInterface mosaic(int noOfSeeds);

  /**
   * This method chunks the image into regular squares across the rows and
   * columns. This chunking method produces what many think of as an equivalent to
   * pixelating the image.
   * 
   * @param noOfSquaresAcross It is the number of squares across the width of the
   *                          image
   * @return an ImageModelInterface object
   * 
   */

  public ImageModelInterface pixelate(int noOfSquaresAcross);

  /**
   * This method generates the chunked image to makes it possible to convert an
   * image that has many pixels into one that has fewer pixels without actually
   * changing the number of colors that the image uses. It generates the
   * cross-stitch pattern from an image.
   * 
   * 
   * @return an ImageModelInterface object
   * @throws IOException if there is an error while DMC Floss RGB values from the
   *                     file.
   */
  public ImageModelInterface pattern1() throws IOException;

  /**
   * This method saves the cross-stitch pattern to the file.
   * 
   * @param fileName It is the file name where pattern needs to be printed.
   * @return an ImageInterface object
   * @throws IOException is there is an error while writing the pattern to the
   *                     file.
   */
  public ImageModelInterface savePattern(String fileName) throws IOException;

  public ImageModelInterface pattern() throws IOException;

  // public void notifyPatternObservers();
  public ImageModelInterface patternRemoveColor(String dmcCode) throws IOException;

  public ImageModelInterface patternReplaceColor(int xCordinate, int yCordinate, String dmcCode)
      throws IOException;

  public ImageModelInterface patternAddText(String text, String dmcCode) throws IOException;

  public List<DmcFloss> getDmcFlossColors() throws IOException;

  public ImageModelInterface patternAddNewColors(List<String> selectedColors) throws IOException;

  public List<SymbolCordinates> patternGetCordinatesForSymbol() throws IOException;
}
