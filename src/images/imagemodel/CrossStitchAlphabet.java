package images.imagemodel;

/**
 * CrossStitchAlphabet, represents the Siena font representation of cross-stitch
 * alphabets.
 * 
 */
public class CrossStitchAlphabet {
  private final int id;
  private final char alphabet;
  private final int[][] alphabetPattern;
  private final int noOfSquaresOccupiedInXAxis;

  /**
   * Constructs a CrossStitchAlphabet.
   * 
   * @param id                         it is the identifier value
   * @param alphabet                   it is the unique unicode value
   * @param alphabetPattern            It is the 2D matrix of the alphabet pattern
   * @param noOfSquaresOccupiedInXAxis It is the number of squares by the alphabet
   *                                   across the width
   */
  public CrossStitchAlphabet(int id, char alphabet, int[][] alphabetPattern,
      int noOfSquaresOccupiedInXAxis) {
    this.id = id;
    this.alphabet = alphabet;
    this.alphabetPattern = alphabetPattern;
    this.noOfSquaresOccupiedInXAxis = noOfSquaresOccupiedInXAxis;
  }

  protected int getId() {
    return id;
  }

  protected char getAlphabet() {
    return alphabet;
  }

  protected int[][] getAlphabetPattern() {
    return ImageOperationsUtility.copyArray(alphabetPattern);
  }

  protected int getNoOfSquaresOccupiedInXAxis() {
    return noOfSquaresOccupiedInXAxis;
  }

}
