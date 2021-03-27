package images.imagemodel;

import java.util.Objects;

/**
 * Pixelate, implements an interface PixelateInterface that defines methods to
 * generate pixelate effect on the image.
 *
 */

public class Pixelate implements PixelateInterface {

  @Override
  public int[][][] doPixelate(int[][][] rgbBuffer, int noOfSquaresAcross) {

    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");

    int[][][] pixelate_rgb_buffer = ImageOperationsUtility.copyArray(rgbBuffer);

    int image_height = rgbBuffer.length;
    int image_width = rgbBuffer[0].length;

    int square_length = (int) Math.round(image_width / noOfSquaresAcross);

    for (int y = 0; y < image_width; y += square_length) {
      for (int x = 0; x < image_height; x += square_length) {
        for (int yd = y; ((yd < y + square_length) && (yd < image_width)); yd++) {
          for (int xd = x; ((xd < x + square_length) && (xd < image_height)); xd++) {

            int centre_x = (square_length + x + x) / 2;
            int centre_y = (square_length + y + y) / 2;

            if ((centre_y < image_width) && (centre_x < image_height)) {
              pixelate_rgb_buffer[xd][yd][0] = rgbBuffer[centre_x][centre_y][0];
              pixelate_rgb_buffer[xd][yd][1] = rgbBuffer[centre_x][centre_y][1];
              pixelate_rgb_buffer[xd][yd][2] = rgbBuffer[centre_x][centre_y][2];
            } else {
              pixelate_rgb_buffer[xd][yd][0] = rgbBuffer[x][y][0];
              pixelate_rgb_buffer[xd][yd][1] = rgbBuffer[x][y][1];
              pixelate_rgb_buffer[xd][yd][2] = rgbBuffer[x][y][2];
            }
          }
        }
      }
    }

    return pixelate_rgb_buffer;
  }

}
