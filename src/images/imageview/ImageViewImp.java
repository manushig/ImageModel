package images.imageview;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import images.imagecontrol.InteractiveController;
import images.imagemodel.DmcFloss;
import images.imagemodel.Legend;
import images.imagemodel.SymbolCordinates;
import images.imagecontrol.Features;

/**
 * ImageViewImp, a JFrame to take user inputs and perform image manipulations on
 * the image.
 *
 */
public class ImageViewImp extends JFrame implements ImageViewInterface {

  /**
   * It is the generated serial version id.
   */
  private static final long serialVersionUID = 6710309563254238911L;
  private int inputValue;
  private JButton ditherButton;
  private JButton mosaicButton;
  private JButton pixelateButton;
  private JButton commandButton;
  private JButton patternColorReplaceButton;
  private JButton patternColorRemoveButton;
  private JButton patternNewColorButton;
  private JButton addTextButton;
  private JButton selectNewColorPatternButton;
  private JMenuItem savePatternTextMenuItem;
  private JMenuItem exchangeColorPatternMenuItem;
  private JMenuItem removeColorPatternMenuItem;
  private JMenuItem displaySymbolsPatternMenuItem;
  private JMenuItem addTextPatternMenuItem;
  private JMenuItem selectNewColorPatternMenuItem;

  private JMenu imageOperationsMenu;
  private JMenu fileOperationsMenu;
  private JMenuItem batchCommandMenu;
  private JMenu commandOperationsMenu;
  private JMenu patternOperationsMenu;
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

  private JButton imageOpenChooser;
  private JButton imageSaveChooser;
  private JButton batchFileOpenChooser;
  private JButton patternSaveOpenChooser;
  private BufferedImage imageToDisplay;
  private JLabel imageLabel;

  private String commandText;
  private String addTextToPattern;
  private boolean mouseListenerIsActive;
  private List<Legend> patternLegendList;
  private List<DmcFloss> dmcFlossList;
  private int xCordinateSelected;
  private int yCordinateSelected;
  private String dmcCodeSelected;

  private JMenuItem imageOperationsMenuItem;
  private JMenuItem commandOperationsMenuItem;
  private JMenuItem exitOperationsMenuItem;
  private JMenuItem loadCommandFileMenuItem;
  private Boolean imageOperationsPerformed;

  private DefaultListModel<Object> legendModel;
  private DefaultListModel<Object> dmcModel;
  private JList<Object> dmcList;
  private JList<Object> dmcColorList;
  private JLabel legendLabelText;
  private JFrame colorSelectFrame;
  private JFrame addTextFrame;
  private JLabel progressBarText;

  private List<String> selectedNewColors;
  private String fileName;

  /**
   * Constructs a ImageViewImp.
   * 
   * @param caption    It is the title to be displayed.
   * @param controller It is the controller object
   * @throws IOException if I/O operations failed.
   */
  public ImageViewImp(String caption, InteractiveController controller) throws IOException {
    super(caption);
    Objects.requireNonNull(caption);
    Objects.requireNonNull(controller);
    this.setSize(1000, 1000); // Sets the x and y dimension of the frame
    this.setLocation(100, 100);
    this.setTitle(caption);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout(10, 10));

    setButtons();
    setMenu();
    setPanel();

    imageOperationsPerformed = false;
    mouseListenerIsActive = false;

    this.patternLegendList = new ArrayList<Legend>();
    this.dmcFlossList = new ArrayList<DmcFloss>();

    this.legendModel = new DefaultListModel<Object>();
    this.dmcModel = new DefaultListModel<Object>();

    setPatternOperationsVisibility(false);

