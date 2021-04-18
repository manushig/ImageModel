package images.imagemodel;

import java.util.Objects;

public class SymbolCordinates implements Comparable<SymbolCordinates> {

  private int xCordinate;
  private int yCordinate;
  private char symbol;

  public SymbolCordinates(char symbol, int x, int y) {
    this.xCordinate = x;
    this.yCordinate = y;
    this.symbol = symbol;
  }

  public int getxCordinate() {
    return xCordinate;
  }

  public int getyCordinate() {
    return yCordinate;
  }

  public Character getSymbol() {
    return new Character(symbol);
  }

  @Override
  public boolean equals(Object o) {
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

    SymbolCordinates that = (SymbolCordinates) other;
    return this.getSymbol().compareTo(that.getSymbol());

  }

}
