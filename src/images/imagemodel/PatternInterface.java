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
   * @return processed 2D array of RGB colors and the legend list
   * @throws IOException if an error generated while reading the DMC Floss RGB
   *                     conversion values.
   */

  public Pair<int[][][], List<Legend>> doPattern(int[][][] rgbBuffer, int noOfSquaresAcross)
      throws IOException;

  /**
   * This method exchange one color for another given color in a cross-stitch
   * pattern at given coordinates.
   * 
   * @param rgbBuffer         It is a 2D array of RGB colors.
   * @param noOfSquaresAcross It is the number of evenly sized squares.
   * @param XCordinate        x-coordinate of the image.
   * @param YCordinate        y-coordinate of the image.
   * @param dmcCode           dmc floss code of the color
   * @param legendList        It is the list of legends
   * @return processed 2D array of RGB colors and the legend list
   */
  public Pair<int[][][], List<Legend>> doReplaceColorPattern(int[][][] rgbBuffer,
      int noOfSquaresAcross, int XCordinate, int YCordinate, String dmcCode,
      List<Legend> legendList);

  /**
   * 
   * This method removes the given dmc code from the legend and replace it with
   * Blank and represents it as dot on the pattern.
   * 
   * @param noOfSquaresAcross It is the number of evenly sized squares.
   * @param dmcCode           dmc floss code of the color
   * @param legendList        It is the list of legends
   * @return list of Legend
   */
  public List<Legend> doRemoveColorPattern(int noOfSquaresAcross, String dmcCode,
      List<Legend> legendList);

  /**
   * This method adds a line of text to cross-stitch pattern in a given color.
   * 
   * @param rgbBuffer         It is a 2D array of RGB colors.
   * @param noOfSquaresAcross It is the number of evenly sized squares.
   * @param text              text to be added to the pattern
   * @param dmcCode           dmc floss code of the color
   * @param legendList        It is the list of legends
   * @return processed 2D array of RGB colors and the legend list
   * @throws IOException
   */
  public Pair<int[][][], List<Legend>> doAddTextPattern(int[][][] rgbBuffer, int noOfSquaresAcross,
      String text, String dmcCode, List<Legend> legendList) throws IOException;

  /**
   * This method substitute each color in the actual pattern with given list of
   * colors.
   * 
   * @param rgbBuffer      It is a 2D array of RGB colors.
   * @param selectedColors It is the number of evenly sized squares.
   * @param legendList     It is the list of legends
   * @return processed 2D array of RGB colors and the legend list
   */
  public Pair<int[][][], List<Legend>> doAddNewColorsPattern(int[][][] rgbBuffer,
      List<String> selectedColors, List<Legend> legendList);

  /**
   * This method generates the coordinates and the symbol to be displayed on the
   * pattern.
   * 
   * @param rgbBuffer         It is a 2D array of RGB colors.
   * @param legendlist        It is the list of legends
   * @param noOfSquaresAcross It is the number of evenly sized squares.
   * @return List of SymbolCordinates
   */
  public List<SymbolCordinates> getCordinatesForSymbol(int[][][] rgbBuffer, List<Legend> legendlist,
      int noOfSquaresAcross);

  /**
   * This method generate the cross pattern in a text format.
   * 
   * @param rgbBuffer         It is a 2D array of RGB colors.
   * @param noOfSquaresAcross It is the number of evenly sized squares.
   * @param legendList        It is the list of legends
   * @return generated pattern in text format
   */
  public String savePattern(int[][][] rgbBuffer, int noOfSquaresAcross, List<Legend> legendList);
}
