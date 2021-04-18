package images.imagemodel;

/**
 * PixelateInterface , it is an interface that defines methods to generate
 * pixelate effect on the image.
 * 
 * <ul>
 * <li>It chunks the image into regular squares across the rows and columns.
 * This chunking method produces what many think of as an equivalent to
 * pixelating the image.
 * <li>The big difference between this effect and resizing the image is that the
 * resulting image is the same size as the original image, and the color of each
 * pixel in the original image is replaced with the color of the pixel at the
 * center of its square creating super-pixels.
 * <li>Decide how many super-pixels we want to have across the width of the
 * image and then create evenly sized squares across.
 * <li>Each super-pixel should be no more than one pixel different in width
 * and/or height of another super-pixel in the image
 * </ul>
 * 
 */
public interface PixelateInterface {
  /**
   * This method chunks the image into regular squares across the rows and
   * columns. This chunking method produces what many think of as an equivalent to
   * pixelating the image.
   * 
   * @param rgbBuffer         It is a 2D array of RGB colors.
   * @param noOfSquaresAcross It is the number of evenly sized squares.
   * @return processed 2D array of RGB colors
   */
  public int[][][] doPixelate(int[][][] rgbBuffer, int noOfSquaresAcross);
}
