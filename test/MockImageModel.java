import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import images.imagemodel.DmcFloss;
import images.imagemodel.ImageModelInterface;
import images.imagemodel.ImageObserver;
import images.imagemodel.PatternLegendObserver;
import images.imagemodel.SymbolCordinates;

/**
 * MockImageModel, it is the class which mocks the ImageModel to test the
 * controller in isolation.
 * 
 */
public class MockImageModel implements ImageModelInterface {

  private StringBuffer log;
  private final int uniqueCode;

  /**
   * Constructs a MockModel, specifying log and unique identifier value.
   * 
   * @param log        It is the log
   * @param uniqueCode It is the unique identifier
   */
  public MockImageModel(StringBuffer log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public ImageModelInterface loadImage(String filename) throws IOException {
    log.append(String.format("Function: loadImage and Filename: %s\n", filename));
    return this;
  }

  @Override
  public ImageModelInterface saveImage(String filename) throws IOException {
    log.append(String.format("Function: saveImage and Filename: %s\n", filename));
    return this;
  }

  @Override
  public ImageModelInterface grayScale() {
    log.append(String.format("Function: grayScale\n"));
    return this;
  }

  @Override
  public ImageModelInterface sepia() {
    log.append(String.format("Function: sepia\n"));
    return this;
  }

  @Override
  public ImageModelInterface blur() {
    log.append(String.format("Function: blur\n"));
    return this;
  }

  @Override
  public ImageModelInterface sharpen() {
    log.append(String.format("Function: sharpen\n"));
    return this;
  }

  @Override
  public ImageModelInterface dither(int noOfColorsToReduceTo) {
    log.append(
        String.format("Function: dither and noOfColorsToReduceTo: %d\n", noOfColorsToReduceTo));
    return this;
  }

  @Override
  public ImageModelInterface mosaic(int noOfSeeds) {
    log.append(String.format("Function: mosaic and noOfSeeds: %d\n", noOfSeeds));
    return this;
  }

  @Override
  public ImageModelInterface pixelate(int noOfSquaresAcross) {
    log.append(String.format("Function: pixelate and noOfSquaresAcross: %d\n", noOfSquaresAcross));
    return this;
  }

  @Override
  public ImageModelInterface pattern() throws IOException {
    log.append(String.format("Function: pattern\n"));
    return this;
  }

  @Override
  public ImageModelInterface savePattern(String fileName) throws IOException {
    log.append(String.format("Function: savePattern and fileName: %s\n", fileName));
    return this;
  }

  @Override
  public void registerImageObserver(ImageObserver imageObserver) {
    imageObserver = new MockImageObserver(log, this.uniqueCode);
    log.append(String.format("Function: registerImageObserver and imageObserver: %s\n",
        imageObserver.toString()));
  }

  @Override
  public void removeImageObserver(ImageObserver imageObserver) {
    imageObserver = new MockImageObserver(log, this.uniqueCode);
    log.append(String.format("Function: removeImageObserver and imageObserver: %s\n",
        imageObserver.toString()));
  }

  @Override
  public void notifyImageObservers() {
    log.append(String.format("Function: notifyImageObservers\n"));
  }

  @Override
  public void registerPatternLegendObserver(PatternLegendObserver patternLegendObserver) {
    patternLegendObserver = new MockPatternLegendObserver(log, this.uniqueCode);
    log.append(
        String.format("Function: registerPatternLegendObserver and patternLegendObserver: %s\n",
            patternLegendObserver.toString()));

  }

  @Override
  public void removePatternLegendObserver(PatternLegendObserver patternLegendObserver) {
    patternLegendObserver = new MockPatternLegendObserver(log, this.uniqueCode);
    log.append(
        String.format("Function: removePatternLegendObserver and patternLegendObserver: %s\n",
            patternLegendObserver.toString()));

  }

  @Override
  public void notifyPatternLegendObservers() {
    log.append(String.format("Function: notifyPatternLegendObservers\n"));

  }

  @Override
  public ImageModelInterface patternRemoveColor(String dmcCode) throws IOException {
    log.append(String.format("Function: patternRemoveColor and dmcCode: %s\n", dmcCode));
    return this;
  }

  @Override
  public ImageModelInterface patternReplaceColor(int xCordinate, int yCordinate, String dmcCode)
      throws IOException {
    log.append(String.format(
        "Function: patternReplaceColor and xCordinate: %d and yCordinate:%d and dmcCode: %s\n",
        xCordinate, yCordinate, dmcCode));
    return this;
  }

  @Override
  public ImageModelInterface patternAddText(String text, String dmcCode) throws IOException {
    log.append(
        String.format("Function: patternAddText and text: %s and dmcCode: %s\n", text, dmcCode));
    return this;
  }

  @Override
  public List<DmcFloss> getDmcFlossColors() throws IOException {
    log.append(String.format("Function: getDmcFlossColors\n"));
    return new ArrayList<>();
  }

  @Override
  public ImageModelInterface patternAddNewColors(List<String> selectedColors) throws IOException {
    log.append(String.format("Function: patternAddNewColors and selectedColors: %s\n",
        selectedColors.get(0)));
    return this;
  }

  @Override
  public List<SymbolCordinates> patternGetCordinatesForSymbol() throws IOException {
    log.append(String.format("Function: patternGetCordinatesForSymbol\n"));
    return new ArrayList<>();
  }

}
