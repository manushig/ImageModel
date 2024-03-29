package images.imagemodel;

import java.util.Objects;

/**
 * ColorTransformation, implements an interface ColorTransformationInterface
 * that defines methods to do color transformation manipulation on the image.
 *
 */
public class ColorTransformation implements ColorTransformationInterface {
  @Override
  public int[][][] doColorTransformation(int[][][] rgbBuffer, float[][] colorTransformedMatrix) {

    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");
    Objects.requireNonNull(colorTransformedMatrix,
        "Color transformed matrix value cannot be null.");

    int[][][] rgbBuffer_copy = ImageOperationsUtility.copyArray(rgbBuffer);

    int[][][] colorTransformed_rgb_buffer = new int[rgbBuffer_copy.length]
        [rgbBuffer_copy[0].length][rgbBuffer_copy[0][0].length];

    int height = rgbBuffer_copy.length;
    int width = rgbBuffer_copy[0].length;

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {

        int old_red = rgbBuffer_copy[x][y][0];
        int old_green = rgbBuffer_copy[x][y][1];
        int old_blue = rgbBuffer_copy[x][y][2];

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

    int[] color_transformed_array = new int[3];
    for (int m = 0; m < 3; m++) {
      color_transformed_array[m] = Math.round((red * colorTransformedMatrix[m][0])
          + (green * colorTransformedMatrix[m][1]) + (blue * colorTransformedMatrix[m][2]));
    }

    return color_transformed_array;
  }
}
