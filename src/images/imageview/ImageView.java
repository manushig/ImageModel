package images.imageview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import images.imagecontrol.ImageControllerInterface;
import images.imagecontrol.UIController;
import images.imagemodel.ImageModelInterface;
import images.imagemodel.ImageObserver;
import images.imagemodel.Legend;
import images.imagemodel.PatternLegendObserver;
import images.imagecontrol.Features;

public class ImageView extends JFrame
    implements ImageViewInterface, ActionListener, ImageObserver, PatternLegendObserver {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private UIController controller;
  private ImageModelInterface model;

  private int inputValue;
  private JButton ditherButton;
  private JButton mosaicButton;
  private JButton pixelateButton;
  private JMenuBar menuBar;
  private JMenu imageOperationsMenu;
  private JMenu fileOperationsMenu;
  private JMenu patternOperationsMenu;
  private JMenu commandOperationsMenu;
  private JMenuItem loadImageMenu;
  private JMenuItem saveImageMenu;
  private JMenuItem blurImageMenu;
  private JMenuItem sharpenImageMenu;
  private JMenuItem grayscaleImageMenu;
  private JMenuItem sepiaImageMenu;
  private JMenuItem ditherImageMenu;
  private JMenuItem mosaicImageMenu;
  private JMenuItem pixelateImageMenu;
  private JMenuItem generatePatternImageMenu;
  private JMenuItem savePatternImageMenu;
  private JPanel imagePanel;
  private JPanel legendPanel;
  private String input;
  private JFileChooser imageOpenChooser;
  private JFileChooser imageSaveChooser;
  private BufferedImage imageToDisplay;
  private JLabel imageLabel;
  private JScrollPane imageScrollPane;
  private JScrollPane legendScrollPane;
  private JOptionPane optionPane;
  private Graphics2D g2d;
  private JLayeredPane imageLayeredPane;
  private JSplitPane splitPane;
  private LegendPanel lp;

  public ImageView(String caption, ImageModelInterface model, UIController controller)
      throws IOException {
    super(caption);

    model.registerImageObserver(this);
    model.registerPatternLegendObserver(this);
    this.setSize(1000, 1000); // Sets the x and y dimension of the frame
    this.setLocation(200, 200);
    this.setTitle("Cross Stitch Application");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout(10, 10));

    menuBar = new JMenuBar();

    imageOperationsMenu = new JMenu("Image");
    imageOperationsMenu.setMnemonic(KeyEvent.VK_I); // Alt+F for File Operations

    fileOperationsMenu = new JMenu("File");
    fileOperationsMenu.setMnemonic(KeyEvent.VK_F); // Alt+I for Image Operations

    commandOperationsMenu = new JMenu("Commands");
    commandOperationsMenu.setMnemonic(KeyEvent.VK_C); // Alt+C for Commands Operations

    loadImageMenu = new JMenuItem("Load");
    loadImageMenu.setMnemonic(KeyEvent.VK_L); // l for load

    saveImageMenu = new JMenuItem("Save Image");
    saveImageMenu.setMnemonic(KeyEvent.VK_S); // s for save image

    blurImageMenu = new JMenuItem("Blur");
    blurImageMenu.setMnemonic(KeyEvent.VK_B); // b for blur

    sharpenImageMenu = new JMenuItem("Sharpen");
    sharpenImageMenu.setMnemonic(KeyEvent.VK_R); // r for sharpen

    grayscaleImageMenu = new JMenuItem("Gray scale");
    grayscaleImageMenu.setMnemonic(KeyEvent.VK_G); // g for grayscale

    sepiaImageMenu = new JMenuItem("Sepia Tone");
    sepiaImageMenu.setMnemonic(KeyEvent.VK_T); // t for sepia Tone

    ditherImageMenu = new JMenuItem("Dither");
    ditherImageMenu.setMnemonic(KeyEvent.VK_D); // d for dither

    mosaicImageMenu = new JMenuItem("Mosaic");
    mosaicImageMenu.setMnemonic(KeyEvent.VK_M); // m for mosaic

    pixelateImageMenu = new JMenuItem("Pixelate");
    pixelateImageMenu.setMnemonic(KeyEvent.VK_P); // p for pixelate

    generatePatternImageMenu = new JMenuItem("Cross-Stitch Pattern");
    generatePatternImageMenu.setMnemonic(KeyEvent.VK_C); // c for generate cross stitch pattern

    fileOperationsMenu.add(loadImageMenu);
    fileOperationsMenu.add(saveImageMenu);
    imageOperationsMenu.add(blurImageMenu);
    imageOperationsMenu.add(sharpenImageMenu);
    imageOperationsMenu.add(grayscaleImageMenu);
    imageOperationsMenu.add(sepiaImageMenu);
    imageOperationsMenu.add(ditherImageMenu);
    imageOperationsMenu.add(mosaicImageMenu);
    imageOperationsMenu.add(pixelateImageMenu);
    imageOperationsMenu.add(generatePatternImageMenu);

    menuBar.add(fileOperationsMenu);
    menuBar.add(imageOperationsMenu);

    menuBar.add(commandOperationsMenu);
    this.setJMenuBar(menuBar);

    imagePanel = new JPanel();

    imageLabel = new JLabel();

    imageLabel.setHorizontalAlignment(JLabel.LEFT);
    imageLabel.setVerticalAlignment(JLabel.TOP);

    imageScrollPane = new JScrollPane(imageLabel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    imageScrollPane.setPreferredSize(new Dimension(750, 750));

    imagePanel.add(imageScrollPane);
    imagePanel.setPreferredSize(new Dimension(750, 750));

    legendPanel = new JPanel();
    legendPanel.setPreferredSize(new Dimension(250, 750));
    legendPanel.setBackground(Color.blue);

    lp = new LegendPanel();
    // lp.setPreferredSize(new Dimension(250,750));

    legendScrollPane = new JScrollPane(lp, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    legendScrollPane.setPreferredSize(new Dimension(250, 750));

    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, legendScrollPane);

    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(750);

    this.add(splitPane);
    imageOpenChooser = new JFileChooser();
    imageSaveChooser = new JFileChooser();

    ditherButton = new JButton();
    mosaicButton = new JButton();
    pixelateButton = new JButton();

    input = "";
    pack();
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == loadImageMenu) {

      imageOpenChooser.setCurrentDirectory(new java.io.File("."));
      imageOpenChooser.setSelectedFile(new File(""));
      imageOpenChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

      imageOpenChooser.showOpenDialog(this);
    }
    if (event.getSource() == saveImageMenu) {

      imageSaveChooser.setCurrentDirectory(new java.io.File("."));
      imageSaveChooser.setSelectedFile(new File(""));
      imageSaveChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

      imageSaveChooser.showSaveDialog(this);
    }
    if (event.getSource() == ditherImageMenu) {
      String value = JOptionPane
          .showInputDialog("What is the number of colors you want to reduce to?");
      try {
        if (!Objects.isNull(value)) {
          inputValue = Integer.parseInt(value);
          ditherButton.doClick();
        }

      } catch (NumberFormatException exception) {
        JOptionPane.showMessageDialog(ditherImageMenu, "Enter Only Numbers", "Exception",
            JOptionPane.ERROR_MESSAGE);
      }

    }

    if (event.getSource() == mosaicImageMenu) {
      String value = JOptionPane.showInputDialog("What is the number of seeds?");
      try {
        if (!Objects.isNull(value)) {
          inputValue = Integer.parseInt(value);
          mosaicButton.doClick();
        }

      } catch (NumberFormatException exception) {
        JOptionPane.showMessageDialog(mosaicImageMenu, "Enter Only Numbers", "Exception",
            JOptionPane.ERROR_MESSAGE);
      }

    }

    if (event.getSource() == pixelateImageMenu) {
      String value = JOptionPane.showInputDialog("What is the number of squares accross width?");
      try {
        if (!Objects.isNull(value)) {
          inputValue = Integer.parseInt(value);
          pixelateButton.doClick();
        }

      } catch (NumberFormatException exception) {
        JOptionPane.showMessageDialog(pixelateImageMenu, "Enter Only Numbers", "Exception",
            JOptionPane.ERROR_MESSAGE);
      }

    }

  }

  @Override
  public void setFeatures(Features feature) throws IOException {

    loadImageMenu.addActionListener(this);
    saveImageMenu.addActionListener(this);
    blurImageMenu.addActionListener(l -> {
      try {
        feature.blur();
      } catch (IOException e1) {
        JOptionPane.showMessageDialog(imageSaveChooser, e1.getMessage(), "Exception",
            JOptionPane.ERROR_MESSAGE);
      } catch (NullPointerException exception) {
        JOptionPane.showMessageDialog(imageSaveChooser, exception.getMessage(), "Exception",
            JOptionPane.ERROR_MESSAGE);
      }
    });
    sharpenImageMenu.addActionListener(l -> {
      try {
        feature.sharpen();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      } catch (NullPointerException exception) {
        JOptionPane.showMessageDialog(imageSaveChooser, exception.getMessage(), "Exception",
            JOptionPane.ERROR_MESSAGE);
      }
    });
    grayscaleImageMenu.addActionListener(l -> {
      try {
        feature.grayscale();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      } catch (NullPointerException exception) {
        JOptionPane.showMessageDialog(imageSaveChooser, exception.getMessage(), "Exception",
            JOptionPane.ERROR_MESSAGE);
      }
    });
    sepiaImageMenu.addActionListener(l -> {
      try {
        feature.sepia();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      } catch (NullPointerException exception) {
        JOptionPane.showMessageDialog(imageSaveChooser, exception.getMessage(), "Exception",
            JOptionPane.ERROR_MESSAGE);
      }
    });
    ditherImageMenu.addActionListener(this);
    mosaicImageMenu.addActionListener(this);
    pixelateImageMenu.addActionListener(this);

    generatePatternImageMenu.addActionListener(l -> {
      try {
        feature.pattern();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      } catch (NullPointerException exception) {
        JOptionPane.showMessageDialog(imageSaveChooser, exception.getMessage(), "Exception",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    imageOpenChooser.addActionListener(l -> {
      try {
        feature.loadImage(imageOpenChooser.getSelectedFile().getAbsolutePath());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    });

    imageSaveChooser.addActionListener(l -> {
      try {
        feature.saveImage(imageSaveChooser.getSelectedFile().getAbsolutePath());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        JOptionPane.showMessageDialog(imageSaveChooser, e.getMessage(), "Exception",
            JOptionPane.ERROR_MESSAGE);
      } catch (NullPointerException exception) {
        JOptionPane.showMessageDialog(imageSaveChooser, exception.getMessage(), "Exception",
            JOptionPane.ERROR_MESSAGE);
      }
    });
    ditherButton.addActionListener(l -> {
      try {
        feature.dither(inputValue);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
      } catch (NullPointerException exception) {
        JOptionPane.showMessageDialog(ditherButton, exception.getMessage(), "Exception",
            JOptionPane.ERROR_MESSAGE);
      }
    });
    mosaicButton.addActionListener(l -> {
      try {
        feature.mosaic(inputValue);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
      } catch (NullPointerException exception) {
        JOptionPane.showMessageDialog(mosaicButton, exception.getMessage(), "Exception",
            JOptionPane.ERROR_MESSAGE);
      }
    });
    pixelateButton.addActionListener(l -> {
      try {
        feature.pixelate(inputValue);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
      } catch (NullPointerException exception) {
        JOptionPane.showMessageDialog(pixelateButton, exception.getMessage(), "Exception",
            JOptionPane.ERROR_MESSAGE);
      }
    });
  }

  @Override
  public void updateImage(BufferedImage image) {
    imageToDisplay = image;

    imageLabel.setIcon(new ImageIcon(imageToDisplay));

    this.repaint();
  }

  @Override
  public void updatePatternLegend(List<Legend> pattern) {
    lp.setLegend(pattern);
    lp.populate();
    this.repaint();

  }

  @Override
  public void clearLegend() {
    lp.clearLegend();
    this.repaint();
  }

}
