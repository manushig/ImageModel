package images;

import java.io.IOException;
import java.util.Scanner;

/**
 * Driver program to loads and saves images and demonstrates how all of
 * manipulation can be done on images.
 */
public class ImageDriver {

  private static ImageModelInterface model;
  private static String input_file_path;
  private static String input_file_path1;
  private static int[][][] original_rgb_buffer;
  private static float[][] gray_scale_matrix;
  private static float[][] sepia_tone_matrix;
  private static String output_file_path_gray_scale;
  private static String output_file_path_sepia_tone;
  private static float[][] blur_kernel;
  private static float[][] sharp_kernel;
  private static String output_file_path_blur;
  private static String output_file_path_sharp;
  private static String output_file_path_color_density_8_bit;
  private static String output_file_path_color_density_16_bit;

  /**
   * Driver program that loads and saves images and demonstrates how all of
   * manipulation can be done on images.
   * 
   * @param args Not used.
   */

  public static void main(String[] args) throws IOException {
    setData();

    System.out.println("Image Loading");

    model.loadImage(input_file_path);

    System.out.println("Image Loaded\n");

    // System.out.println(model.toString());

    System.out.println("Applying Color Transformation - Gray Scale on the original image\n");

    model.colorTransformation(gray_scale_matrix);

    System.out.println("Applied Color Transformation - Gray Scale on the original image\n");

    model.saveImage(output_file_path_gray_scale);

    System.out.println(String.format("File saved at %s\n", output_file_path_gray_scale));

    System.out.println("Applying Color Transformation - Sepia Tone on the original image\n");

    model.colorTransformation(sepia_tone_matrix);

    System.out.println("Applied Color Transformation - Sepia Tone on the original image\n");

    model.saveImage(output_file_path_sepia_tone);

    System.out.println(String.format("File saved at %s\n", output_file_path_sepia_tone));

    System.out.println("Applying Filter - Blur on the original image\n");

    model.filter(blur_kernel);

    // System.out.println(model.toString());

    System.out.println("Applied Filter - Blur on the original image\n");

    model.saveImage(output_file_path_blur);

    System.out.println(String.format("File saved at %s\n", output_file_path_blur));

    System.out.println("Applying Filter - Sharpness on the original image\n");

    model.filter(sharp_kernel);

    System.out.println("Applied Filter - Sharpness on the original image\n");

    model.saveImage(output_file_path_sharp);

    System.out.println(String.format("File saved at %s\n", output_file_path_sharp));

    model.loadImage(input_file_path1);

    model.reduceColorDensity(8);

    model.saveImage(output_file_path_color_density_8_bit);

    System.out.println(String.format("File saved at %s\n", output_file_path_color_density_8_bit));
  }

  /**
   * This is a private helper method to set required data.
   */
  private static void setData() {
    model = new ImageModel();

    input_file_path = "..\\ImageModel\\res\\Images\\manhattan-small.png";
    input_file_path1 = "..\\ImageModel\\res\\Images\\goat-original.png";
    output_file_path_gray_scale = "..\\ImageModel\\res\\Images\\GrayScale.jpg";

    output_file_path_sepia_tone = "..\\ImageModel\\res\\Images\\SepiaTone.jpg";

    output_file_path_blur = "..\\ImageModel\\res\\Images\\Blur.jpg";

    output_file_path_sharp = "..\\ImageModel\\res\\Images\\Sharp.jpg";

    output_file_path_color_density_8_bit = "..\\ImageModel\\res\\Images\\colorDensityReduced.jpg";

    output_file_path_color_density_16_bit = "..\\ImageModel\\res\\Images\\Cd_16.jpg";

    gray_scale_matrix = new float[3][3];

    gray_scale_matrix[0][0] = 0.2126f;
    gray_scale_matrix[0][1] = 0.7152f;
    gray_scale_matrix[0][2] = 0.0722f;

    gray_scale_matrix[1][0] = 0.2126f;
    gray_scale_matrix[1][1] = 0.7152f;
    gray_scale_matrix[1][2] = 0.0722f;

    gray_scale_matrix[2][0] = 0.2126f;
    gray_scale_matrix[2][1] = 0.7152f;
    gray_scale_matrix[2][2] = 0.0722f;

    sepia_tone_matrix = new float[3][3];

    sepia_tone_matrix[0][0] = 0.393f;
    sepia_tone_matrix[0][1] = 0.769f;
    sepia_tone_matrix[0][2] = 0.189f;

    sepia_tone_matrix[1][0] = 0.349f;
    sepia_tone_matrix[1][1] = 0.686f;
    sepia_tone_matrix[1][2] = 0.168f;

    sepia_tone_matrix[2][0] = 0.272f;
    sepia_tone_matrix[2][1] = 0.534f;
    sepia_tone_matrix[2][2] = 0.131f;

    blur_kernel = new float[3][3];

    blur_kernel[0][0] = 1 / 16.0f;
    blur_kernel[0][1] = 1 / 8.0f;
    blur_kernel[0][2] = 1 / 16.0f;

    blur_kernel[1][0] = 1 / 8.0f;
    blur_kernel[1][1] = 1 / 4.0f;
    blur_kernel[1][2] = 1 / 8.0f;

    blur_kernel[2][0] = 1 / 16.0f;
    blur_kernel[2][1] = 1 / 8.0f;
    blur_kernel[2][2] = 1 / 16.0f;

    sharp_kernel = new float[5][5];

    sharp_kernel[0][0] = -1 / 8.0f;
    sharp_kernel[0][1] = -1 / 8.0f;
    sharp_kernel[0][2] = -1 / 8.0f;
    sharp_kernel[0][3] = -1 / 8.0f;
    sharp_kernel[0][4] = -1 / 8.0f;

    sharp_kernel[1][0] = -1 / 8.0f;
    sharp_kernel[1][1] = 1 / 4.0f;
    sharp_kernel[1][2] = 1 / 4.0f;
    sharp_kernel[1][3] = 1 / 4.0f;
    sharp_kernel[1][4] = -1 / 8.0f;

    sharp_kernel[2][0] = -1 / 8.0f;
    sharp_kernel[2][1] = 1 / 4.0f;
    sharp_kernel[2][2] = 1.0f;
    sharp_kernel[2][3] = 1 / 4.0f;
    sharp_kernel[2][4] = -1 / 8.0f;

    sharp_kernel[3][0] = -1 / 8.0f;
    sharp_kernel[3][1] = 1 / 4.0f;
    sharp_kernel[3][2] = 1 / 4.0f;
    sharp_kernel[3][3] = 1 / 4.0f;
    sharp_kernel[3][4] = -1 / 8.0f;

    sharp_kernel[4][0] = -1 / 8.0f;
    sharp_kernel[4][1] = -1 / 8.0f;
    sharp_kernel[4][2] = -1 / 8.0f;
    sharp_kernel[4][3] = -1 / 8.0f;
    sharp_kernel[4][4] = -1 / 8.0f;

  }

}
