package images.imageview;

import java.io.IOException;

import images.imagecontrol.Features;

public interface ImageViewInterface {
  void setFeatures(Features f) throws IOException;
  
  void clearLegend();
}
