package images.imagemodel;

/**
 * MosaicInterface , it is an interface that defines methods to generate mosaic
 * effect on the image.
 * <ul>
 * <li>It chunks the image into regular squares across the rows and columns.
 * This chunking method produces what many think of as an equivalent to
 * pixelating the image.
 * <li>The seeds can be chosen in a number of ways. The simplest method (that
 * you will implement) is to choose the seeds randomly.
 * </ul>
 * 
 */
public interface MosaicInterface {
  /**
   * This method chunks the image into regular squares across the rows and
   * columns. This chunking method produces what many think of as an equivalent to
   * pixelating the image.
   * 
   * @param rgbBuffer It is a 2D array of RGB colors
   * @param noOfSeeds it is the number of seeds to be choosen randomnly.
   * @return processed 2D array of RGB colors
   */
  public int[][][] doMosaic(int[][][] rgbBuffer, int noOfSeeds);
}
