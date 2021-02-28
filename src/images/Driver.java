package images;

import java.io.File;
import java.io.IOException;

public class Driver {

 
  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub

    String filePath = "..\\ImageModel\\res\\Images\\Test.png";
    File f = new File(filePath);

    if (f.exists())
      System.out.println("Exists");
    else
      System.out.println("Does not Exists");

    int[][][] x = ImageUtilities.readImage(filePath);

    int height = ImageUtilities.getHeight(filePath);
    int width = ImageUtilities.getWidth(filePath);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          System.out.println(x[i][j][k]);
        }
      }
    }
  }

}
