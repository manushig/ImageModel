package images.imagecontrol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import images.imagecontrol.commands.Blur;
import images.imagecontrol.commands.Dither;
import images.imagecontrol.commands.GrayScale;
import images.imagecontrol.commands.LoadImage;
import images.imagecontrol.commands.Mosaic;
import images.imagecontrol.commands.Pattern;
import images.imagecontrol.commands.PatternAddText;
import images.imagecontrol.commands.PatternNewColors;
import images.imagecontrol.commands.PatternRemoveColor;
import images.imagecontrol.commands.PatternReplaceColor;
import images.imagecontrol.commands.PatternUI;
import images.imagecontrol.commands.Pixelate;
import images.imagecontrol.commands.SaveImage;
import images.imagecontrol.commands.SavePattern;
import images.imagecontrol.commands.Sepia;
import images.imagecontrol.commands.Sharpen;
import images.imagemodel.ImageModelInterface;
import images.imagemodel.ImageObserver;
import images.imagemodel.ImageSubject;
import images.imagemodel.PatternLegendObserver;
import images.imagemodel.SymbolCordinates;
import images.imagemodel.DmcFloss;
import images.imageview.ImageViewInterface;

public class UIController implements Features {

  private ImageViewInterface view;
  private ImageModelInterface model;

  /**
   * Constructs the ImageController, specifying in and out values.
   * 
   * @param in  It is the input to the controller.
   * @param out It is the output from the controller.
   */
  public UIController(ImageModelInterface model) {
    Objects.requireNonNull(model);

    this.model = model;

  }

  public void setView(ImageViewInterface imageView) throws IOException {

    Objects.requireNonNull(imageView);
    this.view = imageView;
    // give the feature callbacks to the view
    this.view.setFeatures(this);
    model.registerImageObserver(view);
    model.registerPatternLegendObserver(view);
  }

  @Override
  public void loadImage(String fileName) {
    try {
      Objects.requireNonNull(fileName);
      if (fileName != "") {
        view.setProgressBarText("In Progress.");
        ImageCommand givenCommand = new LoadImage(fileName);
        givenCommand.execute(model);
        view.clearLegend();
        view.setImageOperationsPerformedStatus(false);
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("Filename cannot be empty.");
      }
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    } finally {
      view.disableMouseListenerOnImagePanel();
    }
  }

  @Override
  public void saveImage(String fileName) {
    try {

      Objects.requireNonNull(fileName);
      if (fileName != "") {
        view.setProgressBarText("In Progress.");
        ImageCommand givenCommand = new SaveImage(fileName);
        givenCommand.execute(model);
        view.setImageOperationsPerformedStatus(false);
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("Filename cannot be empty.");
      }
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    } finally {
      view.disableMouseListenerOnImagePanel();
    }
  }

  @Override
  public void blur() {
    try {
      view.setProgressBarText("In Progress.");
      ImageCommand givenCommand = new Blur();
      givenCommand.execute(model);
      view.clearLegend();
      view.disableMouseListenerOnImagePanel();
      view.setImageOperationsPerformedStatus(true);
      view.setProgressBarText("Suceess.");
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    }
  }

  @Override
  public void sharpen() {
    try {
      view.setProgressBarText("In Progress.");
      ImageCommand givenCommand = new Sharpen();
      givenCommand.execute(model);
      view.clearLegend();
      view.disableMouseListenerOnImagePanel();
      view.setImageOperationsPerformedStatus(true);
      view.setProgressBarText("Suceess.");
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    }
  }

  @Override
  public void grayscale() {
    try {
      view.setProgressBarText("In Progress.");
      ImageCommand givenCommand = new GrayScale();
      givenCommand.execute(model);
      view.clearLegend();
      view.disableMouseListenerOnImagePanel();
      view.setImageOperationsPerformedStatus(true);
      view.setProgressBarText("Suceess.");
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    }
  }

  @Override
  public void sepia() {
    try {
      view.setProgressBarText("In Progress.");
      ImageCommand givenCommand = new Sepia();
      givenCommand.execute(model);
      view.clearLegend();
      view.disableMouseListenerOnImagePanel();
      view.setImageOperationsPerformedStatus(true);
      view.setProgressBarText("Suceess.");
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    }
  }

  @Override
  public void dither(int noOfColorsToReduceTo) {
    try {
      if (noOfColorsToReduceTo > 0) {
        view.setProgressBarText("In Progress.");
        ImageCommand givenCommand = new Dither(noOfColorsToReduceTo);
        givenCommand.execute(model);
        view.clearLegend();
        view.disableMouseListenerOnImagePanel();
        view.setImageOperationsPerformedStatus(true);
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("Value should be greater than 0");
      }
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    }
  }

