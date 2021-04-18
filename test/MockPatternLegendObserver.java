import java.util.List;

import images.imagemodel.Legend;
import images.imagemodel.PatternLegendObserver;

/**
 * MockPatternLegendObserver, it is the class which mocks the
 * PatternLegendObserver to test the controller in isolation.
 * 
 */
public class MockPatternLegendObserver implements PatternLegendObserver {
  private StringBuffer log;
  private final int uniqueCode;

  /**
   * Constructs a MockPatternLegendObserver, specifying log and unique identifier
   * value.
   * 
   * @param log        It is the log
   * @param uniqueCode It is the unique code
   */
  public MockPatternLegendObserver(StringBuffer log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public String toString() {
    return String.format("%s", String.valueOf(this.uniqueCode));
  }

  @Override
  public void updatePatternLegend(List<Legend> pattern) {
    log.append(String.format("Function: updatePatternLegend and pattern: %s", pattern));

  }
}
