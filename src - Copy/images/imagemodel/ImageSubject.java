package images.imagemodel;

public interface ImageSubject {
  /**
   * This method allows objects to register as observer for state changes.
   * 
   * @param imageObserver It is an objects which wants to register for state
   *                      changes.
   */
  public void registerImageObserver(ImageObserver imageObserver);

  /**
   * This method allows objects to be removed as observer.
   * 
   * @param imageObserver It is an objects which wants to remove as observer
   */
  public void removeImageObserver(ImageObserver imageObserver);

  /**
   * This method notifies observers if there is any state change.
   */
  public void notifyImageObservers();
}
