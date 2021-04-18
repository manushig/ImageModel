import java.awt.image.BufferedImage;

import images.imagemodel.ImageObserver;

public class MockImageObserver implements ImageObserver {
  private StringBuffer log;
  private final int uniqueCode;

  /**
   * Constructs a MockImageObserver, specifying log and unique identifier value.
   * 
   * @param log        It is the log
   * @param uniqueCode It is the unique code
   */
  public MockImageObserver(StringBuffer log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void updateImage(BufferedImage image) {
    log.append(String.format("Function: updateImage and BufferedImage: %s\n", image));
  }

  @Override
  public String toString() {
    return String.format("%s", String.valueOf(this.uniqueCode));
  }

}
