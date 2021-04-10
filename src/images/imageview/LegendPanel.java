package images.imageview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.text.IconView;

import images.imagemodel.Legend;

public class LegendPanel extends JPanel {

  private List<Legend> legendList;
  private static final long serialVersionUID = 3L;
  private DefaultListModel<Object> model;
  private JList<Object> list;
  private JLabel legendLabel;

  public LegendPanel() {
    this.legendList = new ArrayList<Legend>();
    this.model = new DefaultListModel<Object>();
    this.setLayout(new BorderLayout(10, 10));
    
    legendLabel = new JLabel();
    legendLabel.setHorizontalAlignment(JLabel.CENTER);
    legendLabel.setVerticalAlignment(JLabel.CENTER);
    
    this.add(legendLabel, BorderLayout.NORTH);
    
    list = new JList<Object>();
    this.add(list, BorderLayout.SOUTH);

    legendLabel = new JLabel();

    this.add(legendLabel);

  }

  public void setLegend(List<Legend> legendList) {
    this.legendList = legendList;
  }

  public void populate() {
    model.clear();
    legendLabel.setText("Legend");
    legendLabel.setFont(new Font("Serif", Font.BOLD, 18));
    
    for (Legend legend : legendList) {
      ImageIcon imageIcon = createImageIcon(
          new Color(legend.getRed(), legend.getGreen(), legend.getBlue()), 100, 60);
      model.addElement(new LegendTextIcon(String.format("DMC-%s", legend.getDmcCode()),
          legend.getSymbol(), imageIcon));
    }
    list.setCellRenderer(new Renderer());

    list.setModel(model);
  }
  
  public void clearLegend() {
    model.clear();
    legendLabel.setText("");
    list.setModel(model);
  }

  private static ImageIcon createImageIcon(Color color, int width, int height) {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();
    graphics.setPaint(color);
    graphics.fillRect(0, 0, width, height);
    return new ImageIcon(image);
  }
}
