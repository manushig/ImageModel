package images.imagemodel;

import java.io.File;
import java.io.IOException;

/**
 * Driver program to loads and saves images and demonstrates how all of
 * manipulation can be done on images.
 */
public class ImageDriver {

  private static ImageModelInterface model;
  private static String input_file_path;
  private static String input_file_path2;
  private static float[][] gray_scale_matrix;
  private static float[][] sepia_tone_matrix;
  private static String output_file_path_gray_scale;
  private static String output_file_path_sepia_tone;
  private static float[][] blur_kernel;
  private static float[][] sharp_kernel;
  private static String output_file_path_blur1_1;
  private static String output_file_path_blur1_2;
  private static String output_file_path_sharp1_1;
  private static String output_file_path_sharp1_2;
  private static String output_file_path_color_density1_8GrayColors;
  private static String output_file_path_color_density1_512Colors;
  private static String output_file_path_color_density1_512withoutEssesnce;
  private static String output_file_path_gray_scale2;
  private static String output_file_path_sepia_tone2;
  private static String output_file_path_blur2_1;
  private static String output_file_path_blur2_2;
  private static String output_file_path_sharp2_1;
  private static String output_file_path_sharp2_2;
  private static String output_file_path_color_density2_8GrayColors;
  private static String output_file_path_color_density2_512Colors;
  private static String output_file_path_color_density2_512withoutEssesnce;
  private static String directory;

  /**
   * Driver program that loads and saves images and demonstrates how all of
   * manipulation can be done on images.
   * 
   * @param args Not used.
   */

  public static void main(String[] args) throws IOException {
    try {
      directory = new File(".").getCanonicalPath();
      input_file_path = args[0];
      input_file_path2 = args[1];
      
    } catch (ArrayIndexOutOfBoundsException e) {
      directory = directory + "\\res";
      
      input_file_path = String.format(directory + "\\Images\\Image1\\Original.png");
      input_file_path2 = String.format(directory + "\\Images\\Image2\\Original.png");

    } finally {
      setData(directory);

      System.out
          .println("******************Starting Processing for first image******************\n");

      model.loadImage(input_file_path);

      System.out.println("First image Loaded\n");

      model.colorTransformation(gray_scale_matrix);

      System.out.println("Applied Color Transformation - Gray Scale on the original image\n");

      model.saveImage(output_file_path_gray_scale);

      System.out.println(String.format("File saved at %s\n", output_file_path_gray_scale));

      model.colorTransformation(sepia_tone_matrix);

      System.out.println("Applied Color Transformation - Sepia Tone on the original image\n");

      model.saveImage(output_file_path_sepia_tone);

      System.out.println(String.format("File saved at %s\n", output_file_path_sepia_tone));

      model.filter(blur_kernel);

      System.out.println("Applied Filter - Blur on the original image\n");

      model.saveImage(output_file_path_blur1_1);

      System.out.println(String.format("File saved at %s\n", output_file_path_blur1_1));

      model.loadImage(output_file_path_blur1_1);

      model.filter(blur_kernel);

      System.out.println("Applied Filter - Blur on the blured image\n");

      model.saveImage(output_file_path_blur1_2);

      System.out.println(String.format("File saved at %s\n", output_file_path_blur1_2));

      model.loadImage(input_file_path);

      model.filter(sharp_kernel);

      System.out.println("Applied Filter - Sharpness on the original image\n");

      model.saveImage(output_file_path_sharp1_1);

      System.out.println(String.format("File saved at %s\n", output_file_path_sharp1_1));

      model.loadImage(output_file_path_sharp1_1);

      model.filter(sharp_kernel);

      System.out.println("Applied Filter - Sharpness on the sharped image\n");

      model.saveImage(output_file_path_sharp1_2);

      System.out.println(String.format("File saved at %s\n", output_file_path_sharp1_2));

      model.loadImage(input_file_path);

      model.reduceColorDensity(8, true);

      System.out.println(
          "Reduced Color Density to 512(8X8X8) colors with essence on the original image\n");

      model.saveImage(output_file_path_color_density1_512Colors);

      System.out
          .println(String.format("File saved at %s\n", output_file_path_color_density1_512Colors));

      model.reduceColorDensity(8, false);

      System.out.println(
          "Reduced Color Density to 512(8X8X8) colors without essence on the original image\n");

      model.saveImage(output_file_path_color_density1_512withoutEssesnce);

      System.out.println(
          String.format("File saved at %s\n", output_file_path_color_density1_512withoutEssesnce));

      model.loadImage(output_file_path_gray_scale);

      model.reduceColorDensity(2, true);

      System.out.println(
          "Reduced Color Density to 8(2X2X2) colors with essence on the gray scale image\n");

      model.saveImage(output_file_path_color_density1_8GrayColors);

      System.out.println(
          String.format("File saved at %s\n", output_file_path_color_density1_8GrayColors));

      System.out
          .println("******************Starting Processing for second image******************\n");

      model.loadImage(input_file_path2);

      System.out.println("Second image Loaded\n");

      model.colorTransformation(gray_scale_matrix);

      System.out.println("Applied Color Transformation - Gray Scale on the original image\n");

      model.saveImage(output_file_path_gray_scale2);

      System.out.println(String.format("File saved at %s\n", output_file_path_gray_scale2));

      model.colorTransformation(sepia_tone_matrix);

      System.out.println("Applied Color Transformation - Sepia Tone on the original image\n");

      model.saveImage(output_file_path_sepia_tone2);

      System.out.println(String.format("File saved at %s\n", output_file_path_sepia_tone2));

      model.filter(blur_kernel);

      System.out.println("Applied Filter - Blur on the original image\n");

      model.saveImage(output_file_path_blur2_1);

      System.out.println(String.format("File saved at %s\n", output_file_path_blur2_1));

      model.loadImage(output_file_path_blur2_1);

      model.filter(blur_kernel);

      System.out.println("Applied Filter - Blur on the blured image\n");

      model.saveImage(output_file_path_blur2_2);

      System.out.println(String.format("File saved at %s\n", output_file_path_blur2_2));

      model.loadImage(input_file_path2);

      model.filter(sharp_kernel);

      System.out.println("Applied Filter - Sharpness on the original image\n");

      model.saveImage(output_file_path_sharp2_1);

      System.out.println(String.format("File saved at %s\n", output_file_path_sharp2_1));

      model.loadImage(output_file_path_sharp2_1);

      model.filter(sharp_kernel);

      System.out.println("Applied Filter - Sharpness on the sharped image\n");

      model.saveImage(output_file_path_sharp2_2);

      System.out.println(String.format("File saved at %s\n", output_file_path_sharp2_2));

      model.loadImage(input_file_path2);

      model.reduceColorDensity(8, true);

      System.out.println("Reduced Color Density to 512(8X8X8) colors on the original image\n");

      model.saveImage(output_file_path_color_density2_512Colors);

      System.out
          .println(String.format("File saved at %s\n", output_file_path_color_density2_512Colors));

      model.reduceColorDensity(8, false);

      System.out.println(
          "Reduced Color Density to 512(8X8X8) colors without essence on the original image\n");

      model.saveImage(output_file_path_color_density2_512withoutEssesnce);

      System.out.println(
          String.format("File saved at %s\n", output_file_path_color_density2_512withoutEssesnce));

      model.loadImage(output_file_path_gray_scale2);

      model.reduceColorDensity(2, true);

      System.out.println("Reduced Color Density to 8(2X2X2) colors on the gray scale image\n");

      model.saveImage(output_file_path_color_density2_8GrayColors);

      System.out.println(
          String.format("File saved at %s\n", output_file_path_color_density2_8GrayColors));

      System.out.println(String.format("********************Done********************"));

    }
  }

