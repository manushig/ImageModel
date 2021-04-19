package images.imagemodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

import javafx.util.Pair;

/**
 * Pattern, implements an interface PatternInterface that defines methods to
 * generate cross-stitch pattern from the image.
 *
 */
public class Pattern implements PatternInterface {

  private List<DmcFloss> dmcDataSet;

  /**
   * Constructs a Pattern.
   * 
   * @throws IOException if there is an error while writing the pattern to the
   *                     file.
   */
  public Pattern() throws IOException {
    List<DmcFloss> dmcSet = ImageOperationsUtility.loadDmcFloss();
    Objects.requireNonNull(dmcSet);
    this.dmcDataSet = dmcSet;
  }

  /**
   * Private helper method to calculate the closet Color to the given color.
   * 
   * @param color      It is the array of RGB color.
   * @param dmcDataSet It is the DMC Floss RGB conversion dataset.
   * @return the closet color to the given color.
   */
  private int getClosetColorId(int[] color, List<DmcFloss> dmcFlossList) {

    Integer[] distanceArray = new Integer[dmcFlossList.size()];
    int ctr = 0;
    for (DmcFloss dmcSet : dmcFlossList) {
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

  @Override
  public Pair<int[][][], List<Legend>> doPattern(int[][][] rgbBuffer, int noOfSquaresAcross)
      throws IOException {
    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");

    int[][][] rgbBuffer_copy = ImageOperationsUtility.copyArray(rgbBuffer);

    int[][][] pattern_buffer = new int[rgbBuffer_copy.length]
        [rgbBuffer_copy[0].length][rgbBuffer_copy[0][0].length];

    int[][][] legend_buffer = new int[rgbBuffer_copy.length][rgbBuffer_copy[0].length][1];

    Objects.requireNonNull(dmcDataSet);

    int height = rgbBuffer_copy.length;
    int width = rgbBuffer_copy[0].length;

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {

        int[] color = { rgbBuffer_copy[x][y][0], rgbBuffer_copy[x][y][1], rgbBuffer_copy[x][y][2] };
        int dmc_id = getClosetColorId(color, this.dmcDataSet);

        pattern_buffer[x][y][0] = dmcDataSet.get(dmc_id).getRedValue();
        pattern_buffer[x][y][1] = dmcDataSet.get(dmc_id).getGreenValue();
        pattern_buffer[x][y][2] = dmcDataSet.get(dmc_id).getBlueValue();

        legend_buffer[x][y][0] = dmc_id;
      }
    }
    Set<Legend> legendSet = generateLegend(legend_buffer, noOfSquaresAcross);
    List<Legend> legendList = new ArrayList<>(legendSet);

    return new Pair<int[][][], List<Legend>>(pattern_buffer, legendList);
  }

  /**
   * Private helper method to generate the Legends list.
   * 
   * @param dmcBuffer         It is 3D Array of DMC code id's'
   * @param noOfSquaresAcross It is the number of squares across the width of the
   *                          image.
   * @return Set of Legends
   */
  private Set<Legend> generateLegend(int[][][] dmcBuffer, int noOfSquaresAcross) {

    List<Legend> legendSet = new ArrayList<Legend>();

    int image_height = dmcBuffer.length;
    int image_width = dmcBuffer[0].length;

    int square_length = image_width / noOfSquaresAcross;

    int pixelate_x = image_height / square_length;

    int m = pixelate_x;
    int n = noOfSquaresAcross;

    int i = 0;
    int j = 0;
    for (int y = 0; y < m; y++) {
      for (int x = 0; x < n; x++) {
        int id = dmcBuffer[i][j][0];

        legendSet.add(new Legend(id, dmcDataSet.get(id).getDmcCode(),
            dmcDataSet.get(id).getSymbol(), dmcDataSet.get(id).getRedValue(),
            dmcDataSet.get(id).getGreenValue(), dmcDataSet.get(id).getBlueValue()));
        j += square_length;
      }

      j = 0;
      i += square_length;

    }

    Set<Legend> legendListTree = new TreeSet<Legend>(legendSet);

    return legendListTree;
  }

  @Override
  public Pair<int[][][], List<Legend>> doReplaceColorPattern(int[][][] rgbBuffer,
      int noOfSquaresAcross, int xCordinate, int yCordinate, String dmcCode,
      List<Legend> legendList) {
    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");
    Objects.requireNonNull(dmcCode, "DMC Code value cannot be null.");
    Objects.requireNonNull(legendList, "LegendList cannot be null.");

    int[][][] pattern_buffer = ImageOperationsUtility.copyArray(rgbBuffer);

    int[] color = new int[3];
    color[0] = pattern_buffer[yCordinate][xCordinate][0];
    color[1] = pattern_buffer[yCordinate][xCordinate][1];
    color[2] = pattern_buffer[yCordinate][xCordinate][2];

    DmcFloss dmcFlossToReplaceWith = getDmcFlossDetails(dmcCode);

    Objects.requireNonNull(dmcFlossToReplaceWith);

    int height = pattern_buffer.length;
    int width = pattern_buffer[0].length;

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        int redColor = pattern_buffer[x][y][0];
        int greenColor = pattern_buffer[x][y][1];
        int blueColor = pattern_buffer[x][y][2];

        if (redColor == color[0] && greenColor == color[1] && blueColor == color[2]) {
          pattern_buffer[x][y][0] = dmcFlossToReplaceWith.getRedValue();
          pattern_buffer[x][y][1] = dmcFlossToReplaceWith.getGreenValue();
          pattern_buffer[x][y][2] = dmcFlossToReplaceWith.getBlueValue();
        }
      }
    }

