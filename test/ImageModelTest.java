import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import images.imagemodel.ColorTransformation;
import images.imagemodel.ColorTransformationInterface;
import images.imagemodel.Filter;
import images.imagemodel.FilterInterface;
import images.imagemodel.Image;
import images.imagemodel.ImageInterface;
import images.imagemodel.ImageModel;
import images.imagemodel.ImageModelInterface;
import images.imagemodel.Pattern;
import images.imagemodel.PatternInterface;
import images.imagemodel.Pixelate;
import images.imagemodel.PixelateInterface;
import images.imagemodel.ReducingColorDensity;
import images.imagemodel.ReducingColorDensityInterface;
import org.junit.Test;


/**
 * A JUnit test class for manipulating 2 D RGB array image data.
 */
public class ImageModelTest {

  /**
   * Test method to test Filter - Blur Logic.
   */

  @Test
  public void testBlur() {
    float[][] kernel = { { 2.0f, 1.0f, 2.0f }, { -1.0f, 0f, 2.0f }, { 1.0f, 1.0f, 1.0f } };

    int[][][] rgbBuffer = { { { 1, 1, 1 }, { 6, 6, 6 }, { 4, 4, 4 }, { 5, 5, 5 } },
        { { 8, 8, 8 }, { 7, 7, 7 }, { 9, 9, 9 }, { 0, 0, 0 } },
        { { 43, 43, 43 }, { 54, 54, 54 }, { 56, 56, 56 }, { 76, 76, 76 } },
        { { 65, 65, 65 }, { 43, 43, 43 }, { 123, 123, 123 }, { 32, 32, 32 } } };

    int[][][] expectedOutput = { { { 27, 27, 27 }, { 31, 31, 31 }, { 20, 20, 20 }, { 5, 5, 5 } },
        { { 124, 124, 124 }, { 179, 179, 179 }, { 205, 205, 205 }, { 136, 136, 136 } },
        { { 238, 238, 238 }, { 341, 341, 341 }, { 319, 319, 319 }, { 117, 117, 117 } },
        { { 237, 237, 237 }, { 433, 433, 433 }, { 337, 337, 337 }, { 65, 65, 65 } } };

    FilterInterface filter = new Filter();
    int[][][] actualOutput = filter.doFilter(rgbBuffer, kernel);
    for (int i = 0; i < actualOutput.length; i++) {
      for (int j = 0; j < actualOutput[0].length; j++) {
        for (int k = 0; k < rgbBuffer[0][0].length; k++) {
          assertEquals(expectedOutput[i][j][k], actualOutput[i][j][k]);
        }
      }

    }
  }

  /**
   * Test method to test Filter - NullPointerException, when RGB buffer value
   * passed as null.
   */
  @Test(expected = NullPointerException.class)
  public void testFilterIllegalArgumentRgbBufferAsNull() {
    float[][] kernel = { { 2.0f, 1.0f, 2.0f }, { -1.0f, 0f, 2.0f }, { 1.0f, 1.0f, 1.0f } };

    FilterInterface filter = new Filter();

    filter.doFilter(null, kernel);

  }

  /**
   * Test method to test Filter - NullPointerException, when kernel value passed
   * as null.
   */

  @Test(expected = NullPointerException.class)
  public void testFilterIllegalArgumentKernelAsNull() {
    int[][][] rgbBuffer = { { { 1, 1, 1 }, { 6, 6, 6 }, { 4, 4, 4 }, { 5, 5, 5 } },
        { { 8, 8, 8 }, { 7, 7, 7 }, { 9, 9, 9 }, { 0, 0, 0 } },
        { { 43, 43, 43 }, { 54, 54, 54 }, { 56, 56, 56 }, { 76, 76, 76 } },
        { { 65, 65, 65 }, { 43, 43, 43 }, { 123, 123, 123 }, { 32, 32, 32 } } };
    FilterInterface filter = new Filter();

    filter.doFilter(rgbBuffer, null);

  }

  /**
   * Test method to test Filter - Sharpness Logic.
   */

