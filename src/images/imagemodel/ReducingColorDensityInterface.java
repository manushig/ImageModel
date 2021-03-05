package images.imagemodel;

/**
 * ReducingColorDensityInterface, it is an interface that defines methods to
 * reduce color density.To transform the colors in an image, it reduce the
 * number of colors in the image.The technique for preserving the essence of the
 * image breaks down an image that has many colors into an image that is made of
 * dots from just a few colors is known as dithering.
 */
public interface ReducingColorDensityInterface {

  /**
   * This method applies Floyd-Steinberg algorithm to dither an image.
   * 
   * @param rgbBuffer              It is a 2D array of RGB colors
   * @param maxNoOfColorsInChannel It is the number of colors to be reduced to an
   *                               ImageModel object
   * @param ditheringRequired      It is a check whether dithering to be performed
   *                               or not.
   * @return processed 2D array of RGB colors
   */
  public int[][][] doReduceColorDensity(int[][][] rgbBuffer, int noOfColorsToReduceTo,
      Boolean isDitheringRequired);
}
