package images.imagemodel;

/**
 * Represents the Pattern subject. Offers methods to register, de-register
 * Pattern Observers and add list of Legends.
 */
public interface PatternSubject {
  /**
   * This method allows objects to register as observer for state changes.
   * 
   * @param patternLegendObserver It is an objects which wants to register for
   *                              state changes.
   */
  public void registerPatternLegendObserver(PatternLegendObserver patternLegendObserver);

  /**
   * This method allows objects to be removed as observer.
   * 
   * @param patternLegendObserver It is an objects which wants to remove as
   *                              observer
   */
  public void removePatternLegendObserver(PatternLegendObserver patternLegendObserver);

  /**
   * This method notifies observers if there is any state change.
   */
  public void notifyPatternLegendObservers();
}
