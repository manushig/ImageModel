package images.imagecontrol;

import java.util.List;

/**
 * This interface represents a set of features that the program offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller. How
 * the view uses them as callbacks is completely up to how the view is designed
 * (e.g. it could use them as a callback for a button, or a callback for a
 * dialog box, or a set of text inputs, etc.)
 *
 * <p>
 * Each function is designed to take in the necessary data to complete that
 * functionality.
 * </p>
 */
public interface Features {
  /**
   * Load the image from the given path.
   * 
   * @param fileName It is the filename of the image to be loaded
   */
  public void loadImage(String fileName);

  /**
   * Save the image at the given path.
   * 
   * @param fileName It is the filename of the image to be saved.
   */
  public void saveImage(String fileName);

  /**
   * Generate Blur effect on the image.
   */
  public void blur();

  /**
   * Generate Sharpen effect on the image.
   */
  public void sharpen();

  /**
   * Generate Grayscale effect on the image.
   */
  public void grayscale();

  /**
   * Generate Sepia effect on the image.
   */
  public void sepia();

  /**
   * Generate Dither effect on the image.
   * 
   * @param noOfColorsToReduceTo It is the number of colors to reduce per channel.
   */
  public void dither(int noOfColorsToReduceTo);

  /**
   * Generate Mosaic effect on the image.
   * 
   * @param noOfseeds It is the number of seeds to be generated randomly
   */
  public void mosaic(int noOfseeds);

  /**
   * Generate Pixelate effect on the image.
   * 
   * @param noOfSquaresAcross It is the number of square across the width of the
   *                          image.
   */
  public void pixelate(int noOfSquaresAcross);

  /**
   * Generate the cross-stitch pattern.
   */
  public void pattern();

  /**
   * Save the pattern at the given path.
   * 
   * @param fileName It is the filename of the pattern to be saved.
   */
  public void savePattern(String fileName);

  /**
   * Execute the batch command script.
   * 
   * @param commandText It is the script to be executed.
   */
  public void executeBatchcommand(String commandText);

  /**
   * Exchange one color for given dmc code in a cross-stitch pattern at given
   * coordinates.
   * 
   * @param xCordinate x-coordinate of the image clicked.
   * @param yCordinate y-coordinate of the image clicked.
   * @param dmcCode    dmc code color to replace to
   */
  public void patternReplaceColor(int xCordinate, int yCordinate, String dmcCode);

  /**
   * Remove the given color from the pattern.
   * 
   * @param dmcCode dmc code color to remove from the pattern.
   */
  public void patternRemoveColor(String dmcCode);

  /**
   * Enable File Menu Item on Image Operations selected from Menu Bar.
   */
  public void imageOperations();

  /**
   * Enable Command Operations Menu Item on Command Operations selected from Menu
   * Bar.
   */
  public void commandOperations();

  /**
   * To exit the program.
   */
  public void programExit();

  /**
   * Take user input as cordinates on the image to be replaced and the dmc color
   * code when exchange color on pattern operation selected.
   */
  public void exchangeColorPatternButtonClicked();

  /**
   * Take user input as dmc color code to replace when remove color on pattern
   * operation selected.
   */
  public void removeColorPatternButtonClicked();

  /**
   * Execute given batch file.
   * 
   * @param fileName name of the batch file.
   */
  public void executeBatchFile(String fileName);

  /**
   * Add given text in given color onto the pattern.
   * 
   * @param text    The text to be displayed onto the image
   * @param dmcCode Color in which text to be displayed
   */
  public void patternAddText(String text, String dmcCode);

  /**
   * Take user input as list of colors when new color to be displayed on pattern
   * operation selected.
   */
  public void newColorPatternClicked();

  /**
   * Add new given colors on the pattern.
   * 
   * @param selectedColors List of colors to be displaced
   */
  public void addNewColorsPattern(List<String> selectedColors);

  /**
   * Take user input as text and dmc code when user selects Add text operation on
   * the pattern.
   */
  public void addTextPatternButtonClicked();

  /**
   * Take user input as filename when user selects load operation on the file
   * menu.
   */
  public void loadClicked();

  /**
   * Take user input as filename when user selects save image operation on the
   * file menu.
   */
  public void saveImageClicked();

  /**
   * Take user input as number of colors to reduce per channel when user selects
   * dither operation on the image.
   */
  public void ditherClicked();

  /**
   * Take user input as number of seeds when user selects mosaic operation on the
   * image.
   */
  public void mosaicClicked();

  /**
   * Take user input as number of squares across the width when user selects
   * pixelate operation on the image.
   */
  public void pixelateClicked();

  /**
   * Take user input as script commands when user selects batch command operation
   * on the command menu.
   */
  public void batchCommandClicked();

  /**
   * Take user input as filename when user selects load batch file operation on
   * the command menu.
   */
  public void batchCommandLoadClicked();

  /**
   * Take user input as number of seeds when user selects mosaic operation on the
   * image.
   */
  public void newColorClicked();

  /**
   * Take user input as colors when user selects new Colors operation on the
   * pattern.
   */
  public void displaySymbolsClicked();

  /**
   * Take user input as filename when user selects save pattern operation on the
   * pattern.
   */
  public void savePatternClicked();
}
