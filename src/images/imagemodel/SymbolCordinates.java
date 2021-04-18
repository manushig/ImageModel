package images.imagemodel;

import java.util.Objects;

/**
 * SymbolCordinates, represents the coordinates where the symbol needs to be
 * displayed on the image.
 * 
 */
public class SymbolCordinates implements Comparable<SymbolCordinates> {

  private final int xCordinate;
  private final int yCordinate;
  private final char symbol;

  /**
   * COnstructs a SymbolCordinates.
   * 
   * @param symbol symbol to be displayed
   * @param x      x-coordinate where symbol needs to be displayed
   * @param y      y-coordinate where symbol needs to be displayed
   */
  public SymbolCordinates(char symbol, int x, int y) {
    this.xCordinate = x;
    this.yCordinate = y;
    this.symbol = symbol;
  }

  /**
   * It returns the x-coordinate where symbol needs to be displayed.
   * 
   * @return the x-coordinate where symbol needs to be displayed
   */
  public int getxCordinate() {
    return xCordinate;
  }

  /**
   * It returns the y-coordinate where symbol needs to be displayed.
   * 
   * @return the y-coordinate where symbol needs to be displayed
   */
  public int getyCordinate() {
    return yCordinate;
  }

  /**
   * It returns the symbol to be displayed.
   * 
   * @return the symbol to be displayed
   */
  public Character getSymbol() {
    return new Character(symbol);
  }

  @Override
  public boolean equals(Object o) {
    Objects.requireNonNull(o);
    // Fast path for pointer equality:
    if (this == o) { // backward compatibility with default equals
      return true;
    }

    if (!(o instanceof SymbolCordinates)) {
      return false;
    }

    SymbolCordinates that = (SymbolCordinates) o;

    return (this.getSymbol() == that.getSymbol() && this.getxCordinate() == that.getxCordinate()
        && this.getyCordinate() == that.getyCordinate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.symbol, this.xCordinate, this.yCordinate);
  }

  @Override
  public int compareTo(SymbolCordinates other) {
    Objects.requireNonNull(other);
    SymbolCordinates that = (SymbolCordinates) other;
    return this.getSymbol().compareTo(that.getSymbol());

  }

}
