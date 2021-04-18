package images.imagecontrol;

import java.util.List;

import images.imagemodel.DmcFloss;

public interface Features{

  public void loadImage(String fileName);

  public void saveImage(String fileName);

  public void blur();

  public void sharpen();

  public void grayscale();

  public void sepia();

  public void dither(int noOfColorsToReduceTo);

  public void mosaic(int noOfseeds);

  public void pixelate(int noOfSquaresAcross);

  public void pattern();

  public void savePattern(String fileName);

  public void executeBatchcommand(String commandText);

  public void patternReplaceColor(int xCordinate, int yCordinate, String dmcCode);

  public void patternRemoveColor(String dmcCode);

  public void imageOperations();

  public void commandOperations();

  public void programExit();

  public void exchangeColorPatternButtonClicked();

  public void removeColorPatternButtonClicked();

  public void executeBatchFile(String fileName);

  public void patternAddText(String text, String dmcCode);

  public void newColorPatternClicked();

  public void addNewColorsPattern(List<String> selectedColors);

  public void addTextPatternButtonClicked();

  public void loadClicked();

  public void saveImageClicked();

  public void ditherClicked();

  public void mosaicClicked();

  public void pixelateClicked();

  public void batchCommandClicked();

  public void batchCommandLoadClicked();

  public void newColorClicked();

  public void displaySymbolsClicked();

  public void savePatternClicked();
}
