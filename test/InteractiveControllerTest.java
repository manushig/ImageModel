import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

import images.imagecontrol.InteractiveController;
import images.imagemodel.ImageModelInterface;
import images.imageview.ImageViewInterface;

/**
 * A JUnit test class for testing Interactive Controller.
 */
public class InteractiveControllerTest {
  private StringBuffer log;
  private ImageViewInterface mockview;
  private ImageModelInterface mockmodel;
  private InteractiveController control;

  @Before
  public void setUp() throws IOException {
    log = new StringBuffer();

    mockview = new MockView(log);

    mockmodel = new MockImageModel(log, 1234);

    control = new InteractiveController(mockmodel);

    control.setView(mockview);
  }

  @Test
  public void testControllerisNotNull() throws IOException {
    assertFalse(Objects.isNull(control));

  }

  @Test(expected = NullPointerException.class)
  public void testControllerModelNull() throws IOException {
    control = new InteractiveController(null);
  }

  @Test
  public void testSetFeatures() throws IOException {
    log = new StringBuffer();
    mockview = new MockView(log);
    mockmodel = new MockImageModel(log, 1234);
    control = new InteractiveController(mockmodel);

    control.setView(mockview);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test(expected = NullPointerException.class)
  public void testSetFeaturesViewNull() throws IOException {
    log = new StringBuffer();
    mockview = new MockView(log);

    control = new InteractiveController(mockmodel);

    control.setView(null);
  }

  @Test
  public void testLoad() {
    String fileName = "Test.png";

    control.loadImage(fileName);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: loadImage and Filename: Test.png\n" + "Function: clearLegend\n"
        + "Function: setImageOperationsPerformedStatus and flag: false\n"
        + "Function: enableImageOperation\n"
        + "Function: setProgressBarText and message: Success.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testLoadNullFileName() {

    control.loadImage(null);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Issue occured. Please retry\n"
        + "Function: setProgressBarText and message: Issue occured. Please retry\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test
  public void testLoadEmptyFileName() {
    String fileName = "";

    control.loadImage(fileName);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Filename not selected.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testSaveImage() {
    String fileName = "Test.png";

    control.saveImage(fileName);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: saveImage and Filename: Test.png\n"
        + "Function: setImageOperationsPerformedStatus and flag: false\n"
        + "Function: setProgressBarText and message: Success.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testSaveImageNullFileName() {

    control.saveImage(null);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Issue occured. Please retry\n"
        + "Function: setProgressBarText and message: Issue occured. Please retry\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test
  public void testSaveImageEmptyFileName() {
    String fileName = "";

    control.saveImage(fileName);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Filename not selected.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testBlurImage() {
    control.blur();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n" + "Function: blur\n"
        + "Function: clearLegend\n" + "Function: disableMouseListenerOnImagePanel\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testSharpenImage() {
    control.sharpen();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n" + "Function: sharpen\n"
        + "Function: clearLegend\n" + "Function: disableMouseListenerOnImagePanel\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testGrayScaleImage() {
    control.grayscale();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n" + "Function: grayScale\n"
        + "Function: clearLegend\n" + "Function: disableMouseListenerOnImagePanel\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testSepiaImage() {
    control.sepia();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n" + "Function: sepia\n"
        + "Function: clearLegend\n" + "Function: disableMouseListenerOnImagePanel\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testDitherImage() {
    int noOfColorsToReduceTo = 2;
    control.dither(noOfColorsToReduceTo);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: dither and noOfColorsToReduceTo: 2\n" + "Function: clearLegend\n"
        + "Function: disableMouseListenerOnImagePanel\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testDitherImageColorsNegative() {
    int noOfColorsToReduceTo = -2;
    control.dither(noOfColorsToReduceTo);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Value should be greater than 0\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testMosaicImage() {
    int noOfseeds = 2;
    control.mosaic(noOfseeds);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: mosaic and noOfSeeds: 2\n" + "Function: clearLegend\n"
        + "Function: disableMouseListenerOnImagePanel\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testMosaicImageSeedsNegative() {
    int noOfseeds = -2;
    control.mosaic(noOfseeds);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Value should be greater than 0\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testPixelateImage() {
    int noOfSquaresAcross = 2;
    control.pixelate(noOfSquaresAcross);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: pixelate and noOfSquaresAcross: 2\n" + "Function: clearLegend\n"
        + "Function: disableMouseListenerOnImagePanel\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testPixelateImageSquareNegative() {
    int noOfSquaresAcross = -2;
    control.pixelate(noOfSquaresAcross);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Value should be greater than 0\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testPatternImage() {
    control.pattern();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n" + "Function: pattern\n"
        + "Function: disableMouseListenerOnImagePanel\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testSavePattern() {
    String fileName = "Test.txt";

    control.savePattern(fileName);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: savePattern and fileName: Test.txt\n"
        + "Function: disableMouseListenerOnImagePanel\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testSavePatternNullFileName() {

    control.savePattern(null);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Issue occured. Please retry\n"
        + "Function: setProgressBarText and message: Issue occured. Please retry\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test
  public void testSavePatternEmptyFileName() {
    String fileName = "";

    control.savePattern(fileName);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Filename not selected.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testExecuteBatchcommand() {
    String commandText = "load Test.png";

    control.executeBatchcommand(commandText);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: loadImage and Filename: Test.png\n"
        + "Function: disableImagePanelOnBatchCommands and message: "
        + "Load Batch file or enter batch commands to proceed.\n"
        + "Function: displayInformationMessage and message: load command is executed.\n" + "\n"
        + "Function: setProgressBarText and message: Success.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testExecuteBatchCommandTextNull() {

    control.executeBatchcommand(null);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: disableImagePanelOnBatchCommands and message: "
        + "Load Batch file or enter batch commands to proceed.\n"
        + "Function: displayExceptionMessage and message: Issue occured. Please retry\n"
        + "Function: setProgressBarText and message: Issue occured. Please retry\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test
  public void testExecuteBatchcommandTextEmpty() {
    String commandText = "";

    control.executeBatchcommand(commandText);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Text given is empty.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testExecuteBatchFile() {
    String fileName = "Test.txt";

    control.executeBatchFile(fileName);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: disableImagePanelOnBatchCommands and message: "
        + "Load Batch file or enter batch commands to proceed.\n"
        + "Function: displayExceptionMessage and message: Issue occured. Please retry\n"
        + "Function: setProgressBarText and message: Issue occured. Please retry\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testExecuteBatchFileNullFileName() {

    control.executeBatchFile(null);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: disableImagePanelOnBatchCommands and message: "
        + "Load Batch file or enter batch commands to proceed.\n"
        + "Function: displayExceptionMessage and message: Issue occured. Please retry\n"
        + "Function: setProgressBarText and message: Issue occured. Please retry\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test
  public void testExecuteBatchFileEmptyFileName() {
    String fileName = "";

    control.executeBatchFile(fileName);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Filename not selected.\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testPatternReplaceColor() {
    String dmcCode = "700";

    control.patternReplaceColor(0, 0, dmcCode);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: patternReplaceColor and xCordinate: 0 and yCordinate:0 and dmcCode: 700\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testPatternReplaceColorNullDmcCode() {

    control.patternReplaceColor(0, 0, null);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Issue occured. Please retry\n"
        + "Function: setProgressBarText and message: Issue occured. Please retry\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test
  public void testPatternReplaceColorEmptyDmcCode() {
    String dmcCode = "";

    control.patternReplaceColor(0, 0, dmcCode);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: DMC Code cannot be empty.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testPatternRemoveColor() {
    String dmcCode = "700";

    control.patternRemoveColor(dmcCode);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: patternRemoveColor and dmcCode: 700\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: disableMouseListenerOnImagePanel\n"
        + "Function: setProgressBarText and message: Success.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testPatternRemoveColorNullDmcCode() {

    control.patternRemoveColor(null);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Issue occured. Please retry\n"
        + "Function: setProgressBarText and message: Issue occured. Please retry\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test
  public void testPatternRemoveColorEmptyDmcCode() {
    String dmcCode = "";

    control.patternRemoveColor(dmcCode);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: DMC Code cannot be empty.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testImageOperations() {
    control.imageOperations();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: enableMenuItemsOnImageOperations\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testProgramExit() {
    control.programExit();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: programExitOperations\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testCommandOperations() {
    control.commandOperations();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: enableMenuItemsOnCommandOperations\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testExchangeColorPatternButtonClicked() {
    control.exchangeColorPatternButtonClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayInformationMessage and message: "
        + "Please click on image to replace color.\n"
        + "Function: addMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testRemoveColorPatternButtonClicked() {
    control.removeColorPatternButtonClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: getColorRemoveInput\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testPatternAddText() {
    String dmcCode = "700";
    String text = "Hi";

    control.patternAddText(text, dmcCode);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: patternAddText and text: Hi and dmcCode: 700\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testpatternAddTextNullDmcCode() {

    control.patternAddText("Hi", null);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Issue occured. Please retry\n"
        + "Function: setProgressBarText and message: Issue occured. Please retry\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test
  public void testPatternAddTextEmptyDmcCode() {
    String dmcCode = "";

    control.patternAddText("Hi", dmcCode);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: DMC Code cannot be empty.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testPatternAddTextNullText() {

    control.patternAddText(null, "700");

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Issue occured. Please retry\n"
        + "Function: setProgressBarText and message: Issue occured. Please retry\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test
  public void testPatternAddTextEmptyText() {
    String dmcCode = "700";

    control.patternAddText("", dmcCode);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: DMC Code cannot be empty.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testNewColorPatternClicked() {
    control.newColorPatternClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: getDmcFlossColors\n"
        + "Function: addNewColorsWindow and  size of ddmcList: 0\n"
        + "Function: setProgressBarText and message: Success.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testAddNewColorsPattern() {
    List<String> selectedColors = new ArrayList<String>();
    selectedColors.add("600");

    control.addNewColorsPattern(selectedColors);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: patternAddNewColors and selectedColors: 600\n"
        + "Function: setImageOperationsPerformedStatus and flag: true\n"
        + "Function: setProgressBarText and message: Success.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testaddNewColorsPatternNullSelectedColors() {
    List<String> selectedColors = null;
    control.addNewColorsPattern(selectedColors);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Issue occured. Please retry\n"
        + "Function: setProgressBarText and message: Issue occured. Please retry\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test
  public void testaddNewColorsPatternSelectedColorsSize0() {
    List<String> selectedColors = new ArrayList<String>();
    control.addNewColorsPattern(selectedColors);

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: displayExceptionMessage and message: Select atleast one color.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());
  }

  @Test
  public void testAddTextPatternButtonClicked() {
    control.addTextPatternButtonClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: getDmcFlossColors\n"
        + "Function: selectColorAndAddTextWindow and size of dmcList: 0\n"
        + "Function: setProgressBarText and message: Success.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testLoadClicked() {
    control.loadClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: getFileNameForLoad\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testSaveImageClicked() {
    control.saveImageClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: getFileNameForSave\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testDitherClicked() {
    control.ditherClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: getDitherUserInput\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testMosaicClicked() {
    control.mosaicClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: getMosaicUserInput\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testPixelateClicked() {
    control.pixelateClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: getPixelateInput\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testBatchCommandClicked() {
    control.batchCommandClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: geBatchCommandInput\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testBatchCommandLoadClicked() {
    control.batchCommandLoadClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: getBatchCommandLoadInput\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testNewColorClicked() {
    control.newColorClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: getNewColorInput\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testDisplaySymbolsClicked() {
    control.displaySymbolsClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: setProgressBarText and message: In Progress.\n"
        + "Function: patternGetCordinatesForSymbol\n"
        + "Function: diplaySymbolsOnPattern and size of symbolCordinatesList: 0\n"
        + "Function: setProgressBarText and message: Success.\n"
        + "Function: disableMouseListenerOnImagePanel\n";
    assertEquals(expectedMessage, log.toString());

  }

  @Test
  public void testSavePatternClicked() {
    control.savePatternClicked();

    String expectedMessage = "Function: setFeatures and feature object passed\n"
        + "Function: registerImageObserver and imageObserver: 1234\n"
        + "Function: registerPatternLegendObserver and patternLegendObserver: 1234\n"
        + "Function: getSavePatternInput\n";
    assertEquals(expectedMessage, log.toString());

  }

}
