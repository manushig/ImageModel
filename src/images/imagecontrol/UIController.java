package images.imagecontrol;

import java.io.IOException;
import java.util.HashMap;
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
import images.imagecontrol.commands.PatternUI;
import images.imagecontrol.commands.Pixelate;
import images.imagecontrol.commands.SaveImage;
import images.imagecontrol.commands.SavePattern;
import images.imagecontrol.commands.Sepia;
import images.imagecontrol.commands.Sharpen;
import images.imagemodel.ImageModelInterface;
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
    Objects.requireNonNull(model);
    Objects.requireNonNull(imageView);
    this.view = imageView;
    // give the feature callbacks to the view
    this.view.setFeatures(this);
  }

  @Override
  public void blur() throws IOException {
    ImageCommand givenCommand = new Blur();
    givenCommand.execute(model);
    view.clearLegend();
  }

  @Override
  public void loadImage(String fileName) throws IOException {

    ImageCommand givenCommand = new LoadImage(fileName);
    givenCommand.execute(model);
  }

  @Override
  public void saveImage(String fileName) throws IOException {
    ImageCommand givenCommand = new SaveImage(fileName);
    givenCommand.execute(model);

  }

  @Override
  public void sharpen() throws IOException {
    ImageCommand givenCommand = new Sharpen();
    givenCommand.execute(model);
    view.clearLegend();
  }

  @Override
  public void grayscale() throws IOException {
    ImageCommand givenCommand = new GrayScale();
    givenCommand.execute(model);
    view.clearLegend();
  }

  @Override
  public void sepia() throws IOException {
    ImageCommand givenCommand = new Sepia();
    givenCommand.execute(model);
    view.clearLegend();
  }

  @Override
  public void dither(int noOfColorsToReduceTo) throws IOException {
    ImageCommand givenCommand = new Dither(noOfColorsToReduceTo);
    givenCommand.execute(model);
    view.clearLegend();
  }

  @Override
  public void mosaic(int noOfseeds) throws IOException {
    ImageCommand givenCommand = new Mosaic(noOfseeds);
    givenCommand.execute(model);
    view.clearLegend();
  }

  @Override
  public void pixelate(int noOfSquaresAcross) throws IOException {
    ImageCommand givenCommand = new Pixelate(noOfSquaresAcross);
    givenCommand.execute(model);
    view.clearLegend();
  }

  @Override
  public void pattern() throws IOException {
    ImageCommand givenCommand = new PatternUI();
    givenCommand.execute(model);

  }

}