  @Test
  public void testSharp() {
    float[][] kernel = { { -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f },
        { -1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f }, { -1 / 8f, 1 / 4f, 1.0f, 1 / 4f, -1 / 8f },
        { -1 / 8f, 1 / 4f, 1 / 4f, 1 / 4f, -1 / 8f },
        { -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f, -1 / 8f } };

    int[][][] rgbBuffer = { { { 1, 1, 1 }, { 6, 6, 6 }, { 4, 4, 4 }, { 5, 5, 5 } },
        { { 8, 8, 8 }, { 7, 7, 7 }, { 9, 9, 9 }, { 0, 0, 0 } },
        { { 43, 43, 43 }, { 54, 54, 54 }, { 56, 56, 56 }, { 76, 76, 76 } },
        { { 65, 65, 65 }, { 43, 43, 43 }, { 123, 123, 123 }, { 32, 32, 32 } } };

    int[][][] expectedOutput = {
        { { -14, -14, -14 }, { -16, -16, -16 }, { -19, -19, -19 }, { -17, -17, -17 } },
        { { -2, -2, -2 }, { 9, 9, 9 }, { 22, 22, 22 }, { 4, 4, 4 } },
        { { 62, 62, 62 }, { 127, 127, 127 }, { 126, 126, 126 }, { 116, 116, 116 } },
        { { 75, 75, 75 }, { 112, 112, 112 }, { 172, 172, 172 }, { 82, 82, 82 } } };

    FilterInterface filter = new Filter();
    int[][][] actualOutput = filter.doFilter(rgbBuffer, kernel);
    for (int i = 0; i < actualOutput.length; i++) {
      for (int j = 0; j < actualOutput[0].length; j++) {
        for (int k = 0; k < rgbBuffer[0][0].length; k++) {
          assertEquals(expectedOutput[i][j][k], actualOutput[i][j][k]);
        }
      }

    }
  }

  /**
   * Test method to test Color Transformation - Gray Scale Logic.
   */
  @Test
  public void testGrayScale() {
    float[][] colorTransformationMatrix = { { 0.2126f, 0.7152f, 0.0722f },
        { 0.2126f, 0.7152f, 0.0722f }, { 0.2126f, 0.7152f, 0.0722f } };

    int[][][] rgbBuffer = { { { 27, 27, 27 }, { 31, 31, 31 }, { 20, 20, 20 }, { 5, 5, 5 } },
        { { 124, 124, 124 }, { 179, 179, 179 }, { 205, 205, 205 }, { 136, 136, 136 } },
        { { 238, 238, 238 }, { 255, 255, 255 }, { 255, 255, 255 }, { 117, 117, 117 } },
        { { 237, 237, 237 }, { 255, 255, 255 }, { 255, 255, 255 }, { 65, 65, 65 } } };

    int[][][] expectedOutput = { { { 27, 27, 27 }, { 31, 31, 31 }, { 20, 20, 20 }, { 5, 5, 5 } },
        { { 124, 124, 124 }, { 179, 179, 179 }, { 205, 205, 205 }, { 136, 136, 136 } },
        { { 238, 238, 238 }, { 255, 255, 255 }, { 255, 255, 255 }, { 117, 117, 117 } },
        { { 237, 237, 237 }, { 255, 255, 255 }, { 255, 255, 255 }, { 65, 65, 65 } } };

    ColorTransformationInterface colorTransformation = new ColorTransformation();
    int[][][] actualOutput = colorTransformation.doColorTransformation(rgbBuffer,
        colorTransformationMatrix);
    for (int i = 0; i < actualOutput.length; i++) {
      for (int j = 0; j < actualOutput[0].length; j++) {
        for (int k = 0; k < rgbBuffer[0][0].length; k++) {
          assertEquals(expectedOutput[i][j][k], actualOutput[i][j][k]);
        }
      }
    }
  }

  /**
   * Test method to test Color Transformation - Sepia Tone Logic.
   */
  @Test
  public void testSepiaTone() {
    float[][] colorTransformationMatrix = { { 0.393f, 0.769f, 0.189f }, { 0.393f, 0.769f, 0.189f },
        { 0.393f, 0.769f, 0.189f } };

    int[][][] rgbBuffer = { { { 27, 27, 27 }, { 31, 31, 31 }, { 20, 20, 20 }, { 5, 5, 5 } },
        { { 124, 124, 124 }, { 179, 179, 179 }, { 205, 205, 205 }, { 136, 136, 136 } },
        { { 238, 238, 238 }, { 255, 255, 255 }, { 255, 255, 255 }, { 117, 117, 117 } },
        { { 237, 237, 237 }, { 255, 255, 255 }, { 255, 255, 255 }, { 65, 65, 65 } } };

    int[][][] expectedOutput = { { { 36, 36, 36 }, { 42, 42, 42 }, { 27, 27, 27 }, { 7, 7, 7 } },
        { { 168, 168, 168 }, { 242, 242, 242 }, { 277, 277, 277 }, { 184, 184, 184 } },
        { { 322, 322, 322 }, { 345, 345, 345 }, { 345, 345, 345 }, { 158, 158, 158 } },
        { { 320, 320, 320 }, { 345, 345, 345 }, { 345, 345, 345 }, { 88, 88, 88 } } };

    ColorTransformationInterface colorTransformation = new ColorTransformation();
    int[][][] actualOutput = colorTransformation.doColorTransformation(rgbBuffer,
        colorTransformationMatrix);
    for (int i = 0; i < actualOutput.length; i++) {
      for (int j = 0; j < actualOutput[0].length; j++) {
        for (int k = 0; k < rgbBuffer[0][0].length; k++) {
          assertEquals(expectedOutput[i][j][k], actualOutput[i][j][k]);
        }
      }
    }
  }

