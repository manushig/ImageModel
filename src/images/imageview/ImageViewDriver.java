package images.imageview;

import java.io.IOException;

import images.imagecontrol.UIController;
import images.imagemodel.ImageModel;
import images.imagemodel.ImageModelInterface;

public class ImageViewDriver {
  public static void main(String[] args) throws IOException {
    ImageModelInterface model = new ImageModel();

    UIController control = new UIController(model);

    ImageViewInterface view = new ImageView("Cross Stitch Application", model, control);
    
    control.setView(view);

  }
}