    addTextToPattern = "";
    fileName = "";
    pack();
    this.setVisible(true);
  }

  private void setMenu() {
    JMenuBar menuBar = new JMenuBar();

    JMenu operationsMenu = new JMenu("Operations");
    operationsMenu.setMnemonic(KeyEvent.VK_O);

    imageOperationsMenuItem = new JMenuItem("Image Operations");
    imageOperationsMenuItem.setMnemonic(KeyEvent.VK_1);

    commandOperationsMenuItem = new JMenuItem("Command Operations");
    commandOperationsMenuItem.setMnemonic(KeyEvent.VK_2);

    exitOperationsMenuItem = new JMenuItem("Exit");
    exitOperationsMenuItem.setMnemonic(KeyEvent.VK_E);

    operationsMenu.add(imageOperationsMenuItem);
    operationsMenu.add(commandOperationsMenuItem);
    operationsMenu.add(exitOperationsMenuItem);

    imageOperationsMenu = new JMenu("Image Operations");
    imageOperationsMenu.setMnemonic(KeyEvent.VK_I); // Alt+F for File Operations

    fileOperationsMenu = new JMenu("File");
    fileOperationsMenu.setMnemonic(KeyEvent.VK_F); // Alt+I for Image Operations

    commandOperationsMenu = new JMenu("Batch Command Operations");
    commandOperationsMenu.setMnemonic(KeyEvent.VK_C); // Alt+C for Commands Operations

    patternOperationsMenu = new JMenu("Pattern Operations");
    patternOperationsMenu.setMnemonic(KeyEvent.VK_3);

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
    generatePatternImageMenu.setMnemonic(KeyEvent.VK_X); // c for generate cross stitch pattern

    batchCommandMenu = new JMenuItem("Batch-Commands");
    batchCommandMenu.setMnemonic(KeyEvent.VK_A);

    loadCommandFileMenuItem = new JMenuItem("Upload Batch-Commands File");
    loadCommandFileMenuItem.setMnemonic(KeyEvent.VK_U);

    savePatternTextMenuItem = new JMenuItem("Save Pattern in Text Format");
    savePatternTextMenuItem.setMnemonic(KeyEvent.VK_V);

    exchangeColorPatternMenuItem = new JMenuItem("Exchange Color from Pattern");
    exchangeColorPatternMenuItem.setMnemonic(KeyEvent.VK_H);

    removeColorPatternMenuItem = new JMenuItem("Remove Color from Pattern");
    removeColorPatternMenuItem.setMnemonic(KeyEvent.VK_J);

    displaySymbolsPatternMenuItem = new JMenuItem("Display Symbols to Pattern");
    displaySymbolsPatternMenuItem.setMnemonic(KeyEvent.VK_Y);

    addTextPatternMenuItem = new JMenuItem("Add Text to Pattern");
    addTextPatternMenuItem.setMnemonic(KeyEvent.VK_K);

    selectNewColorPatternMenuItem = new JMenuItem("Add New Colors");
    selectNewColorPatternMenuItem.setMnemonic(KeyEvent.VK_N);

    fileOperationsMenu.add(loadImageMenu);
    fileOperationsMenu.add(saveImageMenu);
    fileOperationsMenu.setEnabled(false);

    imageOperationsMenu.add(blurImageMenu);
    imageOperationsMenu.add(sharpenImageMenu);
    imageOperationsMenu.add(grayscaleImageMenu);
    imageOperationsMenu.add(sepiaImageMenu);
    imageOperationsMenu.add(ditherImageMenu);
    imageOperationsMenu.add(mosaicImageMenu);
    imageOperationsMenu.add(pixelateImageMenu);
    imageOperationsMenu.add(generatePatternImageMenu);
    imageOperationsMenu.setEnabled(false);

    commandOperationsMenu.add(batchCommandMenu);
    commandOperationsMenu.add(loadCommandFileMenuItem);
    commandOperationsMenu.setEnabled(false);

    patternOperationsMenu.add(savePatternTextMenuItem);
    patternOperationsMenu.add(exchangeColorPatternMenuItem);
    patternOperationsMenu.add(removeColorPatternMenuItem);
    patternOperationsMenu.add(displaySymbolsPatternMenuItem);
    patternOperationsMenu.add(addTextPatternMenuItem);
    patternOperationsMenu.add(selectNewColorPatternMenuItem);
    patternOperationsMenu.setEnabled(false);

    menuBar.add(operationsMenu);
    menuBar.add(fileOperationsMenu);
    menuBar.add(imageOperationsMenu);
    menuBar.add(patternOperationsMenu);
    menuBar.add(commandOperationsMenu);
    this.setJMenuBar(menuBar);
  }

  private void setPanel() {
    // Image Panel
    JPanel imagePanel = new JPanel();

    imageLabel = new JLabel();

    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);
    imageLabel
        .setText("Either Select Image or Command Operations to proceed from Operations menu.");

    JScrollPane imageScrollPane = new JScrollPane(imageLabel,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    imageScrollPane.setPreferredSize(new Dimension(710, 710));

    imagePanel.add(imageScrollPane);
    imagePanel.setPreferredSize(new Dimension(750, 750));

    progressBarText = new JLabel();
    progressBarText
        .setText("Either Select Image or Command Operations to proceed from Operations menu.");

    progressBarText.setHorizontalAlignment(JLabel.RIGHT);
    progressBarText.setVerticalAlignment(JLabel.TOP);

    imagePanel.add(progressBarText);

    // Legend Panel
    JPanel legendPanel = new JPanel();

    legendPanel.setLayout(new BorderLayout(10, 10));

    legendLabelText = new JLabel();
    legendLabelText.setHorizontalAlignment(JLabel.CENTER);
    legendLabelText.setVerticalAlignment(JLabel.CENTER);

    legendPanel.add(legendLabelText, BorderLayout.NORTH);

    dmcList = new JList<Object>();
    dmcColorList = new JList<Object>();

    JScrollPane legendScrollPane = new JScrollPane(dmcList,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    legendScrollPane.setPreferredSize(new Dimension(250, 400));

    legendPanel.add(legendScrollPane, BorderLayout.CENTER);

    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, legendPanel);

    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(750);

    this.add(splitPane);
  }

  private void setButtons() {
    ditherButton = new JButton();
    mosaicButton = new JButton();
    pixelateButton = new JButton();
    commandButton = new JButton();
    patternColorReplaceButton = new JButton();
    patternColorRemoveButton = new JButton();
    patternNewColorButton = new JButton();
    addTextButton = new JButton();
    selectNewColorPatternButton = new JButton();
    imageOpenChooser = new JButton();
    imageSaveChooser = new JButton();
    batchFileOpenChooser = new JButton();
    patternSaveOpenChooser = new JButton();
  }

  private void setPatternOperationsVisibility(boolean visibilityFlag) {
    patternOperationsMenu.setEnabled(visibilityFlag);
  }

  @Override
  public void getFileNameForLoad() {

    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "png", "gif",
        "bmp");
    fileChooser(imageOpenChooser, filter, "load");
  }

  @Override
  public void getFileNameForSave() {
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "png", "gif",
        "bmp");
    fileChooser(imageSaveChooser, filter, "save");
  }

  @Override
  public void getDitherUserInput() {
    getUserInput("What is the number of colors you want to reduce to?", ditherButton,
        ditherImageMenu);

  }

  @Override
  public void getMosaicUserInput() {
    getUserInput("What is the number of seeds?", mosaicButton, mosaicImageMenu);
  }

  @Override
  public void getPixelateInput() {
    getUserInput("What is the number of squares accross width?", pixelateButton, pixelateImageMenu);
  }

  /**
   * Private helper method to display JOptionPane and get user input.
   * 
   * @param message          Message to display
   * @param buttonToClick    button be clicked.
   * @param callingComponent Component
   */
  private void getUserInput(String message, JButton buttonToClick, Component callingComponent) {
    String value = JOptionPane.showInputDialog(message);
    try {
      if (!Objects.isNull(value) && !value.isEmpty()) {
        inputValue = Integer.parseInt(value);
        buttonToClick.doClick();
      }

    } catch (NumberFormatException exception) {
      JOptionPane.showMessageDialog(callingComponent, "Enter Only Numbers", "Exception",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void geBatchCommandInput() {
    CommandFrame commandFrame = new CommandFrame(this);
    commandFrame.setVisible(true);
  }

  @Override
  public void getBatchCommandLoadInput() {
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "doc",
        "docx");
    fileChooser(batchFileOpenChooser, filter, "load");

  }

  /**
   * Private helper method to display fileChooser and get user input.
   * 
   * @param fileChooser It is the component of JButton
   * @param filter      It is the filter for file extensions.
   * @param operation   It is verify which type of Dialog box to show to the user.
   */
  private void fileChooser(JButton fileChooserButton, FileNameExtensionFilter filter,
      String operation) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new java.io.File("."));
    fileChooser.setSelectedFile(new File(""));
    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    fileChooser.setFileFilter(filter);
    int result = 0;
    if (operation.equals("save")) {
      result = fileChooser.showSaveDialog(this);
    } else if (operation.equals("load")) {
      result = fileChooser.showOpenDialog(this);
    }
    if (Objects.isNull(result) || result == 1) {
      this.fileName = "";
    } else {
      this.fileName = fileChooser.getSelectedFile().getAbsolutePath();
    }
    fileChooserButton.doClick();
  }

  @Override
  public void getSavePatternInput() {
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "doc",
        "docx");
    fileChooser(patternSaveOpenChooser, filter, "save");
  }

  @Override
  public void getNewColorInput() {
    Object[] legendTextIconList = dmcColorList.getSelectedValues();
    this.selectedNewColors = new ArrayList<String>();
    for (Object legendObj : legendTextIconList) {
      LegendTextIcon legendTextIcon = (LegendTextIcon) legendObj;
      selectedNewColors.add(legendTextIcon.getDmcFlossText());
    }
    colorSelectFrame.dispose();
    patternNewColorButton.doClick();
  }

  @Override
  public void setFeatures(Features feature) throws IOException {
    Objects.requireNonNull(feature);

    imageOperationsMenuItem.addActionListener(l -> feature.imageOperations());

    commandOperationsMenuItem.addActionListener(l -> feature.commandOperations());

    exitOperationsMenuItem.addActionListener(l -> feature.programExit());

    loadImageMenu.addActionListener(l -> feature.loadClicked());

    saveImageMenu.addActionListener(l -> feature.saveImageClicked());

    blurImageMenu.addActionListener(l -> feature.blur());

    sharpenImageMenu.addActionListener(l -> feature.sharpen());

    grayscaleImageMenu.addActionListener(l -> feature.grayscale());

    sepiaImageMenu.addActionListener(l -> feature.sepia());

    ditherImageMenu.addActionListener(l -> feature.ditherClicked());

    mosaicImageMenu.addActionListener(l -> feature.mosaicClicked());

    pixelateImageMenu.addActionListener(l -> feature.pixelateClicked());

    generatePatternImageMenu.addActionListener(l -> feature.pattern());

    imageOpenChooser.addActionListener(l -> feature.loadImage(this.fileName));

    imageSaveChooser.addActionListener(l -> feature.saveImage(this.fileName));

    ditherButton.addActionListener(l -> feature.dither(inputValue));

    mosaicButton.addActionListener(l -> feature.mosaic(inputValue));

    pixelateButton.addActionListener(l -> feature.pixelate(inputValue));

    batchCommandMenu.addActionListener(l -> feature.batchCommandClicked());

    loadCommandFileMenuItem.addActionListener(l -> feature.batchCommandLoadClicked());

    commandButton.addActionListener(l -> feature.executeBatchcommand(this.commandText));

    batchFileOpenChooser.addActionListener(l -> feature.executeBatchFile(this.fileName));

    patternSaveOpenChooser.addActionListener(l -> feature.savePattern(this.fileName));

    exchangeColorPatternMenuItem
        .addActionListener(l -> feature.exchangeColorPatternButtonClicked());

    removeColorPatternMenuItem.addActionListener(l -> feature.removeColorPatternButtonClicked());

    patternColorRemoveButton.addActionListener(l -> feature.patternRemoveColor(dmcCodeSelected));

    patternColorReplaceButton.addActionListener(
        l -> feature.patternReplaceColor(xCordinateSelected, yCordinateSelected, dmcCodeSelected));

    addTextPatternMenuItem.addActionListener(l -> feature.addTextPatternButtonClicked());

    // Fix color ---------------------------- dmccode
    addTextButton.addActionListener(l -> feature.patternAddText(addTextToPattern, dmcCodeSelected));

    selectNewColorPatternMenuItem.addActionListener(l -> feature.newColorPatternClicked());

    selectNewColorPatternButton.addActionListener(l -> feature.newColorClicked());

    patternNewColorButton.addActionListener(l -> feature.addNewColorsPattern(selectedNewColors));

    displaySymbolsPatternMenuItem.addActionListener(l -> feature.displaySymbolsClicked());

    savePatternTextMenuItem.addActionListener(l -> feature.savePatternClicked());
  }

  @Override
  public void updateImage(BufferedImage image) {
    Objects.requireNonNull(image);
    imageToDisplay = image;

    imageLabel.setIcon(new ImageIcon(imageToDisplay));
    imageLabel.setText("");
    imageLabel.setHorizontalAlignment(JLabel.LEFT);
    imageLabel.setVerticalAlignment(JLabel.TOP);

    this.repaint();
  }

  @Override
  public void updatePatternLegend(List<Legend> patternLegendList) {
    Objects.requireNonNull(patternLegendList);
    this.patternLegendList = patternLegendList;
    populateLegend();
    setPatternOperationsVisibility(true);
    this.repaint();

  }

  @Override
  public void clearLegend() {
    Objects.requireNonNull(legendModel);
    legendModel.clear();
    legendLabelText.setText("");

    dmcList.setModel(legendModel);

    setPatternOperationsVisibility(false);

    this.repaint();
  }

  @Override
  public void setCommandText(String text) {
    Objects.requireNonNull(text);
    if (!text.isEmpty()) {
      this.commandText = text;
      commandButton.doClick();
    } else {
      JOptionPane.showMessageDialog(commandButton, "No commands given.", "Information",
          JOptionPane.INFORMATION_MESSAGE);
    }
  }

  @Override
  public void disableImagePanelOnBatchCommands(String message) {
    Objects.requireNonNull(message);
    imageOperationsPerformed = false;
    fileOperationsMenu.setEnabled(false);
    imageOperationsMenu.setEnabled(false);
    commandOperationsMenu.setEnabled(true);
    imageLabel.setText(message);

    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);
    imageLabel.setIcon(null);

    setProgressBarText(message);
    clearLegend();
  }

  @Override
  public void addMouseListenerOnImagePanel() {
    mouseListenerIsActive = true;
    imageLabel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent event) {
        if (mouseListenerIsActive) {
          int x = event.getX();
          int y = event.getY();
          if (x <= imageToDisplay.getWidth() && y <= imageToDisplay.getHeight()) {

            String[] colorOptions = populateLegendList();
            String selectedColor = displaycolorOptions("Exchange Color", colorOptions);

            if (!Objects.isNull(selectedColor)) {
              dmcCodeSelected = selectedColor;
              xCordinateSelected = x;
              yCordinateSelected = y;
              patternColorReplaceButton.doClick();
            } else {
              mouseListenerIsActive = false;
            }

          } else {
            JOptionPane.showMessageDialog(null, "Please click on the image.", "Information",
                JOptionPane.INFORMATION_MESSAGE);
          }
        }
      }
    });

  }

  /**
   * Private helper method to show the JOptionPane to get color code.
   * 
   * @param title        Title to be displayed
   * @param colorOptions List of DMC Codes
   * @return the selected color
   */
  private String displaycolorOptions(String title, String[] colorOptions) {

    String selectedColor = (String) JOptionPane.showInputDialog(null, "Select DMC Color", title,
        JOptionPane.QUESTION_MESSAGE, null, colorOptions, colorOptions[0]);

    return selectedColor;
  }

  @Override
  public void disableMouseListenerOnImagePanel() {
    mouseListenerIsActive = false;
  }

  /**
   * Populate legend list for removing the color on the pattern and avoiding Blank
   * option.
   */
  private String[] populateLegendList() {
    int size = patternLegendList.size();
    String[] legendDmcCode = new String[size];
    int j = 0;
    for (int i = 0; i < size; i++) {
      if (!patternLegendList.get(i).getDmcCode().equals("Blank")) {
        String code = String.format("%s", patternLegendList.get(i).getDmcCode());
        legendDmcCode[j] = code;
        j++;
      }
    }

    return legendDmcCode;
  }

  /**
   * Private helper method to populate DMC Floss list on the legend Panel.
   */
  private void populateLegend() {
    legendModel.clear();
    legendLabelText.setText("Legend");
    legendLabelText.setFont(new Font("Serif", Font.BOLD, 18));

    for (Legend legend : patternLegendList) {
      ImageIcon imageIcon = createImageIcon(
          new Color(legend.getRed(), legend.getGreen(), legend.getBlue()), 100, 60);
      legendModel.addElement(
          new LegendTextIcon(String.format("%c  DMC-%s", legend.getSymbol(), legend.getDmcCode()),
              legend.getSymbol(), imageIcon));
    }
    dmcList.setCellRenderer(new Renderer());

    dmcList.setModel(legendModel);
  }

  /**
   * Private helper method to populate DMC Floss list on the dialog box.
   */
  private void populateDmcFlossList() {
    dmcModel.clear();

    for (DmcFloss dmcFloss : dmcFlossList) {
      ImageIcon imageIcon = createImageIcon(
          new Color(dmcFloss.getRedValue(), dmcFloss.getGreenValue(), dmcFloss.getBlueValue()), 100,
          60);
      dmcModel.addElement(new LegendTextIcon(String.format("%s", dmcFloss.getDmcCode()),
          dmcFloss.getSymbol(), imageIcon));
    }
    dmcColorList.setCellRenderer(new Renderer());

    dmcColorList.setModel(dmcModel);
  }

  /**
   * Private helper method to create the image icon to display the DMC Floss
   * code's color.
   * 
   * @param color  It is the color to display
   * @param width  It is the width of the image
   * @param height It is the height of the image
   * @return ImageIcon object
   */
  private ImageIcon createImageIcon(Color color, int width, int height) {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();
    graphics.setPaint(color);
    graphics.fillRect(0, 0, width, height);
    return new ImageIcon(image);
  }

  @Override
  public void enableMenuItemsOnImageOperations() {
    int userSelectionValue = -1;
    if (imageOperationsPerformed) {
      userSelectionValue = JOptionPane.showConfirmDialog(imageOperationsMenuItem,
          "Any unsaved work may lost. Do you want to proceed?", "Warning",
          JOptionPane.YES_NO_OPTION);
    } else {
      userSelectionValue = 0;
    }
    if (userSelectionValue == 0) {
      imageOperationsPerformed = false;
      fileOperationsMenu.setEnabled(true);
      imageOperationsMenu.setEnabled(false);
      commandOperationsMenu.setEnabled(false);
      imageLabel.setText("Load Image file to proceed.");
      imageLabel.setHorizontalAlignment(JLabel.CENTER);
      imageLabel.setVerticalAlignment(JLabel.CENTER);
      imageLabel.setIcon(null);

      progressBarText.setText("Load Image file to proceed.");
      clearLegend();
    }
  }

  @Override
  public void enableImageOperation() {
    imageOperationsMenu.setEnabled(true);
  }

  @Override
  public void enableMenuItemsOnCommandOperations() {
    int userSelectionValue = -1;
    if (imageOperationsPerformed) {
      userSelectionValue = JOptionPane.showConfirmDialog(commandOperationsMenuItem,
          "Any unsaved work may lost. Do you want to proceed?", "Warning",
          JOptionPane.YES_NO_OPTION);
    } else {
      userSelectionValue = 0;
    }
    if (userSelectionValue == 0) {
      disableImagePanelOnBatchCommands("Load Batch file or enter batch commands to proceed.");
    }
  }

  @Override
  public void programExitOperations() {
    int userSelectionValue = -1;
    if (imageOperationsPerformed) {
      userSelectionValue = JOptionPane.showConfirmDialog(commandOperationsMenuItem,
          "Any unsaved work may lost. Do you want to proceed?", "Warning",
          JOptionPane.YES_NO_OPTION);
    } else {
      userSelectionValue = 0;
    }
    if (userSelectionValue == 0) {
      System.exit(0);
    }
  }

  @Override
  public void setImageOperationsPerformedStatus(boolean flag) {
    imageOperationsPerformed = flag;
  }

  @Override
  public void displayExceptionMessage(String message) {
    if (Objects.isNull(message) || message.isEmpty()) {
      message = "Image operation failed.";
    }
    JOptionPane.showMessageDialog(null, message, "Exception", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void displayInformationMessage(String message) {
    if (Objects.isNull(message) || message.isEmpty()) {
      message = "Image operation failed.";
    }
    JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void getColorRemoveInput() {
    String[] colorOptions = populateLegendList();
    String selectedColor = displaycolorOptions("Remove Color", colorOptions);

    if (!Objects.isNull(selectedColor)) {
      dmcCodeSelected = selectedColor;
      patternColorRemoveButton.doClick();
    }
  }

  @Override
  public void addNewColorsWindow(List<DmcFloss> dmcList) {
    Objects.requireNonNull(dmcList);
    colorSelectFrame = new JFrame();

    colorSelectFrame.setSize(300, 450); // Sets the x and y dimension of the frame
    colorSelectFrame.setLocation(150, 150);
    colorSelectFrame.setTitle("Select New Colors");
    colorSelectFrame.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

    this.dmcFlossList = dmcList;
    populateDmcFlossList();

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout(10, 10));

    JLabel label = new JLabel();
    label.setText("Select new colors to add to the pattern.");

    panel.add(label, BorderLayout.NORTH);

    JScrollPane dmcScrollPane = new JScrollPane(dmcColorList,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    dmcScrollPane.setPreferredSize(new Dimension(250, 400));

    panel.add(dmcScrollPane, BorderLayout.CENTER);

    selectNewColorPatternButton.setText("Add New Colors");

    panel.add(selectNewColorPatternButton, BorderLayout.SOUTH);

    colorSelectFrame.add(panel);
    colorSelectFrame.setVisible(true);
  }

  @Override
  public void selectColorAndAddTextWindow(List<DmcFloss> dmcList) {
    Objects.requireNonNull(dmcList);
    String[] colorOptions = populateDmcFloss(dmcList);
    dmcCodeSelected = displaycolorOptions("Add Text", colorOptions);

    if (!Objects.isNull(dmcCodeSelected)) {
      addTextFrame = new AddTextFrame(this);
      addTextFrame.setVisible(true);
    }

  }

  /**
   * Private helper method DMC Floss details.
   * 
   * @param dmcList List of DmcFloss
   * @return List of DMC codes
   */
  private String[] populateDmcFloss(List<DmcFloss> dmcList) {
    int size = dmcList.size();
    String[] dmcCode = new String[size];
    int j = 0;
    for (int i = 0; i < size; i++) {

      String code = String.format("%s", dmcList.get(i).getDmcCode());
      dmcCode[j] = code;
      j++;

    }

    return dmcCode;
  }

  @Override
  public void setAddTextToPattern(String text) {
    Objects.requireNonNull(text);
    if (!text.isEmpty()) {
      this.addTextToPattern = text;
      addTextButton.doClick();
    } else {
      JOptionPane.showMessageDialog(addTextButton, "No text given.", "Information",
          JOptionPane.INFORMATION_MESSAGE);
    }
  }

  @Override
  public void setProgressBarText(String message) {
    Objects.requireNonNull(message);
    progressBarText.setText(message);
    this.repaint();
  }

  /**
   * Private helper method to add symbol onto the image at given coordinates.
   * 
   * @param symbolCordinatesList List of symbolCordinatesList
   */
  private void addSymbols(List<SymbolCordinates> symbolCordinatesList) {
    BufferedImage test = new BufferedImage(imageToDisplay.getWidth(), imageToDisplay.getHeight(),
        BufferedImage.TYPE_INT_ARGB);

    Graphics2D g = (Graphics2D) test.getGraphics();

    g.drawImage(imageToDisplay, 1, 2, null);
    AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);

    g.setComposite(alpha);
    g.setColor(Color.BLACK);
    g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));

    FontMetrics fMetrics = g.getFontMetrics();

    for (SymbolCordinates symbolCordinates : symbolCordinatesList) {
      String symbolToAdd = String.valueOf(symbolCordinates.getSymbol());
      fMetrics.getStringBounds(symbolToAdd, g);

      int centerX = symbolCordinates.getxCordinate();
      int centerY = symbolCordinates.getyCordinate();

      g.drawString(symbolToAdd, centerY, centerX);
    }
    imageLabel.setIcon(new ImageIcon(test));
    imageLabel.setText("");
    imageLabel.setHorizontalAlignment(JLabel.LEFT);
    imageLabel.setVerticalAlignment(JLabel.TOP);

    this.repaint();
    g.dispose();
  }

  @Override
  public void diplaySymbolsOnPattern(List<SymbolCordinates> symbolCordinatesList) {
    Objects.requireNonNull(symbolCordinatesList);
    addSymbols(symbolCordinatesList);
  }
}
