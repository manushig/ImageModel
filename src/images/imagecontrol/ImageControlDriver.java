package images.imagecontrol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

import images.imagemodel.ImageModel;
import images.imagemodel.ImageModelInterface;

/**
 * Driver program to loads and saves images and demonstrates how all of
 * manipulation can be done on images.
 */
public class ImageControlDriver {
  /**
   * Driver program that loads and saves images and demonstrates how all of
   * manipulation can be done on images.
   * 
   * @param args Not used.
   */
  public static void main(String[] args) throws IOException {
    ImageModelInterface model = new ImageModel();
    String directory = new File(".").getCanonicalPath();
    String batchfileName = args[args.length - 1];
    String fileSeperator = FileSystems.getDefault().getSeparator();
    String batchFile = String.format("%s%s%s", directory, fileSeperator, batchfileName);

    StringBuffer out = new StringBuffer();

    Readable reader = new BufferedReader(new FileReader(batchFile));

    ImageControllerInterface control = new ImageController(reader, out);

    System.out.println("Started\n");
    
    control.start(model);

    System.out.println(out.toString());

    System.out.println("Done\n");
  }
}
