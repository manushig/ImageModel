package images.imagemodel;

/**
 * FilterInterface, it is an interface that defines methods to do filter
 * manipulation on the image. Given a pixel in the image and a channel, the
 * result of the filter can be computed for that pixel and channel.Filtering
 * modifies the value of a pixel depending on the values of its neighbors.
 * Filtering is applied separately to each channel.
 * <ul>
 * <li>Blurring can be done by applying this filter to every channel of every
 * pixel to produce the output image.
 * <li>Image sharpening is in some ways, the opposite of blurring. Sharpening
 * accentuates edges (the boundaries between regions of high contrast), thereby
 * giving the image a "sharper" look.
 * </ul>
 */
public interface FilterInterface {
  /**
   * This method computes the filter algorithm to every channel of every pixel.
   * 
   * @param rgbBuffer It is a 2D array of RGB colors
   * @param kernel    It is a 2D array of numbers, having odd dimensions
   * @return processed 2D array of RGB colors
   */
  public int[][][] doFilter(int[][][] rgbBuffer, float[][] kernel);
}
