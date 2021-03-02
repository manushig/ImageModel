package images;

/**
 * ColorTransformation, implements an interface ColorTransformationInterface
 * that defines methods to do color transformation manipulation on the image.
 *
 */
public class ColorTransformation implements ColorTransformationInterface {

  private final int minimum_clamp_value;
  private final int maximum_clamp_value;

  /**
   * Constructs a ColorTransformation.
   */
  public ColorTransformation() {
    this.minimum_clamp_value = 0;
    this.maximum_clamp_value = 255;
  }

  @Override
  public int[][][] doColorTransformation(int height, int width, int[][][] rgbBuffer,
      float[][] colorTransformedMatrix) {

    // Fix me- do clone or copy rgb_buffer

    int[][][] colorTransformed_rgb_buffer = rgbBuffer;

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {

        int old_red = colorTransformed_rgb_buffer[x][y][0];
        int old_green = colorTransformed_rgb_buffer[x][y][1];
        int old_blue = colorTransformed_rgb_buffer[x][y][2];

        int[] color_transformed_array = colorTransformed(old_red, old_green, old_blue,
            colorTransformedMatrix);

        colorTransformed_rgb_buffer[x][y][0] = color_transformed_array[0];
        colorTransformed_rgb_buffer[x][y][1] = color_transformed_array[1];
        colorTransformed_rgb_buffer[x][y][2] = color_transformed_array[2];
      }
    }

    return colorTransformed_rgb_buffer;
  }

  /**
   * This is a private helper method to do calculation to get new 2D RGB array
   * after doing color transformation.
   */
  private int[] colorTransformed(int red, int green, int blue, float[][] colorTransformedMatrix) {
    final float a11 = colorTransformedMatrix[0][0];
    final float a12 = colorTransformedMatrix[0][1];
    final float a13 = colorTransformedMatrix[0][2];
    final float a21 = colorTransformedMatrix[1][0];
    final float a22 = colorTransformedMatrix[1][1];
    final float a23 = colorTransformedMatrix[1][2];
    final float a31 = colorTransformedMatrix[2][0];
    final float a32 = colorTransformedMatrix[2][1];
    final float a33 = colorTransformedMatrix[2][2];

    int[] color_transformed_array = new int[3];

    float new_red = (red * a11) + (green * a12) + (blue * a13);
    float new_green = (red * a21) + (green * a22) + (blue * a23);
    float new_blue = (red * a31) + (green * a32) + (blue * a33);

    int[] clamped_array = Clamping.doClamping(this.minimum_clamp_value, this.maximum_clamp_value,
        (int) Math.round(new_red), (int) Math.round(new_green), (int) Math.round(new_blue));

    // Fixme - do clone or copy on clamped_array

    color_transformed_array[0] = clamped_array[0];
    color_transformed_array[1] = clamped_array[1];
    color_transformed_array[2] = clamped_array[2];

    return color_transformed_array;
  }
}
