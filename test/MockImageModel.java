import java.io.IOException;

import images.imagemodel.ImageModelInterface;
import images.imagemodel.ImageObserver;

/**
 * MockImageModel, it is the class which mockks the ImageModel to test the
 * controller in isolation.
 *
 * 
 */
public class MockImageModel implements ImageModelInterface {

  private StringBuffer log;
  private final int uniqueCode;

  /**
   * Constructs a MockModel, specifying log and unique identifier value.
   * 
   * @param log        It is the log
   * @param uniqueCode It is the unique code
   */
  public MockImageModel(StringBuffer log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public String toString() {
    return String.valueOf(this.uniqueCode);
  }

  @Override
  public ImageModelInterface loadImage(String filename) throws IOException {
    log.append(String.format("Filename: %s and UniqueCode: %d", filename, this.uniqueCode));
    return this;
  }

  @Override
  public ImageModelInterface saveImage(String filename) throws IOException {
    log.append(String.format("Filename: %s and UniqueCode: %d", filename, this.uniqueCode));
    return this;
  }

  @Override
  public ImageModelInterface grayScale() {
    log.append(String.format("UniqueCode: %d", this.uniqueCode));
    return this;
  }

  @Override
  public ImageModelInterface sepia() {
    log.append(String.format("UniqueCode: %d", this.uniqueCode));
    return this;
  }

  @Override
  public ImageModelInterface blur() {
    log.append(String.format("UniqueCode: %d", this.uniqueCode));
    return this;
  }

  @Override
  public ImageModelInterface sharpen() {
    log.append(String.format("UniqueCode: %d", this.uniqueCode));
    return this;
  }

  @Override
  public ImageModelInterface dither(int noOfColorsToReduceTo) {
    log.append(String.format("noOfColorsToReduceTo: %d and UniqueCode: %d", noOfColorsToReduceTo,
        this.uniqueCode));
    return this;
  }

  @Override
  public ImageModelInterface mosaic(int noOfSeeds) {
    log.append(String.format("noOfSeeds: %d and UniqueCode: %d", noOfSeeds, this.uniqueCode));
    return this;
  }

  @Override
  public ImageModelInterface pixelate(int noOfSquaresAcross) {
    log.append(String.format("noOfSquaresAcross: %d and UniqueCode: %d", noOfSquaresAcross,
        this.uniqueCode));
    return this;
  }

  @Override
  public ImageModelInterface pattern() throws IOException {
    log.append(String.format("UniqueCode: %d", this.uniqueCode));
    return this;
  }

  @Override
  public ImageModelInterface savePattern(String fileName) throws IOException {
    log.append(String.format("Filename: %s and UniqueCode: %d", fileName, this.uniqueCode));
    return this;
  }

  @Override
  public void registerImageObserver(ImageObserver imageObserver)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This feature is not available");

  }

  @Override
  public void removeImageObserver(ImageObserver imageObserver)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This feature is not available");
  }

  @Override
  public void notifyImageObservers() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This feature is not available");
  }

}
