package images.imagecontrol;

import java.io.IOException;

public interface Features {

  public void loadImage(String fileName) throws IOException;

  public void saveImage(String fileName) throws IOException;

  public void blur() throws IOException;

  public void sharpen() throws IOException;

  public void grayscale() throws IOException;

  public void sepia() throws IOException;

  public void dither(int noOfColorsToReduceTo) throws IOException;

  public void mosaic(int noOfseeds) throws IOException;

  public void pixelate(int noOfSquaresAcross) throws IOException;
  
  public void pattern() throws IOException;

}