  /**
   * Test method to test Color Transformation - NullPointerException, when RGB
   * buffer value passed as null.
   */
  @Test(expected = NullPointerException.class)
  public void testColorTransformationIllegalArgumentRgbBufferAsNull() {
    float[][] colorTransformationMatrix = { { 0.393f, 0.769f, 0.189f }, { 0.393f, 0.769f, 0.189f },
        { 0.393f, 0.769f, 0.189f } };

    ColorTransformationInterface colorTransformation = new ColorTransformation();

    colorTransformation.doColorTransformation(null, colorTransformationMatrix);

  }

  /**
   * Test method to test Color Transformation - NullPointerException, when color
   * transformation matrix value passed as null.
   */
  @Test(expected = NullPointerException.class)
  public void testFilterIllegalArgumentColorTransformationMatrixAsNull() {
    int[][][] rgbBuffer = { { { 1, 1, 1 }, { 6, 6, 6 }, { 4, 4, 4 }, { 5, 5, 5 } },
        { { 8, 8, 8 }, { 7, 7, 7 }, { 9, 9, 9 }, { 0, 0, 0 } },
        { { 43, 43, 43 }, { 54, 54, 54 }, { 56, 56, 56 }, { 76, 76, 76 } },
        { { 65, 65, 65 }, { 43, 43, 43 }, { 123, 123, 123 }, { 32, 32, 32 } } };
    ColorTransformationInterface colorTransformation = new ColorTransformation();

    colorTransformation.doColorTransformation(rgbBuffer, null);

  }

  @Test
  public void testReduceColorDensityTo8Colors() {

    int[][][] rgbBuffer = { { { 27, 27, 27 }, { 31, 31, 31 }, { 20, 20, 20 }, { 5, 5, 5 } },
        { { 124, 124, 124 }, { 179, 179, 179 }, { 205, 205, 205 }, { 136, 136, 136 } },
        { { 238, 238, 238 }, { 255, 255, 255 }, { 255, 255, 255 }, { 117, 117, 117 } },
        { { 237, 237, 237 }, { 255, 255, 255 }, { 255, 255, 255 }, { 65, 65, 65 } } };

    int[][][] expectedOutput = { { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } },
        { { 0, 0, 0 }, { 255, 255, 255 }, { 0, 0, 0 }, { 255, 255, 255 } },
        { { 255, 255, 255 }, { 255, 255, 255 }, { 255, 255, 255 }, { 0, 0, 0 } },
        { { 255, 255, 255 }, { 255, 255, 255 }, { 255, 255, 255 }, { 0, 0, 0 } } };

