package images;

import java.io.IOException;

/**
 * Driver program to loads and saves images and demonstrates how all of
 * manipulation can be done on images.
 */
public class ImageDriver {

  private static ImageModelInterface model;
  private static String input_file_path;
  private static int[][][] original_rgb_buffer;
  private static float[][] gray_scale_matrix;
  private static float[][] sepia_tone_matrix;
  private static String output_file_path_gray_scale;
  private static String output_file_path_sepia_tone;

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

    original_rgb_buffer = model.getPixels();

    System.out.println("Image Loaded\n");

    System.out.println(model.toString());

    System.out.println("Applying Color Transformation - Gray Scale on the original image\n");

    model.colorTransformation(gray_scale_matrix);

    System.out.println("Applied Color Transformation - Gray Scale on the original image\n");

    model.saveImage(output_file_path_gray_scale);

    System.out.println(String.format("File saved at %s\n", output_file_path_gray_scale));

    System.out.println("Applying Color Transformation - Sepia Tone on the original image\n");

    model.setPixels(original_rgb_buffer);

    model.colorTransformation(sepia_tone_matrix);

    System.out.println("Applied Color Transformation - Sepia Tone on the original image\n");

    model.saveImage(output_file_path_sepia_tone);

    System.out.println(String.format("File saved at %s\n", output_file_path_sepia_tone));

  }

  /**
   * This is a private helper method to set required data.
   */
  private static void setData() {
    model = new ImageModel();

    input_file_path = "..\\ImageModel\\res\\Images\\TestImage.jpg";

    output_file_path_gray_scale = "..\\ImageModel\\res\\Images\\GrayScale.png";

    output_file_path_sepia_tone = "..\\ImageModel\\res\\Images\\SepiaTone.png";

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
  }

}
