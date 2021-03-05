package images.imagemodel;

/**
 * ImageObserver, is an interface that is refer by Subject for updating state.
 *
 */
public interface ImageObserver {
  /**
   * This method id used to update the state.
   */
  public void updateImage();
}
