package images.imagemodel;

import java.util.Objects;

/**
 * DmcFloss, represents the DMC floss to RGB conversion values representation.
 * 
 */
public class DmcFloss implements Comparable<DmcFloss> {
  private final Integer id;
  private final String dmcCode;
  private final int redValue;
  private final int greenValue;
  private final int blueValue;
  private final char symbol;

  /**
   * Constructs a DMCFloss, specifying details like id,dmcValue, redValue,
   * greenValue, blueValue and symbol.
   * 
   * @param id         it is the identifier value
   * @param dmcValue   it is the DMC code value
   * @param redValue   it is the red value
   * @param greenValue it is the green value
   * @param blueValue  it is the blue value
   * @param symbol     it is the unique unicode value
   */
  public DmcFloss(int id, String dmcValue, int redValue, int greenValue, int blueValue,
      char symbol) {
    Objects.requireNonNull(dmcValue);
    this.id = id;
    this.dmcCode = dmcValue;
    this.redValue = redValue;
    this.greenValue = greenValue;
    this.blueValue = blueValue;
    this.symbol = symbol;
  }

  /**
   * It returns the identifier value.
   * 
   * @return the identifier value.
   */
  protected Integer getId() {
    return new Integer(this.id);
  }

  /**
   * It returns the DMC code value.
   * 
   * @return the DMC code value
   */
  public String getDmcCode() {
    return this.dmcCode;
  }

  /**
   * It returns the red value.
   * 
   * @return the red value
   */
  public int getRedValue() {
    return this.redValue;
  }

  /**
   * It returns the green value.
   * 
   * @return the green value
   */
  public int getGreenValue() {
    return this.greenValue;
  }

  /**
   * It returns the blue value.
   * 
   * @return the blue value
   */
  public int getBlueValue() {
    return this.blueValue;
  }

  /**
   * It returns the unique unicode value.
   * 
   * @return the unique unicode value
   */
  public char getSymbol() {
    return this.symbol;
  }

  @Override
  public boolean equals(Object o) {
    Objects.requireNonNull(o);
    // Fast path for pointer equality:
    if (this == o) { // backward compatibility with default equals
      return true;
    }

    if (!(o instanceof DmcFloss)) {
      return false;
    }

    DmcFloss that = (DmcFloss) o;

    return (this.getId() == that.getId() && this.getDmcCode() == that.getDmcCode()
        && this.getRedValue() == that.getRedValue() && this.getGreenValue() == that.getGreenValue()
        && this.getBlueValue() == that.getBlueValue() && this.getSymbol() == that.getSymbol());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.dmcCode, this.redValue, this.greenValue, this.blueValue,
        this.symbol);
  }

  @Override
  public int compareTo(DmcFloss other) {
    Objects.requireNonNull(other);
    DmcFloss that = (DmcFloss) other;
    return this.getId().compareTo(that.getId());

  }

}
