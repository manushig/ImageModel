package images.imagemodel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.util.Pair;

/**
 * Image class implements the ImageInterface interface that can be used to
 * manipulate images to produce some interesting effects.
 */
public class Image implements ImageInterface {
  private int[][][] rgb_buffer;
  private List<Legend> pattern_legend_list;
  private String pattern;

  /**
   * Constructs a Image with rgb_buffer as null, as no image is loaded yet and
   * pattern as null, since no pattern is generated yet.
   *
   */

  public Image() {
    this.rgb_buffer = null;
    this.pattern = null;
    this.pattern_legend_list = new ArrayList<Legend>();
  }

  /**
   * Constructs a Copy constructor for ImageModel class.
   * 
   */

  public Image(Image image) {
    if (!Objects.isNull(image.rgb_buffer)) {
      this.rgb_buffer = new int[image.rgb_buffer.length][image.rgb_buffer[0].length][image.rgb_buffer[0][0].length];
      for (int x = 0; x < image.rgb_buffer.length; x++) {
        for (int y = 0; y < image.rgb_buffer[0].length; y++) {
          for (int z = 0; z < image.rgb_buffer[0][0].length; z++) {
            this.rgb_buffer[x][y][z] = image.rgb_buffer[x][y][z];
          }
        }
      }
    } else {
      this.rgb_buffer = null;
    }

    if (!Objects.isNull(image.pattern)) {
      this.pattern = image.pattern;
    } else {
      this.pattern = null;
    }

    this.pattern_legend_list = new ArrayList<Legend>();
    for (int i = 0; i < image.pattern_legend_list.size(); i++) {
      Objects.requireNonNull(image.pattern_legend_list.get(i));
      this.pattern_legend_list.add(image.pattern_legend_list.get(i));
    }
  }

  @Override
  public String toString() {
    if (Objects.isNull(this.rgb_buffer)) {
      return String.format("No image is loaded.");
    } else {

      return String.format("Image Height - %d and Width - %d", this.rgb_buffer.length,
          this.rgb_buffer[0].length);
    }
  }

