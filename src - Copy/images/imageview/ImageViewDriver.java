package images.imageview;

import java.io.IOException;
import java.util.List;

import images.imagecontrol.UIController;
import images.imagemodel.CrossStitchAlphabet;
import images.imagemodel.ImageModel;
import images.imagemodel.ImageModelInterface;
import images.imagemodel.ImageOperationsUtility;

public class ImageViewDriver {
  public static void main(String[] args) throws IOException {
    ImageModelInterface model = new ImageModel();

    UIController control = new UIController(model);

    ImageViewInterface view = new ImageViewImp("Cross Stitch Application", control);
    
    control.setView(view);

  }
 
}
