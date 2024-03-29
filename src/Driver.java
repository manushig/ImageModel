import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

import images.imagecontrol.ImageController;
import images.imagecontrol.ImageControllerInterface;
import images.imagecontrol.InteractiveController;
import images.imagemodel.ImageModel;
import images.imagemodel.ImageModelInterface;
import images.imageview.ImageViewImp;
import images.imageview.ImageViewInterface;

/**
 * Driver program to demonstrates Cross Stitch Application.
 */
public class Driver {
  /**
   * Driver program that demonstrate how script and interactive operations can be
   * done on Cross Stitch Application.
   * 
   * @param args Arguments as command and batch file path.
   */
  public static void main(String[] args) {
    try {
      String command = args[0];

      ImageModelInterface model = new ImageModel();

      if (command.equals("-script")) {
        String directory = new File(".").getCanonicalPath();
        String batchfileName = args[1];
        String fileSeperator = FileSystems.getDefault().getSeparator();
        String batchFile = String.format("%s%s%s", directory, fileSeperator, batchfileName);

        StringBuffer out = new StringBuffer();

        Readable reader = new BufferedReader(new FileReader(batchFile));

        ImageControllerInterface batchcontroller = new ImageController(reader, out);

        System.out.println("Started\n");

        batchcontroller.start(model);

        System.out.println(out.toString());

        System.out.println("Done\n");
      }

      else if (command.equals("-interactive")) {
        InteractiveController interactiveController = new InteractiveController(model);

        ImageViewInterface view = new ImageViewImp("Cross Stitch Application",
            interactiveController);

        interactiveController.setView(view);

      } else {
        System.out.println("Invalid arguments\n");
      }
    } catch (IOException exception) {
      System.out.println("Issue occured. Please retry.");
    }
  }

}
