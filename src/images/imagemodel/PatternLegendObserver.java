package images.imagemodel;

import java.util.List;

/**
 * PatternLegendObserver, is an interface that is refer by Subject for updating
 * state.
 *
 */

public interface PatternLegendObserver {
  /**
   * This method is used to update the state.
   */
  public void updatePatternLegend(List<Legend> pattern);
}
