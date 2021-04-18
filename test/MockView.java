import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import images.imagecontrol.Features;
import images.imagemodel.DmcFloss;
import images.imagemodel.Legend;
import images.imagemodel.SymbolCordinates;
import images.imageview.ImageViewInterface;

public class MockView implements ImageViewInterface {

  private StringBuffer log;

  public MockView(StringBuffer log) {
    this.log = log;
  }

  @Override
  public void updateImage(BufferedImage image) {
    log.append(String.format("Function: updateImage and image height: %d and image width: %d\n",
        image.getHeight(), image.getWidth()));

  }

  @Override
  public void updatePatternLegend(List<Legend> pattern) {
    log.append(
        String.format("Function: updatePatternLegend and size of pattern: %d\n", pattern.size()));

  }

  @Override
  public void setFeatures(Features feature) throws IOException {
    log.append(String.format("Function: setFeatures and feature object passed\n"));

  }

  @Override
  public void clearLegend() {
    log.append(String.format("Function: clearLegend\n"));

  }

  @Override
  public void setCommandText(String commandText) {
    log.append(String.format("Function: setCommandText and commandText: %s\n", commandText));

  }

  @Override
  public void disableImagePanelOnBatchCommands(String message) {
    log.append(
        String.format("Function: disableImagePanelOnBatchCommands and message: %s\n", message));

  }

  @Override
  public void addMouseListenerOnImagePanel() {
    log.append(String.format("Function: addMouseListenerOnImagePanel\n"));

  }

  @Override
  public void disableMouseListenerOnImagePanel() {
    log.append(String.format("Function: disableMouseListenerOnImagePanel\n"));

  }

  @Override
  public void enableMenuItemsOnImageOperations() {
    log.append(String.format("Function: enableMenuItemsOnImageOperations\n"));

  }

  @Override
  public void enableMenuItemsOnCommandOperations() {
    log.append(String.format("Function: enableMenuItemsOnCommandOperations\n"));

  }

  @Override
  public void programExitOperations() {
    log.append(String.format("Function: programExitOperations\n"));

  }

  @Override
  public void setImageOperationsPerformedStatus(boolean flag) {
    log.append(String.format("Function: setImageOperationsPerformedStatus and flag: %b\n", flag));

  }

  @Override
  public void displayExceptionMessage(String message) {
    log.append(String.format("Function: displayExceptionMessage and message: %s\n", message));

  }

  @Override
  public void displayInformationMessage(String message) {
    log.append(String.format("Function: displayInformationMessage and message: %s\n", message));

  }

  @Override
  public void getColorRemoveInput() {
    log.append(String.format("Function: getColorRemoveInput\n"));

  }

  @Override
  public void addNewColorsWindow(List<DmcFloss> dmcList) {
    log.append(
        String.format("Function: addNewColorsWindow and  size of ddmcList: %d\n", dmcList.size()));

  }

  @Override
  public void setAddTextToPattern(String text) {
    log.append(String.format("Function: setAddTextToPattern and text: %s\n", text));

  }

  @Override
  public void selectColorAndAddTextWindow(List<DmcFloss> dmcList) {
    log.append(String.format("Function: selectColorAndAddTextWindow and size of dmcList: %d\n",
        dmcList.size()));

  }

  @Override
  public void setProgressBarText(String message) {
    log.append(String.format("Function: setProgressBarText and message: %s\n", message));

  }

  @Override
  public void getFileNameForLoad() {
    log.append(String.format("Function: getFileNameForLoad\n"));

  }

  @Override
  public void getFileNameForSave() {
    log.append(String.format("Function: getFileNameForSave\n"));

  }

  @Override
  public void getDitherUserInput() {
    log.append(String.format("Function: getDitherUserInput\n"));

  }

  @Override
  public void getMosaicUserInput() {
    log.append(String.format("Function: getMosaicUserInput\n"));

  }

  @Override
  public void getPixelateInput() {
    log.append(String.format("Function: getPixelateInput\n"));

  }

  @Override
  public void geBatchCommandInput() {
    log.append(String.format("Function: geBatchCommandInput\n"));

  }

  @Override
  public void getBatchCommandLoadInput() {
    log.append(String.format("Function: getBatchCommandLoadInput\n"));

  }

  @Override
  public void getNewColorInput() {
    log.append(String.format("Function: getNewColorInput\n"));

  }

  @Override
  public void diplaySymbolsOnPattern(List<SymbolCordinates> symbolCordinatesList) {
    log.append(
        String.format("Function: diplaySymbolsOnPattern and size of symbolCordinatesList: %d\n",
            symbolCordinatesList.size()));

  }

  @Override
  public void getSavePatternInput() {
    log.append(String.format("Function: getSavePatternInput\n"));

  }

  @Override
  public void enableImageOperation() {
    log.append(String.format("Function: enableImageOperation\n"));

  }

}
