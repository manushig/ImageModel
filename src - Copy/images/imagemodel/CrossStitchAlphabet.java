package images.imagemodel;

public class CrossStitchAlphabet {
  private int id;
  private char alphabet;
  private int[][] alphabetPattern;
  private int noOfSquaresOccupiedInXAxis;

  public CrossStitchAlphabet(int id, char alphabet, int[][] alphabetPattern,
      int noOfSquaresOccupiedInXAxis) {
    this.id = id;
    this.alphabet = alphabet;
    this.alphabetPattern = alphabetPattern;
    this.noOfSquaresOccupiedInXAxis = noOfSquaresOccupiedInXAxis;
  }

  public int getId() {
    return id;
  }

  public char getAlphabet() {
    return alphabet;
  }

  public int[][] getAlphabetPattern() {
    return ImageOperationsUtility.copyArray(alphabetPattern);
  }

  public int getNoOfSquaresOccupiedInXAxis() {
    return noOfSquaresOccupiedInXAxis;
  }

}
