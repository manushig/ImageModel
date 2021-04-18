package images.imageview;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class Renderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public Component getListCellRendererComponent(JList<?> list, Object value, int index,
      boolean isSelected, boolean cellHasFocus) {
    LegendTextIcon legendIcon = (LegendTextIcon) value;
    setText(legendIcon.getDmcFlossText());
    setIcon(legendIcon.getDmcFlossImage());

    if (isSelected) {
      setBackground(list.getSelectionBackground());
      setForeground(list.getSelectionForeground());
    } else {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }
    setEnabled(true);
    setFont(list.getFont());

    return this;
  }

}
