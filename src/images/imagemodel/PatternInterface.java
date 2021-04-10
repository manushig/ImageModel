package images.imagemodel;

import java.io.IOException;
import java.util.List;

import javafx.util.Pair;

/**
 * PatternInterface , it is an interface that defines methods to generate
 * cross-stitch pattern from the image.
 * 
 * <ul>
 * <li>It map each super-pixel in the image to a floss color by calculating the
 * "closest" color of available floss.
 * <li>It calculates the distance between two colors by using redmean formula.
 * <li>The result of creating a image pattern is text. Each pattern should have
 * 3 parts:
 * <li>The size of the pattern expressed a the width and height.
 * <li>An image map that consists of an n x m grid of symbols where each symbol
 * represents the single floss color that should be used to stitch that
 * super-pixel.
 * <li>A legend that lists the DMC floss used in the pattern in numeric order
 * along with the symbol used to represent that floss color in the image map.
 * </ul>
 * 
 */
public interface PatternInterface {
  /**
   * This method map each super-pixel in the image to a floss color by calculating
   * the "closest" color of available floss and generates the cross-stitch
   * pattern.
   * 
   * @param rgbBuffer         It is a 2D array of RGB colors.
   * @param noOfSquaresAcross It is the number of evenly sized squares.
   * @return processed 2D array of RGB colors
   * @throws IOException if an error generated while reading the DMC Floss RGB
   *                     conversion values.
   */
  public String doPattern(int[][][] rgbBuffer, int noOfSquaresAcross) throws IOException;

  public Pair<int[][][], List<Legend>> doUIPattern(int[][][] rgbBuffer, int noOfSquaresAcross) throws IOException;

}
