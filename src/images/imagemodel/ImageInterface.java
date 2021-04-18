package images.imagemodel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * ImageInterface, it is an interface that can be used to manipulate images to
 * produce some interesting effects.
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
 * <li>Pattern Generation - By pixelating the image and then map each
 * super-pixel in the image to a floss color by calculating the "closest" color
 * of available floss, a cross-stitch pattern from an image can be generated.
 * <li>Exchange color on pattern - It provides the ability to exchange one color
 * for another in a cross-stitch pattern by clicking on the color in a displayed
 * pattern and allowing the user to select a different color from the DMC color
 * options.
 * <li>Remove color on pattern - It provide the ability to pick one color in a
 * cross-stitch pattern that will then be removed from the pattern completely.
 * The pixels of that color would be replaced with a blank pixel.
 * <li>Display symbols on pattern - It provides the ability to display the
 * cross-stitch pattern to the screen, display both the symbol and the DMC floss
 * color at the same time.
 * <li>Generate pattern with new colors - It provides the ability to select the
 * DMC color palette to use in an image. Do this by providing the ability for
 * the user to select the DMC color thread colors that they have on hand and
 * then substitute each color in the actual pattern with one that the user has
 * indicated that they have on hand.
 * <li>Add text on pattern - It provides the user with the ability to add a line
 * of text to any cross-stitch pattern using a cross-stitch friendly alphabet.
 * </ul>
 * 
 */
public interface ImageInterface {

  /**
   * This method loads the image from the given file path.
   * 
   * @param filename It is the file name which needs to be loaded
   * @return an ImageInterface object
   * @throws IOException if there is an error reading the file
   */
  public ImageInterface loadImage(String filename) throws IOException;

  /**
   * This method saves the image at the given file path.
   * 
   * @param filename It is the file name which needs to be saved
   * @return an ImageInterface object
   * @throws IOException IOException if file/directory not found
   */
  public ImageInterface saveImage(String filename) throws IOException;

  /**
   * This method applies gray scale color transformation on the image. It modifies
   * the color of a pixel based on its own color.
   * 
   * @return an ImageInterface object
   */
  public ImageInterface colorTransformation(float[][] colorTransformedMatrix);

  /**
   * This method applies filtering on the image. It computes the filter algorithm
   * to every channel of every pixel.
   * 
   * @param kernel It is a 2D array of numbers, having odd dimensions
   * @return an ImageInterface object
   */
  public ImageInterface filter(float[][] kernel);

  /**
   * This method reduces the color density on the image. It transforms the colors
   * in an image is to reduce the number of colors in the image.
   * 
   * @param noOfColorsToReduceTo It is the number of colors to be reduced to
   * @param isDitheringRequired  It is a check whether dithering to be performed
   *                             or not.
   * @return an ImageInterface object
   * 
   */
  public ImageInterface reduceColorDensity(int noOfColorsToReduceTo, Boolean isDitheringRequired);

  /**
   * This method simulates the mosaics on the image. It chooses a set of points in
   * the image (called seeds). Each pixel in the image is then paired to the seed
   * that is closest to it (by distance). This creates a cluster of pixels for
   * each seed. The color of each pixel in the image is replaced with the color of
   * its seed pixel.
   * 
   * @param noOfSeeds It is the number of seeds i.e set of points to be selected
   * @return an ImageInterface object
   * 
   */
  public ImageInterface mosaic(int noOfSeeds);

  /**
   * This method chunks the image into regular squares across the rows and
   * columns. This chunking method produces what many think of as an equivalent to
   * pixelating the image.
   * 
   * @param noOfSquaresAcross It is the number of squares across the width of the
   *                          image
   * @return an ImageInterface object
   * 
   */

  public ImageInterface pixelate(int noOfSquaresAcross);

  /**
   * This method pixelate the image and then and then map each super-pixel in the
   * image to a floss color by calculating the "closest" color of available floss.
   * 
   * @return an ImageInterface object
   * @throws IOException if there is an error while DMC Floss RGB values from the
   *                     file.
   */
  public ImageInterface pattern() throws IOException;

  /**
   * This method saves the cross-stitch pattern to the file.
   * 
   * @param fileName It is the file name where pattern needs to be printed.
   * @return an ImageInterface object
   * @throws IOException if file/directory not found
   */
  public ImageInterface savePattern(String fileName) throws IOException;

  /**
   * It retrieves the list of legends of the generated cross-stitch pattern.
   * 
   * @return an ImageInterface object
   */
  public List<Legend> getPatternLegend();

  /**
   * It retrieves the buffered image.
   * 
   * @return Buffered image
   */
  public BufferedImage getImage();

  /**
   * Remove the given color from the pattern.
   * 
   * @param dmcCode dmc code color to remove from the pattern.
   * @return an ImageInterface object
   * @throws IOException if file/directory not found
   */
  public ImageInterface patternRemoveColor(String dmcCode) throws IOException;

  /**
   * It exchanges one color for another given color in a cross-stitch pattern at
   * given coordinates.
   * 
   * @param xCordinate x-coordinate of the image clicked.
   * @param yCordinate y-coordinate of the image clicked.
   * @param dmcCode    DMC code color to replace to
   * @return an ImageInterface object
   * @throws IOException if file/directory not found
   */
  public ImageInterface patternReplaceColor(int xCordinate, int yCordinate, String dmcCode)
      throws IOException;

  /**
   * It adds a line of text to cross-stitch pattern.
   * 
   * @param text    The text to be displayed onto the image
   * @param dmcCode Color in which text to be displayed
   * @return an ImageInterface object
   * @throws IOException if file/directory not found
   */
  public ImageInterface patternAddText(String text, String dmcCode) throws IOException;

  /**
   * It substitutes each color in the actual pattern with given list of colors.
   * 
   * @param selectedColors List of colors to be displaced
   * @return an ImageInterface object
   * @throws IOException if file/directory not found
   */
  public ImageInterface patternAddNewColor(List<String> selectedColors) throws IOException;

  /**
   * It provides the coordinates and the symbol to be displayed on the pattern.
   * 
   * @return List of SymbolCordinates object, having coordinates and alphabet to
   *         be displayed information.
   * @throws IOException if file/directory not found
   */
  public List<SymbolCordinates> patternGetCoordinatesForSymbol() throws IOException;
}
