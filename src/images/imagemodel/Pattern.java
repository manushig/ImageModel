package images.imagemodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

/**
 * Pattern, implements an interface PatternInterface that defines methods to
 * generate cross-stitch pattern from the image.
 *
 */
public class Pattern implements PatternInterface {

  @Override
  public String doPattern(int[][][] rgbBuffer, int noOfSquaresAcross) throws IOException {
    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");

    int[][][] rgbBuffer_copy = ImageOperationsUtility.copyArray(rgbBuffer);

    int[][][] pattern_buffer = new int[rgbBuffer_copy.length][rgbBuffer_copy[0].length][1];

    List<DmcFloss> dmcDataSet = ImageOperationsUtility.loadDmcFloss();

    Objects.requireNonNull(dmcDataSet);

    int height = rgbBuffer_copy.length;
    int width = rgbBuffer_copy[0].length;

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {

        int[] color = { rgbBuffer_copy[x][y][0], rgbBuffer_copy[x][y][1], rgbBuffer_copy[x][y][2] };
        int dmc_id = getClosetColorId(color, dmcDataSet);

        pattern_buffer[x][y][0] = dmc_id;
      }
    }
    return printPattern(pattern_buffer, noOfSquaresAcross, dmcDataSet);
  }

  /**
   * Private Helper method to print the cross-stitch pattern.
   * 
   * @param dmcBuffer         It is the processed 2 D array of RGB color
   * @param noOfSquaresAcross It is the number of squares across the width of the
   *                          image.
   * @param dmcDataSet        It is the DMC Floss RGB conversion dataset.
   * @return It returns the generated pattern.
   */
  private String printPattern(int[][][] dmcBuffer, int noOfSquaresAcross,
      List<DmcFloss> dmcDataSet) {

    List<Legend> legendSet = new ArrayList<Legend>();

    int image_height = dmcBuffer.length;
    int image_width = dmcBuffer[0].length;

    int square_length = image_width / noOfSquaresAcross;

    int pixelate_x = image_height / square_length;

    int m = pixelate_x;
    int n = noOfSquaresAcross;

    StringBuilder pattern = new StringBuilder();

    pattern.append(String.format("%d x %d\n\n", n, m));

    int i = 0;
    int j = 0;
    for (int y = 0; y < m; y++) {
      for (int x = 0; x < n; x++) {
        int id = dmcBuffer[i][j][0];
        pattern.append(String.format("%c", dmcDataSet.get(id).getSymbol()));
        legendSet
            .add(new Legend(id, dmcDataSet.get(id).getDmcCode(), dmcDataSet.get(id).getSymbol()));
        j += square_length;
      }
      pattern.append("\n");
      j = 0;
      i += square_length;

    }

    pattern.append("\nLEGEND\n");

    Set<Legend> legendListTree = new TreeSet<Legend>(legendSet);

    for (Legend legend : legendListTree) {
      pattern.append(String.format("%c DMC-%s\n", legend.getSymbol(), legend.getDmcCode()));
    }

    return pattern.toString();
  }

  /**
   * Private helper method to calculate the closet Color to the given color.
   * 
   * @param color      It is the array of RGB color.
   * @param dmcDataSet It is the DMC Floss RGB conversion dataset.
   * @return the closet color to the given color.
   */
  private int getClosetColorId(int[] color, List<DmcFloss> dmcDataSet) {

    Integer[] distanceArray = new Integer[dmcDataSet.size()];
    int ctr = 0;
    for (DmcFloss dmcSet : dmcDataSet) {
      int[] dmcColor = { dmcSet.getRedValue(), dmcSet.getGreenValue(), dmcSet.getBlueValue() };
      distanceArray[ctr] = (int) Math.round(calculateColorDistance(color, dmcColor));
      ctr++;
    }

    int[] sortedIndices = IntStream.range(0, distanceArray.length).boxed()
        .sorted((i, j) -> distanceArray[i].compareTo(distanceArray[j])).mapToInt(ele -> ele)
        .toArray();

    int value = sortedIndices[0];

    return value;
  }

  /**
   * Private helper method to calculate the redmean.
   * 
   * @param c1 It is an array of RGB of Color 1.
   * @param c2 It is an array of RGB of Color 2.
   * @return the redmean value
   */
  private double calculateColorDistance(int[] c1, int[] c2) {
    double rmean = (c1[0] + c2[0]) / 2;
    int r = c1[0] - c2[0];
    int g = c1[1] - c2[1];
    int b = c1[2] - c2[2];
    double weightR = 2 + rmean / 256;
    double weightG = 4.0;
    double weightB = 2 + (255 - rmean) / 256;
    return Math.sqrt(weightR * r * r + weightG * g * g + weightB * b * b);
  }

}
