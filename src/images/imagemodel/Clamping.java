package images.imagemodel;

import java.util.Objects;

/**
 * Clamping class that has methods to "clamp" each value in each channel to
 * avoid overflow and underflow while saving, and to display such images
 * properly.
 */
public class Clamping {
  private static final int minimum = 0;
  private static final int maximum = 255;

  /**
   * This method,"clamp" each value in each channel to avoid overflow and
   * underflow while saving, and to display such images properly.
   * 
   * @param channel It is a channel of an image, it can be either red or green or
   *                blue
   * @return It returns the clamped value of a channel
   */

  public static int doClamping(int channel) {

    return clampingHelper(channel);
  }

  /**
   * This method,"clamp" each value in each channel of 2 D RGB array to avoid
   * overflow and underflow while saving, and to display such images properly.
   * 
   * @param rgbBuffer It is a 2D array of RGB colors
   * @return An array having clamped RGB color values
   */
  public static int[][][] doClamping(int[][][] rgbBuffer) {
    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");
    int[][][] clamped_array = new int[rgbBuffer.length][rgbBuffer[0].length][rgbBuffer[0][0].length];

    for (int x = 0; x < rgbBuffer.length; x++) {
      for (int y = 0; y < rgbBuffer[0].length; y++) {
        for (int z = 0; z < rgbBuffer[0][0].length; z++) {
          clamped_array[x][y][z] = clampingHelper(rgbBuffer[x][y][z]);
        }
      }
    }
    return clamped_array;
  }

  /**
   * This is a private helper method to clamp the values.
   * 
   * @param channel It is a channel of an image, it can be either red or green or
   *                blue
   * @return It returns the clamped value of a channel
   */
  private static int clampingHelper(int channel) {
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
