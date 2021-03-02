package images;

/**
 * Clamping class that has methods to "clamp" each value in each channel to
 * avoid overflow and underflow while saving, and to display such images
 * properly.
 */
public class Clamping {
  /**
   * This method,"clamp" each value in each channel to avoid overflow and
   * underflow while saving, and to display such images properly.
   * 
   * @param minimum It is a permissible minimum value
   * @param maximum It is a permissible maximum value
   * @param red     It is a red color
   * @param green   It is a green color
   * @param blue    It is a blue color
   * @return An array having RGB color values
   */
  public static int[] doClamping(int minimum, int maximum, int red, int green, int blue) {
    int[] clamped_array = new int[3];

    if (red > maximum) {
      clamped_array[0] = maximum;
    } else if (red < minimum) {
      clamped_array[0] = minimum;
    } else {
      clamped_array[0] = red;
    }

    if (green > maximum) {
      clamped_array[1] = maximum;
    } else if (green < minimum) {
      clamped_array[1] = minimum;
    } else {
      clamped_array[1] = green;
    }

    if (blue > maximum) {
      clamped_array[2] = maximum;
    } else if (blue < minimum) {
      clamped_array[2] = minimum;
    } else {
      clamped_array[2] = blue;
    }

    return clamped_array;
  }
}