  /**
   * This is a private helper method to set required data.
   * @throws IOException 
   */
  private static void setData(String directory) throws IOException {
    model = new ImageModel();

    output_file_path_gray_scale = String
        .format(directory + "\\Images\\Image1\\GrayScale1.png");

    output_file_path_sepia_tone = String
        .format(directory + "\\Images\\Image1\\SepiaTone1.png");

    output_file_path_blur1_1 = String.format(directory + "\\Images\\Image1\\Blur1.png");

    output_file_path_blur1_2 = String.format(directory + "\\Images\\Image1\\Blur2.png");

    output_file_path_sharp1_1 = String.format(directory + "\\Images\\Image1\\Sharp1.png");

    output_file_path_sharp1_2 = String.format(directory + "\\Images\\Image1\\Sharp2.png");

    output_file_path_color_density1_512Colors = String
        .format(directory + "\\Images\\Image1\\ReduceCD512Colors.png");

    output_file_path_color_density1_8GrayColors = String
        .format(directory + "\\Images\\Image1\\ReduceCD8GrayColors.png");

    output_file_path_color_density1_512withoutEssesnce = String
        .format(directory + "\\Images\\Image1\\ReduceCD512WithoutEssence.png");

    output_file_path_gray_scale2 = String
        .format(directory + "\\Images\\Image2\\GrayScale1.png");

    output_file_path_sepia_tone2 = String
        .format(directory + "\\Images\\Image2\\SepiaTone1.png");

    output_file_path_blur2_1 = String.format(directory + "\\Images\\Image2\\Blur1.png");

    output_file_path_blur2_2 = String.format(directory + "\\Images\\Image2\\Blur2.png");

    output_file_path_sharp2_1 = String.format(directory + "\\Images\\Image2\\Sharp1.png");

    output_file_path_sharp2_2 = String.format(directory + "\\Images\\Image2\\Sharp2.png");

    output_file_path_color_density2_512Colors = String
        .format(directory + "\\Images\\Image2\\ReduceCD512Colors.png");

    output_file_path_color_density2_8GrayColors = String
        .format(directory + "\\Images\\Image2\\ReduceCD8GrayColors.png");

    output_file_path_color_density2_512withoutEssesnce = String
        .format(directory + "\\Images\\Image2\\ReduceCD512WithoutEssence.png");

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
