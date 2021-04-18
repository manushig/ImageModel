package images.imagemodel;

import java.util.Objects;

/**
 * ReducingColorDensity, implements an interface ReducingColorDensityInterface
 * that defines methods to reduce color density on the image.
 *
 */
public class ReducingColorDensity implements ReducingColorDensityInterface {

  @Override
  public int[][][] doReduceColorDensity(int[][][] rgbBuffer, int noOfColorsToReduceTo,
      boolean isDitheringRequired) {
    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");

    int[][][] reduced_color_density_rgb_buffer = ImageOperationsUtility.copyArray(rgbBuffer);

    int image_height = reduced_color_density_rgb_buffer.length;
    int image_width = reduced_color_density_rgb_buffer[0].length;

    for (int y = 0; y < image_width; y++) {
      for (int x = 0; x < image_height; x++) {
        for (int z = 0; z < 3; z++) {
          int old_channel = reduced_color_density_rgb_buffer[x][y][z];

          int new_channel = Math.round(((noOfColorsToReduceTo - 1) * old_channel / 255)
              * (255 / (float) (noOfColorsToReduceTo - 1)));

          reduced_color_density_rgb_buffer[x][y][z] = new_channel;

          if (isDitheringRequired) {
            int error_channel = old_channel - new_channel;

            if ((x + 1) < image_height) {
              reduced_color_density_rgb_buffer[x + 1][y][z] = Math
                  .round(reduced_color_density_rgb_buffer[x + 1][y][z] + error_channel * 7 / 16.0f);
            }
            if (((x - 1) >= 0) && ((y + 1) < image_width)) {
              reduced_color_density_rgb_buffer[x - 1][y + 1][z] = Math.round(
                  reduced_color_density_rgb_buffer[x - 1][y + 1][z] + error_channel * 3 / 16.0f);
            }
            if ((y + 1) < image_width) {
              reduced_color_density_rgb_buffer[x][y + 1][z] = Math
                  .round(reduced_color_density_rgb_buffer[x][y + 1][z] + error_channel * 5 / 16.0f);
            }
            if (((x + 1) < image_height) && ((y + 1) < image_width)) {
              reduced_color_density_rgb_buffer[x + 1][y + 1][z] = Math.round(
                  reduced_color_density_rgb_buffer[x + 1][y + 1][z] + error_channel * 1 / 16.f);
            }
          }
        }

      }
    }
    return reduced_color_density_rgb_buffer;
  }

}
