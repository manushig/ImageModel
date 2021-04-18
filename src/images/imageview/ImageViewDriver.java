package images.imageview;

import java.io.IOException;
import images.imagecontrol.InteractiveController;
import images.imagemodel.ImageModel;
import images.imagemodel.ImageModelInterface;

/**
 * Driver program to demonstrates Cross Stitch Application.
 */
public class ImageViewDriver {
  /**
   * Driver program that demonstrate how script and interactive operations can be
   * done on Cross Stitch Application.
   * 
   * @param args Not used
   */
  public static void main(String[] args) throws IOException {
    ImageModelInterface model = new ImageModel();

    InteractiveController control = new InteractiveController(model);

    ImageViewInterface view = new ImageViewImp("Cross Stitch Application", control);

    control.setView(view);

  }

}