  @Override
  public void mosaic(int noOfseeds) {
    try {
      if (noOfseeds > 0) {
        view.setProgressBarText("In Progress.");
        ImageCommand givenCommand = new Mosaic(noOfseeds);
        givenCommand.execute(model);
        view.clearLegend();
        view.disableMouseListenerOnImagePanel();
        view.setImageOperationsPerformedStatus(true);
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("Value should be greater than 0");
      }
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    }
  }

  @Override
  public void pixelate(int noOfSquaresAcross) {
    try {
      if (noOfSquaresAcross > 0) {
        view.setProgressBarText("In Progress.");
        ImageCommand givenCommand = new Pixelate(noOfSquaresAcross);
        givenCommand.execute(model);
        view.clearLegend();
        view.disableMouseListenerOnImagePanel();
        view.setImageOperationsPerformedStatus(true);
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("Value should be greater than 0");
      }
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    }
  }

  @Override
  public void pattern() {
    try {
      view.setProgressBarText("In Progress.");
      ImageCommand givenCommand = new Pattern();
      givenCommand.execute(model);
      view.disableMouseListenerOnImagePanel();
      view.setImageOperationsPerformedStatus(true);
      view.setProgressBarText("Suceess.");
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    }
  }

  @Override
  public void savePattern(String fileName) {
    try {
      Objects.requireNonNull(fileName);
      if (fileName != "") {
        view.setProgressBarText("In Progress.");
        ImageCommand givenCommand = new SavePattern(fileName);
        givenCommand.execute(model);
        view.disableMouseListenerOnImagePanel();
        view.setImageOperationsPerformedStatus(true);
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("Filename cannot be empty.");
      }
    } catch (Exception exception) {
      view.displayExceptionMessage("Issue occured. Please retry");
      view.setProgressBarText("Issue occured. Please retry");
    }
  }

  @Override
  public void executeBatchcommand(String commandText) {
    try {
      Objects.requireNonNull(commandText);
      if (commandText != "") {
        view.setProgressBarText("In Progress.");
        StringBuffer out = new StringBuffer();

        Readable reader = new BufferedReader(new StringReader(commandText));
        ImageControllerInterface batchController = new ImageController(reader, out);
        batchController.start(model);
        view.disableImagePanelOnBatchCommands(
            "Load Batch file or enter batch commands to proceed.");
        view.displayInformationMessage(out.toString());
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("Text given is empty.");
      }
    } catch (Exception exception) {
      view.disableImagePanelOnBatchCommands("Load Batch file or enter batch commands to proceed.");
      view.displayExceptionMessage(exception.getMessage());

      view.setProgressBarText("Issue occured. Please retry");
    }
  }

  @Override
  public void executeBatchFile(String fileName) {
    try {
      Objects.requireNonNull(fileName);

      if (fileName != "") {
        view.setProgressBarText("In Progress.");
        StringBuffer out = new StringBuffer();
        Readable reader = new BufferedReader(new FileReader(fileName));

        ImageControllerInterface batchController = new ImageController(reader, out);
        batchController.start(model);
        view.disableImagePanelOnBatchCommands(
            "Load Batch file or enter batch commands to proceed.");
        view.displayInformationMessage(out.toString());
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("FileName cannot be empty.");
      }
    } catch (Exception exception) {
      view.disableImagePanelOnBatchCommands("Load Batch file or enter batch commands to proceed.");
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    }
  }

  @Override
  public void patternReplaceColor(int xCordinate, int yCordinate, String dmcCode) {
    try {
      Objects.requireNonNull(dmcCode);

      if (dmcCode != "") {
        view.setProgressBarText("In Progress.");
        ImageCommand givenCommand = new PatternReplaceColor(xCordinate, yCordinate, dmcCode);
        givenCommand.execute(model);
        view.setImageOperationsPerformedStatus(true);
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("DMC Code cannot be empty.");
      }
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    } finally {
      view.disableMouseListenerOnImagePanel();
    }

  }

  @Override
  public void patternRemoveColor(String dmcCode) {
    try {
      Objects.requireNonNull(dmcCode);

      if (dmcCode != "") {
        view.setProgressBarText("In Progress.");
        ImageCommand givenCommand = new PatternRemoveColor(dmcCode);
        givenCommand.execute(model);
        view.setImageOperationsPerformedStatus(true);
        view.disableMouseListenerOnImagePanel();
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("DMC Code cannot be empty.");
      }
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    } finally {
      view.disableMouseListenerOnImagePanel();
    }
  }