  @Override
  public ImageInterface loadImage(String fileName) throws IOException {
    Objects.requireNonNull(fileName, "Filename cannot be null");

    int[][][] rgb_buffer = ImageUtilities.readImage(fileName);

    Objects.requireNonNull(rgb_buffer, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer;

    return new Image(this);
  }

  @Override
  public ImageInterface saveImage(String fileName) throws IOException {
    Objects.requireNonNull(fileName, "Filepath cannot be null.");

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    ImageUtilities.writeImage(image_buffer, image_buffer[0].length, image_buffer.length, fileName);

    return new Image(this);
  }

  @Override
  public ImageInterface colorTransformation(float[][] colorTransformedMatrix) {
    Objects.requireNonNull(colorTransformedMatrix, "Color Transformed matrix cannot not be null");

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    ColorTransformationInterface colorTransformation = new ColorTransformation();

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    int[][][] rgb_buffer_processed = colorTransformation.doColorTransformation(image_buffer,
        colorTransformedMatrix);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    int[][][] rgb_buffer_processed_clamped = Clamping.doClamping(rgb_buffer_processed);

    Objects.requireNonNull(rgb_buffer_processed_clamped, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer_processed_clamped;

    return new Image(this);
  }

  @Override
  public ImageInterface filter(float[][] kernel) {
    Objects.requireNonNull(kernel, "Kernel value cannot not be null.");

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    FilterInterface filter = new Filter();

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    int[][][] rgb_buffer_processed = filter.doFilter(image_buffer, kernel);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    int[][][] rgb_buffer_processed_clamped = Clamping.doClamping(rgb_buffer_processed);

    Objects.requireNonNull(rgb_buffer_processed_clamped, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer_processed_clamped;

    return new Image(this);
  }

  @Override
  public ImageInterface reduceColorDensity(int noOfColorsToReduceTo, Boolean isDitheringRequired) {

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    ReducingColorDensityInterface colorDensity = new ReducingColorDensity();

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    int[][][] rgb_buffer_processed = colorDensity.doReduceColorDensity(image_buffer,
        noOfColorsToReduceTo, isDitheringRequired);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    int[][][] rgb_buffer_processed_clamped = Clamping.doClamping(rgb_buffer_processed);

    Objects.requireNonNull(rgb_buffer_processed_clamped, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer_processed_clamped;

    return new Image(this);
  }

  @Override
  public ImageInterface mosaic(int noOfSeeds) {

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    MosaicInterface mosaic = new Mosaic();

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    int[][][] rgb_buffer_processed = mosaic.doMosaic(image_buffer, noOfSeeds);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer_processed;

    return new Image(this);
  }

  @Override
  public ImageInterface pixelate(int noOfSquaresAcross) {

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    PixelateInterface pixelate = new Pixelate();

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    int[][][] rgb_buffer_processed = pixelate.doPixelate(image_buffer, noOfSquaresAcross);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    this.rgb_buffer = rgb_buffer_processed;

    return new Image(this);
  }

  @Override
  public ImageInterface pattern1() throws IOException {
    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    PixelateInterface pixelate = new Pixelate();

    PatternInterface pattern = new Pattern();

    int noOfSquaresAcross = ImageOperationsUtility.getNoofSquaresAcrossPattern();

    int[][][] rgb_buffer_processed = pixelate.doPixelate(image_buffer, noOfSquaresAcross);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    String generated_pattern = pattern.doPattern1(rgb_buffer_processed, noOfSquaresAcross);

    Objects.requireNonNull(generated_pattern, "Generated pattern value cannot be null.");

    this.pattern = generated_pattern;

    return new Image(this);
  }

  @Override
  public ImageInterface savePattern(String fileName) throws IOException {
    Objects.requireNonNull(fileName, "Filepath cannot be null");
    // Objects.requireNonNull(this.pattern, "Please generate Pattern first.");

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    Objects.requireNonNull(image_buffer);

    PatternInterface pattern = new Pattern();

    int noOfSquaresAcross = ImageOperationsUtility.getNoofSquaresAcrossPattern();

    List<Legend> legendList_copy = ImageOperationsUtility.copyLegendList(this.pattern_legend_list);

    Objects.requireNonNull(legendList_copy);

    String patternToPrint = pattern.savePattern(image_buffer, noOfSquaresAcross, legendList_copy);

    Objects.requireNonNull(patternToPrint);
    
    ImageOperationsUtility.savePattern(patternToPrint, fileName);

    return new Image(this);
  }

  @Override
  public List<Legend> getPatternLegend() {
    return this.pattern_legend_list;
  }

  @Override
  public BufferedImage getImage() {
    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    BufferedImage bufferedImage = ImageUtilities.getBufferedImage(image_buffer,
        image_buffer[0].length, image_buffer.length);
    Objects.requireNonNull(bufferedImage);

    return bufferedImage;
  }

  @Override
  public ImageInterface pattern() throws IOException {
    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    PixelateInterface pixelate = new Pixelate();

    PatternInterface pattern = new Pattern();

    int noOfSquaresAcross = ImageOperationsUtility.getNoofSquaresAcrossPattern();

    int[][][] rgb_buffer_processed = pixelate.doPixelate(image_buffer, noOfSquaresAcross);

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    Pair<int[][][], List<Legend>> result = pattern.doPattern(rgb_buffer_processed,
        noOfSquaresAcross);

    Objects.requireNonNull(result);

    rgb_buffer_processed = result.getKey();

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    List<Legend> legendList = result.getValue();

    Objects.requireNonNull(legendList, "Legend List cannot be null");

    this.rgb_buffer = rgb_buffer_processed;

    this.pattern_legend_list = legendList;

    return new Image(this);
  }

  @Override
  public ImageInterface patternRemoveColor(String dmcCode) throws IOException {
    Objects.requireNonNull(dmcCode);

    Objects.requireNonNull(this.pattern_legend_list, "Generate the pattern first.");

    List<Legend> legendList_copy = ImageOperationsUtility.copyLegendList(this.pattern_legend_list);

    Objects.requireNonNull(legendList_copy);

    PatternInterface pattern = new Pattern();

    int noOfSquaresAcross = ImageOperationsUtility.getNoofSquaresAcrossPattern();

    List<Legend> legendList = pattern.doRemoveColorPattern(noOfSquaresAcross, dmcCode,
        legendList_copy);

    Objects.requireNonNull(legendList, "Legend List cannot be null");

    this.pattern_legend_list = legendList;

    return new Image(this);
  }

  @Override
  public ImageInterface patternReplaceColor(int xCordinate, int yCordinate, String dmcCode)
      throws IOException {
    Objects.requireNonNull(dmcCode);

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    Objects.requireNonNull(image_buffer);

    Objects.requireNonNull(this.pattern_legend_list, "Generate the pattern first.");

    List<Legend> legendList_copy = ImageOperationsUtility.copyLegendList(this.pattern_legend_list);

    Objects.requireNonNull(legendList_copy);

    PatternInterface pattern = new Pattern();

    int noOfSquaresAcross = ImageOperationsUtility.getNoofSquaresAcrossPattern();

    Pair<int[][][], List<Legend>> result = pattern.doReplaceColorPattern(image_buffer,
        noOfSquaresAcross, xCordinate, yCordinate, dmcCode, legendList_copy);

    Objects.requireNonNull(result);

    int[][][] rgb_buffer_processed = result.getKey();

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    List<Legend> legendList = result.getValue();

    Objects.requireNonNull(legendList, "Legend List cannot be null");

    this.rgb_buffer = rgb_buffer_processed;

    this.pattern_legend_list = legendList;

    return new Image(this);
  }

  @Override
  public ImageInterface patternAddText(String text, String dmcCode) throws IOException {
    Objects.requireNonNull(dmcCode, "DMC Code cannot be null");

    Objects.requireNonNull(text, "Text input cannot be null");

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    Objects.requireNonNull(image_buffer);

    PatternInterface pattern = new Pattern();

    int noOfSquaresAcross = ImageOperationsUtility.getNoofSquaresAcrossPattern();

    List<Legend> legendList_copy = ImageOperationsUtility.copyLegendList(this.pattern_legend_list);

    Objects.requireNonNull(legendList_copy);

    Pair<int[][][], List<Legend>> result = pattern.doAddTextPattern(image_buffer, noOfSquaresAcross,
        text, dmcCode, legendList_copy);

    Objects.requireNonNull(result);

    int[][][] rgb_buffer_processed = result.getKey();

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    List<Legend> legendList = result.getValue();

    Objects.requireNonNull(legendList, "Legend List cannot be null");

    this.rgb_buffer = rgb_buffer_processed;

    this.pattern_legend_list = legendList;

    return new Image(this);
  }

  @Override
  public ImageInterface patternAddNewColor(List<String> selectedColors) throws IOException {
    Objects.requireNonNull(selectedColors, "Selected colors cannot be null");

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    Objects.requireNonNull(image_buffer);

    PatternInterface pattern = new Pattern();

    List<Legend> legendList_copy = ImageOperationsUtility.copyLegendList(this.pattern_legend_list);

    Objects.requireNonNull(legendList_copy);

    Pair<int[][][], List<Legend>> result = pattern.doAddNewColorsPattern(image_buffer,
        selectedColors, legendList_copy);

    Objects.requireNonNull(result);

    int[][][] rgb_buffer_processed = result.getKey();

    Objects.requireNonNull(rgb_buffer_processed, "2 D RGB array value cannot be null.");

    List<Legend> legendList = result.getValue();

    Objects.requireNonNull(legendList, "Legend List cannot be null");

    this.rgb_buffer = rgb_buffer_processed;

    this.pattern_legend_list = legendList;

    return new Image(this);
  }

  @Override
  public List<SymbolCordinates> patternGetCoordinatesForSymbol() throws IOException {

    Objects.requireNonNull(this.rgb_buffer, "Please load the image first.");

    int[][][] image_buffer = ImageOperationsUtility.copyArray(this.rgb_buffer);

    Objects.requireNonNull(image_buffer);

    PatternInterface pattern = new Pattern();

    int noOfSquaresAcross = ImageOperationsUtility.getNoofSquaresAcrossPattern();

    List<Legend> legendList_copy = ImageOperationsUtility.copyLegendList(this.pattern_legend_list);

    Objects.requireNonNull(legendList_copy);

    List<SymbolCordinates> symbolCordinates = pattern.getCordinatesForSymbol(image_buffer,
        legendList_copy, noOfSquaresAcross);

    Objects.requireNonNull(symbolCordinates);

    return symbolCordinates;
  }
}
