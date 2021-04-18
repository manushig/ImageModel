package images.imagemodel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * ImageOperationsUtility, is a utility class that has methods which helps to do
 * various operations on the Image.
 * 
 */
public class ImageOperationsUtility {
  /**
   * This method generate the kernel for sharpening the image.
   * 
   * @return the kernel for sharpen filter.
   */
  protected static float[][] getSharpKernel() {

    float[][] sharp_kernel = new float[5][5];

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

    return sharp_kernel;
  }

  /**
   * This method generate the kernel for blurring the image.
   * 
   * @return the kernel for blur filter.
   */
  protected static float[][] getBlurKernel() {
    float[][] blur_kernel = new float[3][3];

    blur_kernel[0][0] = 1 / 16.0f;
    blur_kernel[0][1] = 1 / 8.0f;
    blur_kernel[0][2] = 1 / 16.0f;

    blur_kernel[1][0] = 1 / 8.0f;
    blur_kernel[1][1] = 1 / 4.0f;
    blur_kernel[1][2] = 1 / 8.0f;

    blur_kernel[2][0] = 1 / 16.0f;
    blur_kernel[2][1] = 1 / 8.0f;
    blur_kernel[2][2] = 1 / 16.0f;

    return blur_kernel;
  }

  /**
   * This method generate the color transformation matrix for generating sepia
   * tone effect on the image.
   * 
   * @return the matrix for sepia color transformation.
   */
  protected static float[][] getSepiaToneMatrix() {
    float[][] sepia_tone_matrix = new float[3][3];

    sepia_tone_matrix[0][0] = 0.393f;
    sepia_tone_matrix[0][1] = 0.769f;
    sepia_tone_matrix[0][2] = 0.189f;

    sepia_tone_matrix[1][0] = 0.349f;
    sepia_tone_matrix[1][1] = 0.686f;
    sepia_tone_matrix[1][2] = 0.168f;

    sepia_tone_matrix[2][0] = 0.272f;
    sepia_tone_matrix[2][1] = 0.534f;
    sepia_tone_matrix[2][2] = 0.131f;

    return sepia_tone_matrix;
  }

  /**
   * This method generate the color transformation matrix for generating grayscale
   * tone effect on the image.
   * 
   * @return the matrix for grayscale color transformation.
   */
  protected static float[][] getGrayScaleMatrix() {
    float[][] gray_scale_matrix = new float[3][3];

    gray_scale_matrix[0][0] = 0.2126f;
    gray_scale_matrix[0][1] = 0.7152f;
    gray_scale_matrix[0][2] = 0.0722f;

    gray_scale_matrix[1][0] = 0.2126f;
    gray_scale_matrix[1][1] = 0.7152f;
    gray_scale_matrix[1][2] = 0.0722f;

    gray_scale_matrix[2][0] = 0.2126f;
    gray_scale_matrix[2][1] = 0.7152f;
    gray_scale_matrix[2][2] = 0.0722f;

    return gray_scale_matrix;
  }

  /**
   * This method create a clone of an given 3D array.
   * 
   * @param source 3D Array whose clone is required
   * @return Cloned 3D array
   */
  protected static int[][][] copyArray(int[][][] source) {
    Objects.requireNonNull(source, "3 D array value cannot be null.");

    int[][][] target = source.clone();

    for (int i = 0; i < source.length; i++) {
      target[i] = source[i].clone();

      for (int j = 0; j < source[i].length; j++) {
        target[i][j] = source[i][j].clone();
      }
    }

    return target;
  }

  /**
   * It returns the number of squares required when generating the cross-stitch
   * pattern.
   * 
   * @return number of squares.
   */
  protected static int getNoofSquaresAcrossPattern() {
    return 50;
  }

  /**
   * This method writes the generated pattern to the given file.
   * 
   * @param generatedPattern it is the generated pattern which need to be saved to
   *                         a file.
   * @param filename         it is the name of the file where pattern needs to be
   *                         saved.
   * @throws IOException if there is an error while writing to a file.
   */
  protected static void savePattern(String generatedPattern, String filename) throws IOException {
    Objects.requireNonNull(generatedPattern);
    Objects.requireNonNull(filename);
    FileOutputStream fis = new FileOutputStream(filename, false);
    OutputStreamWriter isr = new OutputStreamWriter(fis, StandardCharsets.UTF_16BE);
    BufferedWriter writer = new BufferedWriter(isr);
    writer.write(generatedPattern);
    writer.close();
  }

