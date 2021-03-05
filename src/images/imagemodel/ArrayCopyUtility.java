package images.imagemodel;

/**
 * ArrayCopyUtility class that has method to create a clone 3D Array.
 */
public class ArrayCopyUtility {
  /**
   * This method create a clone of an given array.
   * 
   * @param source Array whose clone is required
   * @return Cloned array
   */
  public static int[][][] copyArray(int[][][] source) {
    int[][][] target = source.clone();

    for (int i = 0; i < source.length; i++) {
      target[i] = source[i].clone();

      for (int j = 0; j < source[i].length; j++) {
        target[i][j] = source[i][j].clone();
      }
    }

    return target;
  }
}
