package images;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Driver {

  private static int[][][] rgb_buffer;
  private static String filePath;
  private static String newFilePath;
  private static String grayScaleFilePath;
  private static String sepiaToneFilePath;
  private static String blurFilePath;

  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub

    filePath = "..\\ImageModel\\res\\Images\\Test.png";
    newFilePath = "..\\ImageModel\\res\\Images\\NewTest2.png";
    grayScaleFilePath = "..\\ImageModel\\res\\Images\\grayScale.png";
    sepiaToneFilePath = "..\\ImageModel\\res\\Images\\sepiaTone.png";
    blurFilePath = "..\\ImageModel\\res\\Images\\blur.png";
    File f = new File(filePath);

    if (f.exists())
      System.out.println("Exists");
    else
      System.out.println("Does not Exists");

    int[][][] x = ImageUtilities.readImage(filePath);

    int height = ImageUtilities.getHeight(filePath);
    int width = ImageUtilities.getWidth(filePath);

    // ImageUtilities.writeImage(x, width, height, newFilePath);

    // filterBlur(x, width, height);
    grayScale(x, width, height);
    sepiaTone(x, width, height);
    blur(x, width, height);

    // rgb_buffer = new int[height][width][3];

  }

  public static void grayScale(int[][][] rgb_value, int width, int height) throws IOException {

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = (int) Math.abs(rgb_value[i][j][0] * 0.2126);
        int g = (int) Math.abs(rgb_value[i][j][1] * 0.7152);
        int b = (int) Math.abs(rgb_value[i][j][2] * 0.0722);

        int sum = r + g + b;

        rgb_value[i][j][0] = sum;
        rgb_value[i][j][1] = sum;
        rgb_value[i][j][2] = sum;

        if (rgb_value[i][j][0] < 0) {
          rgb_value[i][j][0] = 0;
        }
        if (rgb_value[i][j][0] > 255) {
          rgb_value[i][j][0] = 255;
        }
        if (rgb_value[i][j][1] < 0) {
          rgb_value[i][j][1] = 0;
        }
        if (rgb_value[i][j][1] > 255) {
          rgb_value[i][j][1] = 255;
        }
        if (rgb_value[i][j][2] < 0) {
          rgb_value[i][j][2] = 0;
        }
        if (rgb_value[i][j][2] > 255) {
          rgb_value[i][j][2] = 255;
        }
      }
    }

    ImageUtilities.writeImage(rgb_value, width, height, grayScaleFilePath);
  }

  public static void sepiaTone(int[][][] rgb_value, int width, int height) throws IOException {

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        /*
         * rgb_value[i][j][0] = (int) Math.abs((rgb_value[i][j][0] * 0.393) +
         * (rgb_value[i][j][1] * 0.769) + (rgb_value[i][j][2] * 0.189));
         * rgb_value[i][j][1] = (int) Math.abs((rgb_value[i][j][0] * 0.349) +
         * (rgb_value[i][j][1] * 0.686) + (rgb_value[i][j][2] * 0.168));
         * rgb_value[i][j][2] = (int) Math.abs((rgb_value[i][j][0] * 0.272) +
         * (rgb_value[i][j][1] * 0.534) + (rgb_value[i][j][2] * 0.131));
         * 
         * 
         * 
         * //int sum = r + g + b;
         * 
         * //rgb_value[i][j][0] = sum; // rgb_value[i][j][1] = sum; //
         * rgb_value[i][j][2] = sum;
         * 
         * if (rgb_value[i][j][0] < 0) { rgb_value[i][j][0] = 0; } if
         * (rgb_value[i][j][0] > 255) { rgb_value[i][j][0] = 255; } if
         * (rgb_value[i][j][1] < 0) { rgb_value[i][j][1] = 0; } if (rgb_value[i][j][1] >
         * 255) { rgb_value[i][j][1] = 255; } if (rgb_value[i][j][2] < 0) {
         * rgb_value[i][j][2] = 0; } if (rgb_value[i][j][2] > 255) { rgb_value[i][j][2]
         * = 255; }
         */

        int r = rgb_value[i][j][0];
        int g = rgb_value[i][j][1];
        int b = rgb_value[i][j][2];

        float newr = (r * .393f) + (g * .769f) + (b * .189f);
        float newg = (r * .349f) + (g * .686f) + (b * .168f);
        float newb = (r * .272f) + (g * .534f) + (b * .131f);

        // round floats to integers
        int o_r = (int) (newr + 0.5f);
        int o_g = (int) (newg + 0.5f);
        int o_b = (int) (newb + 0.5f);

        rgb_value[i][j][0] = (o_r > 255) ? 255 : o_r;
        rgb_value[i][j][1] = (o_g > 255) ? 255 : o_g;
        rgb_value[i][j][2] = (o_b > 255) ? 255 : o_b;
      }
    }

    ImageUtilities.writeImage(rgb_value, width, height, sepiaToneFilePath);
  }

  public static void blur(int[][][] rgb_value, int width, int height) throws IOException {

    for (int i = 1; i < height-1; i++) {
      for (int j = 1; j < width -1 ; j++) {
        
        float r = (rgb_value[i-1][j-1][0] + rgb_value[i-1][j][0] + rgb_value[i-1][j+1][0])*1f/16 +
            (rgb_value[i][j-1][0] + rgb_value[i][j][0] + rgb_value[i][j+1][0])*1f/8 + 
            (rgb_value[i+1][j-1][0] + rgb_value[i+1][j][0] + rgb_value[i+1][j+1][0])*1f/16;
        
        float g = (rgb_value[i-1][j-1][1] + rgb_value[i-1][j][1] + rgb_value[i-1][j+1][1])*1f/8 +
            (rgb_value[i][j-1][1] + rgb_value[i][j][1] + rgb_value[i][j+1][1])*1f/4 + 
            (rgb_value[i+1][j-1][1] + rgb_value[i+1][j][1] + rgb_value[i+1][j+1][1])*1f/8;
        
        float b = (rgb_value[i-1][j-1][2] + rgb_value[i-1][j][2] + rgb_value[i-1][j+1][2])*1f/16 +
            (rgb_value[i][j-1][2] + rgb_value[i][j][2] + rgb_value[i][j+1][2])*1f/8 + 
            (rgb_value[i+1][j-1][2] + rgb_value[i+1][j][2] + rgb_value[i+1][j+1][2])*1f/16;
        
        
        int o_r = (int)(r);
        int o_g = (int)(g);
        int o_b = (int)(b);
        
       /* rgb_value[i][j][0] = (int) Math.abs((rgb_value[i][j][0] * 0.393) + (rgb_value[i][j][1] * 0.769)
            + (rgb_value[i][j][2] * 0.189));
        rgb_value[i][j][1] = (int) Math.abs((rgb_value[i][j][0] * 0.349) + (rgb_value[i][j][1] * 0.686)
            + (rgb_value[i][j][2] * 0.168));
        rgb_value[i][j][2] = (int) Math.abs((rgb_value[i][j][0] * 0.272) + (rgb_value[i][j][1] * 0.534)
            + (rgb_value[i][j][2] * 0.131));

       
        
        //int sum = r + g + b;

        //rgb_value[i][j][0] = sum;
       // rgb_value[i][j][1] = sum;
       // rgb_value[i][j][2] = sum;

        if (rgb_value[i][j][0] < 0) {
          rgb_value[i][j][0] = 0;
        }
        if (rgb_value[i][j][0] > 255) {
          rgb_value[i][j][0] = 255;
        }
        if (rgb_value[i][j][1] < 0) {
          rgb_value[i][j][1] = 0;
        }
        if (rgb_value[i][j][1] > 255) {
          rgb_value[i][j][1] = 255;
        }
        if (rgb_value[i][j][2] < 0) {
          rgb_value[i][j][2] = 0;
        }
        if (rgb_value[i][j][2] > 255) {
          rgb_value[i][j][2] = 255;
        }*/
        
        /*int r =rgb_value[i][j][0];
        int g = rgb_value[i][j][1];
        int b =rgb_value[i][j][2];
           */ 
       // float newr  = (r * (1f/16)) + (g *(1f / 8)) + (b * (1f / 16));
        //float newg  = (r * (1f / 8)) + (g *(1f / 4)) + (b * (1f / 8));
        //float newb  = (r * (1f/16)) + (g *(1f / 8)) + (b * (1f / 16));
        /*
        float newr  = (r * .0625f) + (g *.125f) + (b * .0625f);
        float newg  = (r * .125f) + (g *.25f) + (b * .125f);
        float newb  = (r * .0625f) + (g *.125f) + (b * .0625f);
        
        // round floats to integers
        int o_r = (int)(newr+0.5f);
        int o_g = (int)(newg+0.5f);
        int o_b = (int)(newb+0.5f);
        */
        rgb_value[i][j][0] = (o_r>255)?255:o_r;
        rgb_value[i][j][1] = (o_g>255)?255:o_g;
        rgb_value[i][j][2] = (o_b>255)?255:o_b;
      }
    }

    ImageUtilities.writeImage(rgb_value, width, height, blurFilePath);
  }

  public static void filterBlur(int[][][] rgb_value, int width, int height)
      throws FileNotFoundException, IOException {

    BufferedImage input = ImageIO.read(new FileInputStream(newFilePath));
    BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    float[] edge_mat = { 1f / 16, 1f / 8, 1f / 16, 1f / 8, 1f / 4, 1f / 8, 1f / 16, 1f / 8,
        1f / 16 };
    ConvolveOp edge_finder_op = new ConvolveOp(new Kernel(3, 3, edge_mat), ConvolveOp.EDGE_NO_OP,
        null);

    BufferedImage edge_img = edge_finder_op.filter(input, null);

    ImageIO.write(edge_img, "png", new FileOutputStream(newFilePath));
    // ImageUtilities.writeImage(, width, height, newFilePath);

  }

}
