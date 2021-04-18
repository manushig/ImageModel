package images.imagemodel;

public interface PatternSubject {
  public void registerPatternLegendObserver(PatternLegendObserver patternLegendObserver);

  public void removePatternLegendObserver(PatternLegendObserver patternLegendObserver);

  public void notifyPatternLegendObservers();
}
