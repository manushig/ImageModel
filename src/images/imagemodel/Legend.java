package images.imagemodel;

import java.util.Objects;

/**
 * Legend, represents the Legend values representation which are required at the
 * time of generating the cross-stitch pattern from the image.
 * 
 */
public class Legend implements Comparable<Legend> {
  private Integer dmcId;
  private String dmcCode;
  private char symbol;
  private int redValue;
  private int greenValue;
  private int blueValue;

  /**
   * Constructs a Legend, specifying details like dmcId,dmcValue and symbol.
   * 
   * @param dmcId   it is the identifier value
   * @param dmcCode it is the DMC code value
   * @param symbol  it is the unique unicode value
   */
  public Legend(int dmcId, String dmcCode, char symbol) {
    Objects.requireNonNull(dmcCode);
    this.dmcId = dmcId;
    this.dmcCode = dmcCode;
    this.symbol = symbol;
  }

  /**
   * Constructs a Legend, specifying details like dmcId,dmcValue symbol, red
   * color, green color and blue color.
   * 
   * @param dmcId   it is the identifier value
   * @param dmcCode it is the DMC code value
   * @param symbol  it is the unique unicode value
   */
  public Legend(int dmcId, String dmcCode, char symbol, int redValue, int greenValue,
      int blueValue) {
    this(dmcId, dmcCode, symbol);
    this.redValue = redValue;
    this.greenValue = greenValue;
    this.blueValue = blueValue;
  }

  /**
   * It returns the DMC code value.
   * 
   * @return the DMC code value.
   */
  public String getDmcCode() {
    return this.dmcCode;
  }

  /**
   * It returns the symbol.
   * 
   * @return the symbol.
   */
  public char getSymbol() {
    return this.symbol;
  }

  /**
   * It returns the DMC id value.
   * 
   * @return the DMC id value.
   */
  public Integer getDmcId() {
    return new Integer(this.dmcId);
  }

  @Override
  public boolean equals(Object o) {
    Objects.requireNonNull(o);
    // Fast path for pointer equality:
    if (this == o) { // backward compatibility with default equals
      return true;
    }

    if (!(o instanceof Legend)) {
      return false;
    }

    Legend that = (Legend) o;

    return (this.getDmcId() == that.getDmcId() && this.getDmcCode() == that.getDmcCode()
        && this.getSymbol() == that.getSymbol());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.dmcId, this.dmcCode, this.symbol);
  }

  @Override
  public int compareTo(Legend other) {
    Objects.requireNonNull(other);
    Legend that = (Legend) other;
    return this.getDmcId().compareTo(that.getDmcId());

  }

  /**
   * It returns the red color value.
   * 
   * @return the red color value.
   */
  public int getRed() {
    return this.redValue;
  }

  /**
   * It returns the green color value.
   * 
   * @return the red color value.
   */
  public int getGreen() {
    return this.greenValue;
  }

  /**
   * It returns the blue color value.
   * 
   * @return the red color value.
   */
  public int getBlue() {
    return this.blueValue;
  }
}
