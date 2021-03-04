package images;

import java.util.Objects;

public class ReducingColorDensity implements ReducingColorDensityInterface {

  @Override
  public int[][][] doReduceColorDensity(int[][][] rgbBuffer, int noOfColorsToReduceTo) {
    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");

    int[][][] reduced_color_density_rgb_buffer = rgbBuffer.clone();

    int image_height = reduced_color_density_rgb_buffer.length;
    int image_width = reduced_color_density_rgb_buffer[0].length;

    for (int x = 0; x < image_height; x++) {
      for (int y = 0; y < image_width; y++) {
        for (int z = 0; z < 3; z++) {
          int old_channel = reduced_color_density_rgb_buffer[x][y][z];

          int new_channel = Math.round(old_channel / (256));

          reduced_color_density_rgb_buffer[x][y][z] = Clamping.doClamping(new_channel);

          int error_channel = old_channel - new_channel;

          if ((x + 1) < image_height) {
            reduced_color_density_rgb_buffer[x + 1][y][z] = Clamping.doClamping(Math
                .round(reduced_color_density_rgb_buffer[x + 1][y][z] + error_channel * 7 / 16.0f));
          }
          if (((x - 1) >= 0) && ((y + 1) < image_width)) {
            reduced_color_density_rgb_buffer[x - 1][y + 1][z] = Clamping.doClamping(Math.round(
                reduced_color_density_rgb_buffer[x - 1][y + 1][z] + error_channel * 3 / 16.0f));
          }
          if ((y + 1) < image_width) {
            reduced_color_density_rgb_buffer[x][y + 1][z] = Clamping.doClamping(Math
                .round(reduced_color_density_rgb_buffer[x][y + 1][z] + error_channel * 5 / 16.0f));
          }
          if (((x + 1) < image_height) && ((y + 1) < image_width)) {
            reduced_color_density_rgb_buffer[x + 1][y + 1][z] = Clamping.doClamping(Math.round(
                reduced_color_density_rgb_buffer[x + 1][y + 1][z] + error_channel * 1 / 16.f));
          }
        }

      }
    }
    return reduced_color_density_rgb_buffer;
  }
}
