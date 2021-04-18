package images.imageview;

import java.util.Objects;
import javax.swing.ImageIcon;

/**
 * LegendTextIcon, represents the Legend representation.
 * 
 */
public class LegendTextIcon {
  private final String dmcFlossText;
  private final char symbol;
  private final ImageIcon dmcFlossImage;

  /**
   * Constructs a LegendTextIcon.
   * 
   * @param dmcFlossText  It is the DMC Code
   * @param symbol        It is the symbol representation of DMC Code
   * @param dmcFlossImage It is the color representing DMC code
   */
  public LegendTextIcon(String dmcFlossText, char symbol, ImageIcon dmcFlossImage) {
    Objects.requireNonNull(dmcFlossText);
    Objects.requireNonNull(dmcFlossImage);
    this.dmcFlossImage = dmcFlossImage;
    this.dmcFlossText = dmcFlossText;
    this.symbol = symbol;
  }

  /**
   * It returns the DMC Code.
   * 
   * @return the DMC Code
   */
  protected String getDmcFlossText() {
    return dmcFlossText;
  }

  /**
   * It return the symbol.
   * 
   * @return the symbol
   */
  protected char getSymbol() {
    return symbol;
  }

  /**
   * It returns the DMC Floss color Image icon.
   * 
   * @return the DMC Floss color Image icon
   */
  protected ImageIcon getDmcFlossImage() {
    return dmcFlossImage;
  }

}
