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
  private static String output_file_path_gray_scale;
  private static String output_file_path_sepia_tone;
  private static String output_file_path_blur1_1;
  private static String output_file_path_blur1_2;
  private static String output_file_path_sharp1_1;
  private static String output_file_path_sharp1_2;
  private static String output_file_path_color_density1_8GrayColors;
  private static String output_file_path_color_density1_512Colors;
  private static String output_file_path_gray_scale2;
  private static String output_file_path_sepia_tone2;
  private static String output_file_path_blur2_1;
  private static String output_file_path_blur2_2;
  private static String output_file_path_sharp2_1;
  private static String output_file_path_sharp2_2;
  private static String output_file_path_color_density2_8GrayColors;
  private static String output_file_path_color_density2_512Colors;
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

      model.grayScale();

      System.out.println("Applied Color Transformation - Gray Scale on the original image\n");

      model.saveImage(output_file_path_gray_scale);

      System.out.println(String.format("File saved at %s\n", output_file_path_gray_scale));

      model.sepia();

      System.out.println("Applied Color Transformation - Sepia Tone on the original image\n");

      model.saveImage(output_file_path_sepia_tone);

      System.out.println(String.format("File saved at %s\n", output_file_path_sepia_tone));

      model.blur();

      System.out.println("Applied Filter - Blur on the original image\n");

      model.saveImage(output_file_path_blur1_1);

      System.out.println(String.format("File saved at %s\n", output_file_path_blur1_1));

      model.loadImage(output_file_path_blur1_1);

      model.blur();

      System.out.println("Applied Filter - Blur on the blured image\n");

      model.saveImage(output_file_path_blur1_2);

      System.out.println(String.format("File saved at %s\n", output_file_path_blur1_2));

      model.loadImage(input_file_path);

      model.sharpen();

      System.out.println("Applied Filter - Sharpness on the original image\n");

      model.saveImage(output_file_path_sharp1_1);

      System.out.println(String.format("File saved at %s\n", output_file_path_sharp1_1));

      model.loadImage(output_file_path_sharp1_1);

      model.sharpen();

      System.out.println("Applied Filter - Sharpness on the sharped image\n");

      model.saveImage(output_file_path_sharp1_2);

      System.out.println(String.format("File saved at %s\n", output_file_path_sharp1_2));

      model.loadImage(input_file_path);

      model.dither(8);

      System.out.println(
          "Reduced Color Density to 512(8X8X8) colors with essence on the original image\n");

      model.saveImage(output_file_path_color_density1_512Colors);

      model.loadImage(output_file_path_gray_scale);

      model.dither(2);

      System.out.println(
          "Reduced Color Density to 8(2X2X2) colors with essence on the gray scale image\n");

      model.saveImage(output_file_path_color_density1_8GrayColors);

      System.out.println(
          String.format("File saved at %s\n", output_file_path_color_density1_8GrayColors));

      System.out
          .println("******************Starting Processing for second image******************\n");

      model.loadImage(input_file_path2);

      System.out.println("Second image Loaded\n");

      model.grayScale();

      System.out.println("Applied Color Transformation - Gray Scale on the original image\n");

      model.saveImage(output_file_path_gray_scale2);

      System.out.println(String.format("File saved at %s\n", output_file_path_gray_scale2));

      model.sepia();

      System.out.println("Applied Color Transformation - Sepia Tone on the original image\n");

      model.saveImage(output_file_path_sepia_tone2);

      System.out.println(String.format("File saved at %s\n", output_file_path_sepia_tone2));

      model.blur();

      System.out.println("Applied Filter - Blur on the original image\n");

      model.saveImage(output_file_path_blur2_1);

      System.out.println(String.format("File saved at %s\n", output_file_path_blur2_1));

      model.loadImage(output_file_path_blur2_1);

      model.blur();

      System.out.println("Applied Filter - Blur on the blured image\n");

      model.saveImage(output_file_path_blur2_2);

      System.out.println(String.format("File saved at %s\n", output_file_path_blur2_2));

      model.loadImage(input_file_path2);

      model.sharpen();

      System.out.println("Applied Filter - Sharpness on the original image\n");

      model.saveImage(output_file_path_sharp2_1);

      System.out.println(String.format("File saved at %s\n", output_file_path_sharp2_1));

      model.loadImage(output_file_path_sharp2_1);

      model.sharpen();

      System.out.println("Applied Filter - Sharpness on the sharped image\n");

      model.saveImage(output_file_path_sharp2_2);

      System.out.println(String.format("File saved at %s\n", output_file_path_sharp2_2));

      model.loadImage(input_file_path2);

      model.dither(8);

      System.out.println("Reduced Color Density to 512(8X8X8) colors on the original image\n");

      model.saveImage(output_file_path_color_density2_512Colors);

      System.out
          .println(String.format("File saved at %s\n", output_file_path_color_density2_512Colors));

      model.loadImage(output_file_path_gray_scale2);

      model.dither(2);

      System.out.println("Reduced Color Density to 8(2X2X2) colors on the gray scale image\n");

      model.saveImage(output_file_path_color_density2_8GrayColors);

      System.out.println(
          String.format("File saved at %s\n", output_file_path_color_density2_8GrayColors));

      System.out.println(String.format("********************Done********************"));

    }
  }

  /**
   * This is a private helper method to set required data.
   * 
   */
  private static void setData(String directory) {
    model = new ImageModel();

    output_file_path_gray_scale = String.format(directory + "\\Images\\Image1\\GrayScale1.png");

    output_file_path_sepia_tone = String.format(directory + "\\Images\\Image1\\SepiaTone1.png");

    output_file_path_blur1_1 = String.format(directory + "\\Images\\Image1\\Blur1.png");

    output_file_path_blur1_2 = String.format(directory + "\\Images\\Image1\\Blur2.png");

    output_file_path_sharp1_1 = String.format(directory + "\\Images\\Image1\\Sharp1.png");

    output_file_path_sharp1_2 = String.format(directory + "\\Images\\Image1\\Sharp2.png");

    output_file_path_color_density1_512Colors = String
        .format(directory + "\\Images\\Image1\\ReduceCD512Colors.png");

    output_file_path_color_density1_8GrayColors = String
        .format(directory + "\\Images\\Image1\\ReduceCD8GrayColors.png");

    output_file_path_gray_scale2 = String.format(directory + "\\Images\\Image2\\GrayScale1.png");

    output_file_path_sepia_tone2 = String.format(directory + "\\Images\\Image2\\SepiaTone1.png");

    output_file_path_blur2_1 = String.format(directory + "\\Images\\Image2\\Blur1.png");

    output_file_path_blur2_2 = String.format(directory + "\\Images\\Image2\\Blur2.png");

    output_file_path_sharp2_1 = String.format(directory + "\\Images\\Image2\\Sharp1.png");

    output_file_path_sharp2_2 = String.format(directory + "\\Images\\Image2\\Sharp2.png");

    output_file_path_color_density2_512Colors = String
        .format(directory + "\\Images\\Image2\\ReduceCD512Colors.png");

    output_file_path_color_density2_8GrayColors = String
        .format(directory + "\\Images\\Image2\\ReduceCD8GrayColors.png");

  }

}
