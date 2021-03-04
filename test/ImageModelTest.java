import static org.junit.Assert.assertEquals;

import org.junit.Test;

import images.ColorTransformation;
import images.ColorTransformationInterface;
import images.Filter;
import images.FilterInterface;
import images.ReducingColorDensity;
import images.ReducingColorDensityInterface;

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

    int[][][] expectedOutput = { { {} } };

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

    int[][][] expectedOutput = { { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } },
        { { 0, 0, 0 }, { 9, 9, 9 }, { 22, 22, 22 }, { 4, 4, 4 } },
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
        { { 168, 168, 168 }, { 242, 242, 242 }, { 255, 255, 255 }, { 184, 184, 184 } },
        { { 255, 255, 255 }, { 255, 255, 255 }, { 255, 255, 255 }, { 158, 158, 158 } },
        { { 255, 255, 255 }, { 255, 255, 255 }, { 255, 255, 255 }, { 88, 88, 88 } } };

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
  public void testReduceColorDensity() {

    int[][][] rgbBuffer = {
        { { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 } },
        { { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 } },
        { { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 } },
        { { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 }, { 128, 128, 128 } } };

    int[][][] expectedOutput = { { {} } };

    ReducingColorDensityInterface reduceColorDensity = new ReducingColorDensity();
    int[][][] actualOutput = reduceColorDensity.doReduceColorDensity(rgbBuffer, 2);
    for (int i = 0; i < actualOutput.length; i++) {
      for (int j = 0; j < actualOutput[0].length; j++) {
        for (int k = 0; k < rgbBuffer[0][0].length; k++) {
          assertEquals(expectedOutput[i][j][k], actualOutput[i][j][k]);
        }
      }
    }
  }
}
