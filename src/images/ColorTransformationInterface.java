package images;

/**
 * ColorTransformationInterface, it is an interface that defines methods to do
 * color transformation manipulation on the image.A color transformation
 * modifies the color of a pixel based on its own color.
 *
 */
public interface ColorTransformationInterface {
  /**
   * This method modifies the color of a pixel based on its own color.
   * 
   * @param height                 It is the height of the image
   * @param width                  It is the width of the image
   * @param rgbBuffer              It is a 2D array of RGB colors
   * @param colorTransformedMatrix It is a matrix representation of the linear
   *                               color transformations
   * @return processed 2D array of RGB colors
   */
  public int[][][] doColorTransformation(int height, int width, int[][][] rgbBuffer,
      float[][] colorTransformedMatrix);
}
