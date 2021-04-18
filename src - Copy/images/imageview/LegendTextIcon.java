package images.imageview;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class LegendTextIcon {
  private String dmcFlossText;
  private char symbol;
  private ImageIcon dmcFlossImage;

  public LegendTextIcon(String dmcFlossText, char symbol, ImageIcon dmcFlossImage) {
    this.dmcFlossImage = dmcFlossImage;
    this.dmcFlossText = dmcFlossText;
    this.symbol = symbol;
  }

  public String getDmcFlossText() {
    return dmcFlossText;
  }

  public void setDmcFlossText(String dmcFlossText) {
    this.dmcFlossText = dmcFlossText;
  }

  public char getSymbol() {
    return symbol;
  }

  public void setSymbol(char symbol) {
    this.symbol = symbol;
  }

  public ImageIcon getDmcFlossImage() {
    return dmcFlossImage;
  }

  public void setDmcFlossImage(ImageIcon dmcFlossImage) {
    this.dmcFlossImage = dmcFlossImage;
  }

}
