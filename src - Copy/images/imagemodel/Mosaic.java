package images.imagemodel;

import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Mosaic, implements an interface MosaicInterface that defines methods to
 * generate mosaic effect on the image.
 *
 */

public class Mosaic implements MosaicInterface {

  @Override
  public int[][][] doMosaic(int[][][] rgbBuffer, int noOfSeeds) {

    Objects.requireNonNull(rgbBuffer, "2 D RGB array value cannot be null.");

    int[][][] rgbBuffer_copy = ImageOperationsUtility.copyArray(rgbBuffer);

    int[][][] mosaic_rgb_buffer = new int[rgbBuffer_copy.length]
        [rgbBuffer_copy[0].length][rgbBuffer_copy[0][0].length];

    int height = rgbBuffer_copy.length;
    int width = rgbBuffer_copy[0].length;

    int[][] seedArray = seedArrayData(noOfSeeds, height, width);

    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {

        int[] closet_seed = getClosetSeed(seedArray, x, y);

        int seed_x = closet_seed[0];
        int seed_y = closet_seed[1];

        mosaic_rgb_buffer[x][y][0] = rgbBuffer_copy[seed_x][seed_y][0];
        mosaic_rgb_buffer[x][y][1] = rgbBuffer_copy[seed_x][seed_y][1];
        mosaic_rgb_buffer[x][y][2] = rgbBuffer_copy[seed_x][seed_y][2];
      }
    }

    return mosaic_rgb_buffer;
  }

  /**
   * Private helper method to generate random numbers within the given range.
   * 
   * @param min it is the minimum value of the range.
   * @param max it is the maximum value of the range.
   * @return the random number
   */
  private int getRandomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

  /**
   * Private helper method to generate the 2 D array of random seeds.
   * 
   * @param noOfSeeds it is the number of seeds to be generated randomly.
   * @param height    it is the height of the image.
   * @param width     it is the width of the image.
   * @return the 2 D array of random seeds
   */
  private int[][] seedArrayData(int noOfSeeds, int height, int width) {
    int[][] seedArray = new int[noOfSeeds][2];
    for (int i = 0; i < noOfSeeds; i++) {
      seedArray[i][0] = getRandomNumber(0, height - 1);
      seedArray[i][1] = getRandomNumber(0, width - 1);
    }
    return seedArray;
  }

  /**
   * Private helper method to calculate the value of D^2, D is the distance
   * between two points.
   * 
   * @param x1 It is the x value of Point 1.
   * @param y1 It is the y value of Point 1.
   * @param x2 It is the x value of Point 2.
   * @param y2 It is the y value of Point 2.
   * @return D^2, distance between two points.
   */
  private int calculateRelativeDistanceBetweenPoints(int x1, int y1, int x2, int y2) {
    return ((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
  }

  /**
   * Private helper method to get the closet seed value to the given point.
   * 
   * @param seedArray It the a 2 D array of randomly generated seeds.
   * @param x          It is the x value of the point.
   * @param y          It is the y value of the point.
   * @return closet seed to the given point.
   */
  private int[] getClosetSeed(int[][] seedArray, int x, int y) {
    Integer[] distanceArray = new Integer[seedArray.length];
    for (int i = 0; i < seedArray.length; i++) {

      distanceArray[i] = calculateRelativeDistanceBetweenPoints(x, y, seedArray[i][0],
          seedArray[i][1]);

    }

    int[] sortedIndices = IntStream.range(0, distanceArray.length).boxed()
        .sorted((i, j) -> distanceArray[i].compareTo(distanceArray[j])).mapToInt(ele -> ele)
        .toArray();

    int value = sortedIndices[0];

    int[] closet_seed = new int[2];
    closet_seed[0] = seedArray[value][0];
    closet_seed[1] = seedArray[value][1];

    return closet_seed;
  }

}
