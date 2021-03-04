package images;

/**
 * Clamping class that has methods to "clamp" each value in each channel to
 * avoid overflow and underflow while saving, and to display such images
 * properly.
 */
public class Clamping {
  private final static int minimum = 0;
  private final static int maximum = 255;

  /**
   * This method,"clamp" each value in each channel to avoid overflow and
   * underflow while saving, and to display such images properly.
   * 
   * @param channel It is a channel of an image, it can be either red or green or
   *                blue
   * @return An array having RGB color values
   */

  public static int doClamping(int channel) {
    int clamped_value = 0;

    if (channel > maximum) {
      clamped_value = maximum;
    } else if (channel < minimum) {
      clamped_value = minimum;
    } else {
      clamped_value = channel;
    }

    return clamped_value;
  }
}