  @Override
  public void imageOperations() {
    view.enableMenuItemsOnImageOperations();
  }

  @Override
  public void commandOperations() {
    view.enableMenuItemsOnCommandOperations();
  }

  @Override
  public void programExit() {
    view.programExitOperations();
  }

  @Override
  public void exchangeColorPatternButtonClicked() {
    view.displayInformationMessage("Please click on image to replace color.");
    view.addMouseListenerOnImagePanel();
  }

  @Override
  public void removeColorPatternButtonClicked() {
    view.getColorRemoveInput();
  }

  @Override
  public void patternAddText(String text, String dmcCode) {
    try {
      Objects.requireNonNull(dmcCode);
      Objects.requireNonNull(text);
      if (dmcCode != "" && text != "") {
        view.setProgressBarText("In Progress.");
        ImageCommand givenCommand = new PatternAddText(text, dmcCode);
        givenCommand.execute(model);
        view.setImageOperationsPerformedStatus(true);
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("DMC Code cannot be empty.");
      }
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    } finally {
      view.disableMouseListenerOnImagePanel();
    }

  }

  @Override
  public void newColorPatternClicked() {
    try {
      view.setProgressBarText("In Progress.");
      List<DmcFloss> dmcFlossDataSet = getDmcFlossDataSet();

      Objects.requireNonNull(dmcFlossDataSet);

      view.addNewColorsWindow(dmcFlossDataSet);
      view.setProgressBarText("Suceess.");
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    } finally {
      view.disableMouseListenerOnImagePanel();
    }

  }

  private List<DmcFloss> getDmcFlossDataSet() throws IOException {
    return model.getDmcFlossColors();
  }

  @Override
  public void addNewColorsPattern(List<String> selectedColors) {
    try {
      Objects.requireNonNull(selectedColors);
      if (selectedColors.size() > 0) {
        view.setProgressBarText("In Progress.");
        ImageCommand givenCommand = new PatternNewColors(selectedColors);
        givenCommand.execute(model);
        view.setImageOperationsPerformedStatus(true);
        view.setProgressBarText("Suceess.");
      } else {
        view.displayExceptionMessage("Select atleast one color.");
      }
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    } finally {
      view.disableMouseListenerOnImagePanel();
    }

  }

  @Override
  public void addTextPatternButtonClicked() {
    try {
      view.setProgressBarText("In Progress.");
      List<DmcFloss> dmcFlossDataSet = getDmcFlossDataSet();

      Objects.requireNonNull(dmcFlossDataSet);

      view.selectColorAndAddTextWindow(dmcFlossDataSet);
      view.setProgressBarText("Suceess.");
    } catch (Exception exception) {
      view.displayExceptionMessage(exception.getMessage());
      view.setProgressBarText("Issue occured. Please retry");
    } finally {
      view.disableMouseListenerOnImagePanel();
    }

  }

  @Override
  public void loadClicked() {
    view.getFileNameForLoad();
  }

  @Override
  public void saveImageClicked() {
    view.getFileNameForSave();

  }

  @Override
  public void ditherClicked() {
    view.getDitherUserInput();

  }

  @Override
  public void mosaicClicked() {
    view.getMosaicUserInput();

  }

  @Override
  public void pixelateClicked() {
    view.getPixelateInput();
  }

  @Override
  public void batchCommandClicked() {
    view.geBatchCommandInput();

  }

  @Override
  public void batchCommandLoadClicked() {
    view.getBatchCommandLoadInput();

  }

  @Override
  public void newColorClicked() {
    view.getNewColorInput();

  }

  @Override
  public void displaySymbolsClicked() {
    try {
      view.setProgressBarText("In Progress.");
      List<SymbolCordinates> symbolCordinatesSet = model.patternGetCordinatesForSymbol();

      Objects.requireNonNull(symbolCordinatesSet);

      view.diplaySymbolsOnPattern(symbolCordinatesSet);
      view.setProgressBarText("Suceess.");
    } catch (Exception exception) {
      view.displayExceptionMessage("Issue occured. Please retry");
      view.setProgressBarText("Issue occured. Please retry");
    } finally {
      view.disableMouseListenerOnImagePanel();
    }

  }

  @Override
  public void savePatternClicked() {
    view.getSavePatternInput();
  }
}