    String dmcFlossCodeToReplace = getDmcFlossDetails(color[0], color[1], color[2], legendList);

    Objects.requireNonNull(dmcFlossCodeToReplace);

    Set<Legend> legendSet = modifyLegendList(legendList, dmcFlossCodeToReplace, null);
    List<Legend> updatedLegendList = new ArrayList<>(legendSet);

    return new Pair<int[][][], List<Legend>>(pattern_buffer, updatedLegendList);
  }

  private DmcFloss getDmcFlossDetails(String dmcCode) {
    for (DmcFloss dmcFloss : dmcDataSet) {
      if (dmcFloss.getDmcCode().equals(dmcCode)) {
        return dmcFloss;
      }
    }
    return null;
  }

  /**
   * Private helper method to get DMC code of the given color.
   * 
   * @param redColor   It is the red color
   * @param greenColor It is the green color
   * @param blueColor  It is the blue color
   * @param legendList It is the list of Legend
   * @return
   */
  private String getDmcFlossDetails(int redColor, int greenColor, int blueColor,
      List<Legend> legendList) {

    for (Legend legend : legendList) {
      if (legend.getRed() == redColor && legend.getGreen() == greenColor
          && legend.getBlue() == blueColor) {

        return legend.getDmcCode();
      }
    }
    return null;
  }

  /**
   * Private helper method to modify the legend list.
   * 
   * @param legendList    It is the list of Legend
   * @param dmcFlossCode  It is DMC Floss to be removed
   * @param dmcFlossToAdd It is DMC Floss to be added
   * @return Updated Legend list
   */
  private Set<Legend> modifyLegendList(List<Legend> legendList, String dmcFlossCode,
      DmcFloss dmcFlossToAdd) {
    int legendIndex = -1;
    for (Legend legend : legendList) {
      if (legend.getDmcCode().equals(dmcFlossCode)) {
        legendIndex = legendList.indexOf(legend);
        break;
      }
    }

    if (legendIndex >= 0) {
      legendList.remove(legendIndex);
    }

    if (!Objects.isNull(dmcFlossToAdd)) {
      legendList.add(new Legend(dmcFlossToAdd.getId(), dmcFlossToAdd.getDmcCode(),
          dmcFlossToAdd.getSymbol(), dmcFlossToAdd.getRedValue(), dmcFlossToAdd.getGreenValue(),
          dmcFlossToAdd.getBlueValue()));
    }

    Set<Legend> legendListTree = new TreeSet<Legend>(legendList);
    return legendListTree;
  }

  @Override
  public List<Legend> doRemoveColorPattern(int noOfSquaresAcross, String dmcCode,
      List<Legend> legendList) {
    Objects.requireNonNull(dmcCode, "DMC Code value cannot be null.");
    Objects.requireNonNull(legendList, "LegendList cannot be null.");

    DmcFloss dmcFlossToRemoveFromLegend = getDmcFlossDetails(dmcCode);
    DmcFloss blankFloss = generateBlankFloss(dmcFlossToRemoveFromLegend, legendList);

    Set<Legend> legendSet = modifyLegendList(legendList, dmcCode, blankFloss);
    List<Legend> updatedLegendList = new ArrayList<>(legendSet);

    return updatedLegendList;
  }

  /**
   * Private helper method to generate the Blank DMC Floss details.
   * 
   * @param dmcfloss   DMC Floss whose will be removed from the pattern
   * @param legendList It is the list of Legend
   * @return Blank DMC Floss details
   */
  private DmcFloss generateBlankFloss(DmcFloss dmcfloss, List<Legend> legendList) {
    int id = getLeastId(legendList) - 1;
    return new DmcFloss(id, "Blank", dmcfloss.getRedValue(), dmcfloss.getGreenValue(),
        dmcfloss.getBlueValue(), '.');

  }

  /**
   * Private helper method to get Least id in the legend list.
   * 
   * @param legendList It is the list of Legend
   * @return least id in the legend list
   */
  private int getLeastId(List<Legend> legendList) {
    Collections.sort(legendList);
    return legendList.get(0).getDmcId();
  }

  @Override
  public Pair<int[][][], List<Legend>> doAddTextPattern(int[][][] rgbBuffer, int noOfSquaresAcross,
      String textToAdd, String dmcCode, List<Legend> legendList) throws IOException {
    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");

    Objects.requireNonNull(textToAdd, "Text cannot be null.");

    Objects.requireNonNull(dmcCode, "dmcCode cannot be null.");

    Objects.requireNonNull(legendList, "Legend List cannot be null.");

    String[] textArray = textToAdd.split("\n");

    int[][][] pattern_buffer = ImageOperationsUtility.copyArray(rgbBuffer);

    List<CrossStitchAlphabet> alphabetDataSet = ImageOperationsUtility.loadAlphabetSet();

    Objects.requireNonNull(alphabetDataSet);

    DmcFloss colorSelectedFloss = getDmcFlossDetails(dmcCode);

    int red_color = colorSelectedFloss.getRedValue();
    int green_color = colorSelectedFloss.getGreenValue();
    int blue_color = colorSelectedFloss.getBlueValue();

    int image_height = pattern_buffer.length;
    int image_width = pattern_buffer[0].length;

    int square_length = image_width / noOfSquaresAcross;

    int offset_x = 0;
    int offset_y = 0;
    int b;
    int p_b;
    int alphabetWidth = 0;
    int alphabetHeight = 0;
    for (String text : textArray) {
      char[] alphabetArray = text.toCharArray();
      for (char alphabet : alphabetArray) {
        CrossStitchAlphabet alphabetDetails = null;
        for (CrossStitchAlphabet alphabetData : alphabetDataSet) {
          if (alphabetData.getAlphabet() == alphabet) {
            alphabetDetails = alphabetData;
            break;
          }
        }

        if (!Objects.isNull(alphabetDetails)) {

          int[][] alphabetPattern = alphabetDetails.getAlphabetPattern();

          alphabetWidth = alphabetDetails.getNoOfSquaresOccupiedInXAxis();
          alphabetHeight = alphabetPattern.length;

          if (((offset_x + (square_length * (alphabetWidth + 1))) < image_width)
              && ((offset_y + (square_length * (alphabetHeight + 1))) < image_height)) {
            for (int a = offset_y, p_a = 0; (a < (offset_y + (alphabetHeight * square_length)))
                && (p_a < alphabetHeight); p_a++) {
              for (b = offset_x, p_b = 0; (b < (offset_x + (alphabetWidth * square_length)))
                  && (p_b < alphabetWidth); p_b++) {

                for (int r_y = a; r_y < (a + square_length); r_y++) {
                  for (int r_x = b; r_x < (b + square_length); r_x++) {

                    if (alphabetPattern[p_a][p_b] == 1) {

                      pattern_buffer[r_y][r_x][0] = red_color;
                      pattern_buffer[r_y][r_x][1] = green_color;
                      pattern_buffer[r_y][r_x][2] = blue_color;
                    }
                  }
                }

                b += square_length;
              }
              a += square_length;

            }

          }

          if ((offset_x + (square_length * (alphabetWidth + 1))) < image_width) {

            offset_x = offset_x + (square_length * (alphabetWidth + 1));

          } else {

            if ((offset_y + (square_length * (alphabetHeight + 1))) < image_height) {

              offset_x = 0;

              offset_y = offset_y + (square_length * (alphabetHeight + 1));

            } else {

              break;
            }
          }

        }
      }
      if ((offset_y + (square_length * (alphabetHeight + 1))) < image_height) {

        offset_x = 0;

        offset_y = offset_y + (square_length * (alphabetHeight + 1));

      } else {

        break;
      }
    }

    Set<Legend> legendSet = addToLegendList(legendList, dmcCode, colorSelectedFloss);
    List<Legend> updatedLegendList = new ArrayList<>(legendSet);

    return new Pair<int[][][], List<Legend>>(pattern_buffer, updatedLegendList);
  }

  /**
   * Private helper method to add DMC floss code to the Legend list.
   * 
   * @param legendList    It is the list of Legend
   * @param dmcFlossCode  It is the DMC Floss code whose details needs to be added
   *                      to Legend list
   * @param dmcFlossToAdd It is the DMC Floss needs to be added to the Legend list
   * @return Updated Legend list
   */
  private Set<Legend> addToLegendList(List<Legend> legendList, String dmcFlossCode,
      DmcFloss dmcFlossToAdd) {
    boolean dmcFlossNotAdded = true;
    for (Legend legend : legendList) {
      if (legend.getDmcCode().equals(dmcFlossCode)) {
        dmcFlossNotAdded = false;
        break;
      }
    }

    if (dmcFlossNotAdded) {
      legendList.add(legendToAdd(dmcFlossToAdd));
    }

    Set<Legend> legendListTree = new TreeSet<Legend>(legendList);
    return legendListTree;
  }

  /**
   * Private helper method to return legend information of the given Dmc Floss.
   * 
   * @param dmcFlossToAdd Dmc Floss to add.
   * @return Legend
   */
  private Legend legendToAdd(DmcFloss dmcFlossToAdd) {
    Legend legendToAdd = new Legend(dmcFlossToAdd.getId(), dmcFlossToAdd.getDmcCode(),
        dmcFlossToAdd.getSymbol(), dmcFlossToAdd.getRedValue(), dmcFlossToAdd.getGreenValue(),
        dmcFlossToAdd.getBlueValue());
    return legendToAdd;
  }

  @Override
  public Pair<int[][][], List<Legend>> doAddNewColorsPattern(int[][][] rgbBuffer,
      List<String> selectedColors, List<Legend> legendList) {
    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");
    Objects.requireNonNull(selectedColors, "Colors to add cannot be null.");
    Objects.requireNonNull(legendList, "Legend list cannot be null.");

    int[][][] rgbBuffer_copy = ImageOperationsUtility.copyArray(rgbBuffer);

    int[][][] pattern_buffer = new int[rgbBuffer_copy.length]
        [rgbBuffer_copy[0].length][rgbBuffer_copy[0][0].length];

    Objects.requireNonNull(dmcDataSet);

    List<DmcFloss> dmcFlossSelectedColorsList = getDmcListFromSelectedColors(selectedColors);

    int height = rgbBuffer_copy.length;
    int width = rgbBuffer_copy[0].length;

    Set<DmcFloss> uniqueDmcFloss = new TreeSet<>();
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {

        int[] color = { rgbBuffer_copy[x][y][0], rgbBuffer_copy[x][y][1], rgbBuffer_copy[x][y][2] };
        int dmc_id = getClosetColorId(color, dmcFlossSelectedColorsList);

        pattern_buffer[x][y][0] = dmcFlossSelectedColorsList.get(dmc_id).getRedValue();
        pattern_buffer[x][y][1] = dmcFlossSelectedColorsList.get(dmc_id).getGreenValue();
        pattern_buffer[x][y][2] = dmcFlossSelectedColorsList.get(dmc_id).getBlueValue();
        uniqueDmcFloss.add(new DmcFloss(dmc_id, dmcFlossSelectedColorsList.get(dmc_id).getDmcCode(),
            dmcFlossSelectedColorsList.get(dmc_id).getRedValue(),
            dmcFlossSelectedColorsList.get(dmc_id).getGreenValue(),
            dmcFlossSelectedColorsList.get(dmc_id).getBlueValue(),
            dmcFlossSelectedColorsList.get(dmc_id).getSymbol()));
      }
    }
    Set<Legend> legendSet = generateLegendListNewColors(uniqueDmcFloss);
    List<Legend> updatedLegendList = new ArrayList<>(legendSet);

    return new Pair<int[][][], List<Legend>>(pattern_buffer, updatedLegendList);
  }

  /**
   * Private helper method to get dmc floss list from the given list of selected
   * colors.
   * 
   * @param selectedColors list of selected colors
   * @return list of DmcFloss
   */
  private List<DmcFloss> getDmcListFromSelectedColors(List<String> selectedColors) {
    List<DmcFloss> dmcFlossSelectedColorsList = new ArrayList<DmcFloss>();
    for (String dmcCode : selectedColors) {
      DmcFloss dmcFloss = getDmcFlossDetails(dmcCode);
      dmcFlossSelectedColorsList.add(dmcFloss);
    }
    return dmcFlossSelectedColorsList;
  }

  /**
   * Private helper method to generate the legend using new colors list.
   * 
   * @param uniqueDmcFloss set of unique Dmc Floss details
   * @return
   */
  private Set<Legend> generateLegendListNewColors(Set<DmcFloss> uniqueDmcFloss) {
    List<Legend> legendList = new ArrayList<Legend>();
    for (DmcFloss dmcFlossToAdd : uniqueDmcFloss) {
      legendList.add(legendToAdd(dmcFlossToAdd));
    }
    Set<Legend> legendListTree = new TreeSet<Legend>(legendList);
    return legendListTree;
  }

  @Override
  public List<SymbolCordinates> getCordinatesForSymbol(int[][][] rgbBuffer, List<Legend> legendList,
      int noOfSquaresAcross) {
    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");

    Objects.requireNonNull(legendList, "Legend list cannot be null.");

    int[][][] rgbBuffer_copy = ImageOperationsUtility.copyArray(rgbBuffer);

    List<SymbolCordinates> symbolsCordinatesList = new ArrayList<SymbolCordinates>();

    int image_height = rgbBuffer.length;
    int image_width = rgbBuffer[0].length;

    int square_length = (int) Math.round(image_width / noOfSquaresAcross);

    for (int y = 0; y < image_width; y += square_length) {
      for (int x = 0; x < image_height; x += square_length) {
        for (int yd = y; ((yd < y + square_length) && (yd < image_width)); yd++) {
          for (int xd = x; ((xd < x + square_length) && (xd < image_height)); xd++) {

            int centre_x = (square_length + x + x) / 2;
            int centre_y = (square_length + y + y) / 2;

            if (xd == centre_x && yd == centre_y) {

              int redColor = rgbBuffer_copy[xd][yd][0];
              int greenColor = rgbBuffer_copy[xd][yd][1];
              int blueColor = rgbBuffer_copy[xd][yd][2];
              char symbol = getSymbol(redColor, greenColor, blueColor, legendList);
              SymbolCordinates symbolData = new SymbolCordinates(symbol, centre_x, centre_y);
              symbolsCordinatesList.add(symbolData);
            }
          }
        }
      }
    }
    return symbolsCordinatesList;

  }

  /**
   * Private helper method to get symbol for the given color.
   * 
   * @param redColor   It is the red color.
   * @param greenColor It is the green color.
   * @param blueColor  It is the blue color.
   * @param legendList It is the list of Legend
   * @return
   */
  private char getSymbol(int redColor, int greenColor, int blueColor, List<Legend> legendList) {
    char symbol = 0;
    for (Legend legend : legendList) {
      if (legend.getRed() == redColor && legend.getGreen() == greenColor
          && legend.getBlue() == blueColor) {

        symbol = legend.getSymbol();
        break;
      }
    }
    return symbol;
  }

  @Override
  public String savePattern(int[][][] rgbBuffer, int noOfSquaresAcross, List<Legend> legendList) {

    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");
    Objects.requireNonNull(legendList, "Legend list cannot be null.");

    int[][][] rgbBuffer_copy = ImageOperationsUtility.copyArray(rgbBuffer);

    int image_height = rgbBuffer_copy.length;
    int image_width = rgbBuffer_copy[0].length;

    int square_length = image_width / noOfSquaresAcross;

    int pixelate_x = image_height / square_length;

    int m = pixelate_x;
    int n = noOfSquaresAcross;

    StringBuilder pattern = new StringBuilder();

    pattern.append(String.format("%d x %d\n\n", n, m));

    for (int x = 0; x < image_height; x += square_length) {
      for (int y = 0; y < image_width; y += square_length) {

        for (int yd = y; ((yd < y + square_length) && (yd < image_width)); yd++) {
          for (int xd = x; ((xd < x + square_length) && (xd < image_height)); xd++) {

            int centre_x = (square_length + x + x) / 2;
            int centre_y = (square_length + y + y) / 2;

            if (xd == centre_x && yd == centre_y) {

              int redColor = rgbBuffer_copy[xd][yd][0];
              int greenColor = rgbBuffer_copy[xd][yd][1];
              int blueColor = rgbBuffer_copy[xd][yd][2];
              char symbol = getSymbol(redColor, greenColor, blueColor, legendList);
              pattern.append(String.format("%c", symbol));
            }
          }
        }

      }
      pattern.append("\n");
    }
    pattern.append("\nLEGEND\n");

    for (Legend legend : legendList) {
      pattern.append(String.format("%c DMC-%s\n", legend.getSymbol(), legend.getDmcCode()));
    }
    return pattern.toString();
  }
}
