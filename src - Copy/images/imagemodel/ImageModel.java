package images.imagemodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * ImageModel class implements the ImageModelInterface interface that can be
 * used to manipulate images to produce some interesting effects.
 */
public class ImageModel implements ImageModelInterface {

  private ImageInterface imageObj;
  private List<ImageObserver> imageObservers;
  private List<PatternLegendObserver> patternLegendObservers;

  /**
   * Constructs a ImageModel.
   *
   */

  public ImageModel() {
    this.imageObj = new Image();
    imageObservers = new ArrayList<ImageObserver>();
    patternLegendObservers = new ArrayList<PatternLegendObserver>();
  }

  /**
   * Constructs a Copy constructor for ImageModelImp class.
   * 
   */

  public ImageModel(ImageModel imgModel) {
    Objects.requireNonNull(imgModel);
    Objects.requireNonNull(imgModel.imageObj);
    Objects.requireNonNull(imgModel.imageObservers);

    this.imageObj = imgModel.imageObj;

    this.imageObservers = new ArrayList<ImageObserver>();
    for (int i = 0; i < imgModel.imageObservers.size(); i++) {
      Objects.requireNonNull(imgModel.imageObservers.get(i));
      this.imageObservers.add(imgModel.imageObservers.get(i));
    }

    this.patternLegendObservers = new ArrayList<PatternLegendObserver>();
    for (int i = 0; i < imgModel.patternLegendObservers.size(); i++) {
      Objects.requireNonNull(imgModel.patternLegendObservers.get(i));
      this.patternLegendObservers.add(imgModel.patternLegendObservers.get(i));
    }
  }

  @Override
  public ImageModelInterface loadImage(String filename) throws IOException {
    Objects.requireNonNull(filename, "Filepath cannot be null");

    ImageInterface imageData = this.imageObj.loadImage(filename);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface saveImage(String filename) throws IOException {
    Objects.requireNonNull(filename, "Filepath cannot be null.");

    ImageInterface imageData = this.imageObj.saveImage(filename);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface grayScale() {
    float[][] colorTransformedMatrix = ImageOperationsUtility.getGrayScaleMatrix();

    Objects.requireNonNull(colorTransformedMatrix, "Color Transformed matrix cannot not be null");

    ImageInterface imageData = this.imageObj.colorTransformation(colorTransformedMatrix);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface sepia() {
    float[][] colorTransformedMatrix = ImageOperationsUtility.getSepiaToneMatrix();

    Objects.requireNonNull(colorTransformedMatrix, "Color Transformed matrix cannot not be null");

    ImageInterface imageData = this.imageObj.colorTransformation(colorTransformedMatrix);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface blur() {
    float[][] kernel = ImageOperationsUtility.getBlurKernel();

    Objects.requireNonNull(kernel, "Kernel value cannot not be null.");

    ImageInterface imageData = this.imageObj.filter(kernel);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface sharpen() {
    float[][] kernel = ImageOperationsUtility.getSharpKernel();

    Objects.requireNonNull(kernel, "Kernel value cannot not be null.");

    ImageInterface imageData = this.imageObj.filter(kernel);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface dither(int noOfColorsToReduceTo) {

    ImageInterface imageData = this.imageObj.reduceColorDensity(noOfColorsToReduceTo, true);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    return new ImageModel(this);
  }

  @Override
  public void registerImageObserver(ImageObserver imageObserver) {
    Objects.requireNonNull(imageObserver);
    imageObservers.add(imageObserver);
  }

  @Override
  public void removeImageObserver(ImageObserver imageObserver) {
    Objects.requireNonNull(imageObserver);
    int observerIndex = imageObservers.indexOf(imageObserver);
    if (observerIndex >= 0) {
      imageObservers.remove(observerIndex);
    }
  }

  @Override
  public void notifyImageObservers() {
    Iterator<ImageObserver> iterator = imageObservers.iterator();
    while (iterator.hasNext()) {
      ImageObserver observer = (ImageObserver) iterator.next();
      observer.updateImage(this.imageObj.getImage());
    }
  }

  @Override
  public void registerPatternLegendObserver(PatternLegendObserver patternLegendObserver) {
    Objects.requireNonNull(patternLegendObserver);
    patternLegendObservers.add(patternLegendObserver);
  }

  @Override
  public void removePatternLegendObserver(PatternLegendObserver patternLegendObserver) {
    Objects.requireNonNull(patternLegendObserver);
    int observerIndex = patternLegendObservers.indexOf(patternLegendObserver);
    if (observerIndex >= 0) {
      patternLegendObservers.remove(observerIndex);
    }
  }

  @Override
  public void notifyPatternLegendObservers() {
    Iterator<PatternLegendObserver> iterator = patternLegendObservers.iterator();
    while (iterator.hasNext()) {
      PatternLegendObserver observer = (PatternLegendObserver) iterator.next();
      observer.updatePatternLegend(this.imageObj.getPatternLegend());
    }
  }

  @Override
  public ImageModelInterface mosaic(int noOfSeeds) {
    ImageInterface imageData = this.imageObj.mosaic(noOfSeeds);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface pixelate(int noOfSquaresAcross) {
    ImageInterface imageData = this.imageObj.pixelate(noOfSquaresAcross);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface pattern1() throws IOException {
    ImageInterface imageData = this.imageObj.pattern();

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface savePattern(String fileName) throws IOException {

    Objects.requireNonNull(fileName, "Filepath cannot be null.");

    ImageInterface imageData = this.imageObj.savePattern(fileName);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface pattern() throws IOException {
    ImageInterface imageData = this.imageObj.pattern();

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    notifyPatternLegendObservers();

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface patternRemoveColor(String dmcCode) throws IOException {
    Objects.requireNonNull(dmcCode);

    ImageInterface imageData = this.imageObj.patternRemoveColor(dmcCode);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    notifyPatternLegendObservers();

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface patternReplaceColor(int xCordinate, int yCordinate, String dmcCode)
      throws IOException {
    Objects.requireNonNull(dmcCode);

    ImageInterface imageData = this.imageObj.patternReplaceColor(xCordinate, yCordinate, dmcCode);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    notifyPatternLegendObservers();

    return new ImageModel(this);
  }

  @Override
  public ImageModelInterface patternAddText(String text, String dmcCode) throws IOException {
    Objects.requireNonNull(dmcCode);
    Objects.requireNonNull(text);
    ImageInterface imageData = this.imageObj.patternAddText(text, dmcCode);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    notifyPatternLegendObservers();

    return new ImageModel(this);
  }

  @Override
  public List<DmcFloss> getDmcFlossColors() throws IOException {
    List<DmcFloss> dmcFlossList = ImageOperationsUtility.loadDmcFloss();
    Objects.requireNonNull(dmcFlossList);
    return dmcFlossList;
  }

  @Override
  public ImageModelInterface patternAddNewColors(List<String> selectedColors) throws IOException {
    Objects.requireNonNull(selectedColors);

    ImageInterface imageData = this.imageObj.patternAddNewColor(selectedColors);

    Objects.requireNonNull(imageData);

    this.imageObj = imageData;

    notifyImageObservers();

    notifyPatternLegendObservers();

    return new ImageModel(this);
  }

  @Override
  public List<SymbolCordinates> patternGetCordinatesForSymbol() throws IOException {
    List<SymbolCordinates> symbolCordinatesData = this.imageObj.patternGetCoordinatesForSymbol();

    Objects.requireNonNull(symbolCordinatesData);

    return symbolCordinatesData;
  }

}
