package images.imageview;

import java.io.IOException;
import java.util.List;

import images.imagecontrol.Features;
import images.imagemodel.DmcFloss;
import images.imagemodel.ImageObserver;
import images.imagemodel.PatternLegendObserver;
import images.imagemodel.SymbolCordinates;

/**
 * ImageViewInterface, its the interface for the view class. This used to take
 * user inputs and display the information and output to the user
 *
 */
public interface ImageViewInterface extends ImageObserver, PatternLegendObserver {
  /**
   * Get the set of feature call backs that the view can use.
   * 
   * @param feature the set of feature call backs as a Features object
   * @throws IOException if file/directory not found
   */

  void setFeatures(Features feature) throws IOException;

  /**
   * Clear the Lgend Panel.
   */
  void clearLegend();

  /**
   * Set the command script entered by the user.
   * 
   * @param commandText It is script command
   */
  void setCommandText(String commandText);

  /**
   * Disable Image Panel when user selected command operations.
   * 
   * @param message It is the message to be displayed.
   */
  void disableImagePanelOnBatchCommands(String message);

  /**
   * It adds the mouse listener to the image Panel.
   */
  public void addMouseListenerOnImagePanel();

  /**
   * It disable the mouse listener to the image Panel.
   */
  public void disableMouseListenerOnImagePanel();

  /**
   * It enables Image File Operations menu bar item.
   */
  public void enableMenuItemsOnImageOperations();

  /**
   * It enables Command Operations menu bar item.
   */
  public void enableMenuItemsOnCommandOperations();

  /**
   * It exit from the program.
   */
  public void programExitOperations();

  /**
   * It sets the flag status if user performed any image operations.
   * 
   * @param flag whether image operations done or not
   */
  public void setImageOperationsPerformedStatus(boolean flag);

  /**
   * Display given exception message.
   * 
   * @param message exception message
   */
  public void displayExceptionMessage(String message);

  /**
   * Displays the information message.
   * 
   * @param message information message
   */
  public void displayInformationMessage(String message);

  /**
   * Gets the DMC Code color to be removed from the pattern.
   */
  public void getColorRemoveInput();

  /**
   * It display all DMC Floss codes to choose from.
   * 
   * @param dmcList List of DMC Floss
   */
  public void addNewColorsWindow(List<DmcFloss> dmcList);

  /**
   * Provide text to set on the pattern.
   * 
   * @param text text to be displayed on the pattern
   */
  public void setAddTextToPattern(String text);

  /**
   * Get the color and text after displaying all color codes.
   * 
   * @param dmcList List of DMC Floss
   */
  public void selectColorAndAddTextWindow(List<DmcFloss> dmcList);

  /**
   * Set message to show the progress of the execution.
   * 
   * @param message message to display
   */
  public void setProgressBarText(String message);

  /**
   * Get the filename for loading the image.
   */
  public void getFileNameForLoad();

  /**
   * Get the filename for saving the image.
   */
  public void getFileNameForSave();

  /**
   * Get number of colors reduce per channel input.
   */
  public void getDitherUserInput();

  /**
   * Get number of seeds input.
   */
  public void getMosaicUserInput();

  /**
   * Get number of square across width input.
   */
  public void getPixelateInput();

  /**
   * Get the batch command script to execute.
   */
  public void geBatchCommandInput();

  /**
   * Get the filename of the batch file to execute.
   */
  public void getBatchCommandLoadInput();

  /**
   * Get list of colors codes to add new colors to the pattern.
   */
  public void getNewColorInput();

  /**
   * Displays the symbols on the pattern based on its coordinates on the image.
   * 
   * @param symbolCordinatesList List of SymbolCordinates
   */
  public void diplaySymbolsOnPattern(List<SymbolCordinates> symbolCordinatesList);

  /**
   * Get the filename for saving the pattern in text format.
   */
  public void getSavePatternInput();

  /**
   * It enables Image Operations menu bar item.
   */
  public void enableImageOperation();
}
