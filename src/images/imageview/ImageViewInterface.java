package images.imageview;

import java.io.IOException;
import java.util.List;

import images.imagecontrol.Features;
import images.imagemodel.DmcFloss;
import images.imagemodel.ImageObserver;
import images.imagemodel.PatternLegendObserver;
import images.imagemodel.SymbolCordinates;

public interface ImageViewInterface extends ImageObserver, PatternLegendObserver {
  void setFeatures(Features feature) throws IOException;

  void clearLegend();

  void setCommandText(String commandText);

  void disableImagePanelOnBatchCommands(String message);

  public void addMouseListenerOnImagePanel();

  public void disableMouseListenerOnImagePanel();

  public void enableMenuItemsOnImageOperations();

  public void enableMenuItemsOnCommandOperations();

  public void programExitOperations();

  public void setImageOperationsPerformedStatus(boolean flag);

  public void displayExceptionMessage(String message);

  public void displayInformationMessage(String message);

  public void getColorRemoveInput();

  public void addNewColorsWindow(List<DmcFloss> dmcList);

  public void setAddTextToPattern(String text);

  public void selectColorAndAddTextWindow(List<DmcFloss> dmcList);

  public void setProgressBarText(String message);

  public void getFileNameForLoad();

  public void getFileNameForSave();

  public void getDitherUserInput();

  public void getMosaicUserInput();

  public void getPixelateInput();

  public void geBatchCommandInput();

  public void getBatchCommandLoadInput();

  public void getNewColorInput();

  public void diplaySymbolsOnPattern(List<SymbolCordinates> symbolCordinatesList);

  public void getSavePatternInput();

  public void enableImageOperation();
}
