package images;

import java.io.IOException;

/**
 * ImageProcessingInterface, it is an interface that has methods to read an
 * image from file and write to a file.
 */
public interface ImageProcessingInterface {

  /**
   * This method reads an image from a file and return it as a 2D array of RGB
   * colors.
   * 
   * @param filepath It is the file path from where image needs to be loaded
   * @return a 2D array of RGB colors
   * @throws IOException if there is an error reading the file
   */
  public int[][][] readImage(String filepath) throws IOException;

  /**
   * Write the content of a 2D array of RGB colors to a file.
   * 
   * @param rgbBuffer The 2D array of RGB values that will be written to the file
   * @param width     The width of the image to be written
   * @param height    The height of the image to be written
   * @param filepath  The name of the file containing the image to read
   * @throws IOException if there is an error reading the file
   */
  public void writeImage(int[][][] rgbBuffer, int width, int height, String filepath)
      throws IOException;

  /**
   * The width of the image in a file.
   * 
   * @param filepath The name of the file containing an image.
   * @return The width of the image contained in the file.
   * @throws IOException if there is an error reading the file
   */
  public int getWidth(String filepath) throws IOException;

  /**
   * The height of the image in a file.
   * 
   * @param filepath The name of the file containing an image.
   * @return The height of the image contained in the file.
   * @throws IOException if there is an error reading the file
   */
  public int getHeight(String filepath) throws IOException;
}
