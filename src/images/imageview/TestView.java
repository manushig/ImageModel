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
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import images.imagecontrol.ImageControllerInterface;
import images.imagemodel.ImageModelInterface;
import images.imagemodel.ImageObserver;
import images.imagemodel.PatternLegendObserver;
import images.imagecontrol.Features;

public class TestView extends JFrame
    implements ImageViewInterface, ActionListener, ImageObserver, PatternLegendObserver {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private ImageControllerInterface controller;
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
  private JScrollPane scrollPane;
  private JOptionPane optionPane;

  public TestView(String caption, ImageModelInterface model) throws IOException {
    super(caption);

    model.registerImageObserver(this);
    model.registerPatternObserver(this);
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

    

    loadImageMenu = new JMenuItem("Load");
    loadImageMenu.setMnemonic(KeyEvent.VK_L); // l for load

    saveImageMenu = new JMenuItem("Save Image");
    saveImageMenu.setMnemonic(KeyEvent.VK_S); // s for save image

    blurImageMenu = new JMenuItem("Blur");
    blurImageMenu.setMnemonic(KeyEvent.VK_B); // b for blur

    
    fileOperationsMenu.add(loadImageMenu);
    fileOperationsMenu.add(saveImageMenu);
    imageOperationsMenu.add(blurImageMenu);

    menuBar.add(fileOperationsMenu);
    menuBar.add(imageOperationsMenu);

    this.setJMenuBar(menuBar);

    imagePanel = new JPanel() {
      private static final long serialVersionUID = 1L;

      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.drawImage(imageToDisplay, 0, 0, 750, 750, this);
        int x = 0;
        int y = 0;

        if (!Objects.isNull(imageToDisplay)) {
          // Graphics2D g2d = (Graphics2D) g;
          x = (this.getWidth() - imageToDisplay.getWidth(null)) / 2;
          y = (this.getHeight() - imageToDisplay.getHeight(null)) / 2;

          Graphics2D g2d = (Graphics2D) g;

          g2d.drawImage(imageToDisplay, x, y, this);

        }
      }

    };
    imagePanel.setPreferredSize(new Dimension(750, 750));

    scrollPane = new JScrollPane(imagePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setBounds(5, 112, 360, 200);
    // this.add(imagePanel, BorderLayout.CENTER);

    this.add(scrollPane, BorderLayout.CENTER);

    legendPanel = new JPanel();
    legendPanel.setPreferredSize(new Dimension(250, 750));
    legendPanel.setBackground(Color.blue);

    this.add(legendPanel, BorderLayout.EAST);
    imageOpenChooser = new JFileChooser();
    imageSaveChooser = new JFileChooser();
    // optionPane = new JOptionPane();

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
    imageOpenChooser.addActionListener(l -> {
      try {
        feature.loadImage(imageOpenChooser.getSelectedFile().getAbsolutePath());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    });

  }

  @Override
  public void updatePattern(String pattern) {

  }

  @Override
  public void updateImage(BufferedImage image) {
    imageToDisplay = image;
    this.repaint();
  }

}
