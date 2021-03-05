package images.imagemodel;

/**
 * ColorTransformationInterface, it is an interface that defines methods to do
 * color transformation manipulation on the image.A color transformation
 * modifies the color of a pixel based on its own color.
 *
 * <ul>
 * <li>A simple operation is to convert a color image into a greyscale image. A
 * greyscale image is composed only of shades of grey (if the red, green and
 * blue values are the same, it is a shade of grey).
 * <li>Photographs taken in the 19th and early 20th century had a characteristic
 * reddish brown tone. This is referred to as a sepia tone.
 * </ul>
 */
public interface ColorTransformationInterface {
  /**
   * This method modifies the color of a pixel based on its own color.
   * 
   * @param rgbBuffer              It is a 2D array of RGB colors
   * @param colorTransformedMatrix It is a matrix representation of the linear
   *                               color transformations
   * @return processed 2D array of RGB colors
   */
  public int[][][] doColorTransformation(int[][][] rgbBuffer, float[][] colorTransformedMatrix);
}