    ReducingColorDensityInterface reduceColorDensity = new ReducingColorDensity();
    int[][][] actualOutput = reduceColorDensity.doReduceColorDensity(rgbBuffer, 2, true);
    for (int i = 0; i < actualOutput.length; i++) {
      for (int j = 0; j < actualOutput[0].length; j++) {
        for (int k = 0; k < rgbBuffer[0][0].length; k++) {
          assertEquals(expectedOutput[i][j][k], actualOutput[i][j][k]);
        }
      }
    }
  }

  @Test
  public void testReduceColorDensityTo512Colors() {

    int[][][] rgbBuffer = {
        { { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 } },
        { { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 } },
        { { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 } },
        { { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 } } };

    int[][][] expectedOutput = {
        { { 109, 109, 109 }, { 109, 109, 109 }, { 109, 109, 109 }, { 109, 109, 109 } },
        { { 109, 109, 109 }, { 146, 146, 146 }, { 146, 146, 146 }, { 146, 146, 146 } },
        { { 109, 109, 109 }, { 146, 146, 146 }, { 109, 109, 109 }, { 109, 109, 109 } },
        { { 109, 109, 109 }, { 109, 109, 109 }, { 146, 146, 146 }, { 109, 109, 109 } } };

    ReducingColorDensityInterface reduceColorDensity = new ReducingColorDensity();
    int[][][] actualOutput = reduceColorDensity.doReduceColorDensity(rgbBuffer, 8, true);
    for (int i = 0; i < actualOutput.length; i++) {
      for (int j = 0; j < actualOutput[0].length; j++) {
        for (int k = 0; k < rgbBuffer[0][0].length; k++) {
          assertEquals(expectedOutput[i][j][k], actualOutput[i][j][k]);
        }
      }
    }
  }

  @Test
  public void testReduceColorDensityTo512ColorsWithoutEssence() {

    int[][][] rgbBuffer = {
        { { 128, 128, 128 }, { 255, 255, 255 }, { 128, 128, 128 }, { 128, 128, 128 } },
        { { 128, 128, 128 }, { 128, 128, 128 }, { 0, 0, 0 }, { 128, 128, 128 } },
        { { 128, 128, 128 }, { 255, 255, 255 }, { 128, 128, 128 }, { 128, 128, 128 } },
        { { 128, 128, 128 }, { 0, 0, 0 }, { 128, 128, 128 }, { 128, 128, 128 } } };

    int[][][] expectedOutput = {
        { { 109, 109, 109 }, { 255, 255, 255 }, { 109, 109, 109 }, { 109, 109, 109 } },
        { { 109, 109, 109 }, { 109, 109, 109 }, { 0, 0, 0 }, { 109, 109, 109 } },
        { { 109, 109, 109 }, { 255, 255, 255 }, { 109, 109, 109 }, { 109, 109, 109 } },
        { { 109, 109, 109 }, { 0, 0, 0 }, { 109, 109, 109 }, { 109, 109, 109 } } };

    ReducingColorDensityInterface reduceColorDensity = new ReducingColorDensity();
    int[][][] actualOutput = reduceColorDensity.doReduceColorDensity(rgbBuffer, 8, false);
    for (int i = 0; i < actualOutput.length; i++) {
      for (int j = 0; j < actualOutput[0].length; j++) {
        for (int k = 0; k < rgbBuffer[0][0].length; k++) {
          assertEquals(expectedOutput[i][j][k], actualOutput[i][j][k]);
        }
      }
    }
  }

  @Test
  public void testImageModel() throws IOException {
    ImageInterface image = new Image();

    assertEquals("No image is loaded.", image.toString());

    String directory = new File(".").getCanonicalPath();
    String input_file_path = String.format(directory + "\\res\\ImageResources\\IndiaFlag.png");

    image.loadImage(input_file_path);

    assertEquals("Image Height - 426 and Width - 640", image.toString());
  }

  @Test
  public void testPixelate() throws IOException {

    int[][][] rgbBuffer = { { { 27, 27, 27 }, { 31, 31, 31 }, { 20, 20, 20 }, { 5, 5, 5 } },
        { { 124, 124, 124 }, { 179, 179, 179 }, { 205, 205, 205 }, { 136, 136, 136 } },
        { { 238, 238, 238 }, { 255, 255, 255 }, { 255, 255, 255 }, { 117, 117, 117 } },
        { { 237, 237, 237 }, { 255, 255, 255 }, { 255, 255, 255 }, { 65, 65, 65 } } };

    int[][][] expectedOutput = {
        { { 179, 179, 179 }, { 179, 179, 179 }, { 136, 136, 136 }, { 136, 136, 136 } },
        { { 179, 179, 179 }, { 179, 179, 179 }, { 136, 136, 136 }, { 136, 136, 136 } },
        { { 255, 255, 255 }, { 255, 255, 255 }, { 65, 65, 65 }, { 65, 65, 65 } },
        { { 255, 255, 255 }, { 255, 255, 255 }, { 65, 65, 65 }, { 65, 65, 65 } } };

    PixelateInterface pixelate = new Pixelate();

    int[][][] actualOutput = pixelate.doPixelate(rgbBuffer, 2);

    for (int i = 0; i < actualOutput.length; i++) {
      for (int j = 0; j < actualOutput[0].length; j++) {
        for (int k = 0; k < rgbBuffer[0][0].length; k++) {
          assertEquals(expectedOutput[i][j][k], actualOutput[i][j][k]);
        }
      }
    }
  }

  @Test
  public void testPattern() throws IOException {

    int[][][] rgbBuffer = { { { 43, 43, 43 }, { 6, 6, 6 }, { 43, 43, 43 }, { 5, 5, 5 } },
        { { 8, 8, 8 }, { 7, 7, 7 }, { 9, 9, 9 }, { 0, 0, 0 } },
        { { 43, 43, 43 }, { 54, 54, 54 }, { 43, 43, 43 }, { 76, 76, 76 } },
        { { 65, 65, 65 }, { 43, 43, 43 }, { 123, 123, 123 }, { 32, 32, 32 } } };
    String expectedOutput = "2 x 2\n" + "\n" + "tt\n" + "tt\n" + "\n" + "LEGEND\n" + "t DMC-934\n";

    PatternInterface pattern = new Pattern();

    String actualOutput = pattern.doPattern(rgbBuffer, 2);

    assertEquals(expectedOutput, actualOutput);

  }

}
