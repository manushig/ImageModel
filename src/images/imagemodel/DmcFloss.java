package images.imagemodel;

/**
 * DmcFloss, represents the DMC floss to RGB conversion values representation.
 * 
 */
public class DmcFloss {
  private int id;
  private String dmcCode;
  private int redValue;
  private int greenValue;
  private int blueValue;
  private char symbol;

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
  protected int getId() {
    return this.id;
  }

  /**
   * It returns the DMC code value.
   * 
   * @return the DMC code value
   */
  protected String getDmcCode() {
    return this.dmcCode;
  }

  /**
   * It returns the red value.
   * 
   * @return the red value
   */
  protected int getRedValue() {
    return this.redValue;
  }

  /**
   * It returns the green value.
   * 
   * @return the green value
   */
  protected int getGreenValue() {
    return this.greenValue;
  }

  /**
   * It returns the blue value.
   * 
   * @return the blue value
   */
  protected int getBlueValue() {
    return this.blueValue;
  }

  /**
   * It returns the unique unicode value.
   * 
   * @return the unique unicode value
   */
  protected char getSymbol() {
    return this.symbol;
  }

}
