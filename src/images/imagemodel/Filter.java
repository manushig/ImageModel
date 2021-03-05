package images.imagemodel;

import java.util.Objects;

/**
 * Filter, implements an interface FilterInterface that defines methods to do
 * filter manipulation on the image.
 *
 */
public class Filter implements FilterInterface {

  @Override
  public int[][][] doFilter(int[][][] rgbBuffer, float[][] kernel) {

    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");
    Objects.requireNonNull(kernel, "Kernel value cannot be null.");

    int[][][] filtered_rgb_buffer = new int[rgbBuffer.length][rgbBuffer[0].length][3];

    int image_height = rgbBuffer.length;
    int image_width = rgbBuffer[0].length;

    int kernel_height = kernel.length;
    int kernel_width = kernel[0].length;

    int height = kernel_height / 2;
    int width = kernel_width / 2;

    for (int x = 0; x < image_height; x++) {
      for (int y = 0; y < image_width; y++) {
        for (int z = 0; z < 3; z++) {
          float sum_channel = 0f;
          for (int m = 0; m < kernel_height; m++) {
            for (int n = 0; n < kernel_width; n++) {

              int xIndex = x - height + m;
              int yIndex = y - width + n;
              if (xIndex < 0 || xIndex >= image_height || yIndex < 0 || yIndex >= image_width) {
                sum_channel = sum_channel + (kernel[m][n] * 0);
              } else {
                sum_channel = sum_channel + (kernel[m][n] * rgbBuffer[xIndex][yIndex][z]);
              }

            }
          }

          filtered_rgb_buffer[x][y][z] = Math.round(sum_channel);
        }
      }
    }

    return filtered_rgb_buffer;
  }
}