  /**
   * This method reads the file and populate the DMC Floss RGB conversion values
   * to the list.
   * 
   * @return the list holding the DMC Floss RGB conversion values along with the
   *         unicode value.
   * @throws IOException if there is an error while reading from the file.
   */
  protected static List<DmcFloss> loadDmcFloss() throws IOException {
    String directory = new File(".").getCanonicalPath();
    String fileName = "DMCFloss.txt";
    String fileSeperator = FileSystems.getDefault().getSeparator();
    String file = String.format("%s%s%s", directory, fileSeperator, fileName);

    Readable reader = new BufferedReader(new FileReader(file));

    Scanner scan = new Scanner(reader);

    List<DmcFloss> dmcDataSet = new ArrayList<DmcFloss>();

    int i = 0;
    while (scan.hasNextLine()) {
      String[] in = scan.next().split(",");
      String dmc = in[0];
      int red = Integer.parseInt(in[1]);
      int green = Integer.parseInt(in[2]);
      int blue = Integer.parseInt(in[3]);
      char symbol = (char) Integer.parseInt(String.valueOf(in[4]), 16);

      dmcDataSet.add(new DmcFloss(i, dmc, red, green, blue, symbol));
      i++;

    }
    scan.close();

    return dmcDataSet;
  }

  /**
   * This method create a clone of an given Legend list.
   * 
   * @param source Legend list whose clone is required
   * @return Cloned Legend list
   */
  protected static List<Legend> copyLegendList(List<Legend> sourecLegendList) {
    Objects.requireNonNull(sourecLegendList, "Legend List cannot be null.");

    List<Legend> copiedList = new ArrayList<Legend>();
    for (int i = 0; i < sourecLegendList.size(); i++) {
      Objects.requireNonNull(sourecLegendList.get(i));
      copiedList.add(sourecLegendList.get(i));
    }
    return copiedList;
  }

  /**
   * This method create a clone of an given 2D array.
   * 
   * @param source 2D Array whose clone is required
   * @return Cloned 2D array
   */
  protected static int[][] copyArray(int[][] source) {
    Objects.requireNonNull(source, "2 D array value cannot be null.");

    int[][] target = source.clone();

    for (int i = 0; i < source.length; i++) {
      target[i] = source[i].clone();
    }

    return target;
  }

  /**
   * This method reads the file and populate the siena font representation of
   * cross-stitch alphabets.
   * 
   * @return the siena font representation of cross-stitch alphabets.
   * @throws IOException if there is an error while reading from the file.
   */
  protected static List<CrossStitchAlphabet> loadAlphabetSet() throws IOException {
    String directory = new File(".").getCanonicalPath();
    String fileName = "Alphabet.txt";
    String fileSeperator = FileSystems.getDefault().getSeparator();
    String file = String.format("%s%s%s", directory, fileSeperator, fileName);

    Readable reader = new BufferedReader(new FileReader(file));

    Scanner scan = new Scanner(reader);

    List<CrossStitchAlphabet> alphabetDataSet = new ArrayList<CrossStitchAlphabet>();

    int i = 0;
    while (scan.hasNext()) {
      String[] in = scan.nextLine().split(" ");
      char alphabet = (char) Integer.parseInt(String.valueOf(in[0]), 16);
      int xAxis = Integer.parseInt(in[1]);

      int[][] alphabetArray = new int[8][5];

      int l = 2;
      for (int j = 0; j < 8; j++) {
        for (int k = 0; k < 5; k++) {
          alphabetArray[j][k] = Integer.parseInt(in[l]);
          l++;
        }
      }

      alphabetDataSet.add(new CrossStitchAlphabet(i, alphabet, alphabetArray, xAxis));
      i++;

    }
    scan.close();

    return alphabetDataSet;
  }

}
