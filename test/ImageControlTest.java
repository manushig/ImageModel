import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import images.imagecontrol.ImageController;
import images.imagecontrol.ImageControllerInterface;
import images.imagemodel.ImageModelInterface;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for manipulating 2 D RGB array image data.
 */
public class ImageControlTest {
  private StringBuffer log;
  private StringBuffer out;
  private ImageModelInterface mockmodel;

  @Before
  public void setUp() {
    log = new StringBuffer();

    out = new StringBuffer();

    mockmodel = new MockImageModel(log, 1234);
  }

  @Test
  public void TestLoad() throws IOException {

    String command = "load Test.png";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Function: loadImage and Filename: Test.png\n", log.toString());
    assertEquals("load command is executed.\n", out.toString());
  }

  @Test
  public void TestSaveImage() throws IOException {

    String command = "save Test.png";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Function: saveImage and Filename: Test.png\n", log.toString());
    assertEquals("save command is executed.\n", out.toString());
  }

  @Test
  public void TestGrayScale() throws IOException {

    String command = "grayscale";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Function: grayScale\n", log.toString());
    assertEquals("grayscale command is executed.\n", out.toString());
  }

  @Test
  public void TestSepia() throws IOException {

    String command = "sepia";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Function: sepia\n", log.toString());
    assertEquals("sepia command is executed.\n", out.toString());
  }

  @Test
  public void TestBlur() throws IOException {

    String command = "blur";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Function: blur\n", log.toString());
    assertEquals("blur command is executed.\n", out.toString());
  }

  @Test
  public void TestSharpen() throws IOException {

    String command = "sharpen";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Function: sharpen\n", log.toString());
    assertEquals("sharpen command is executed.\n", out.toString());
  }

  @Test
  public void TestDither() throws IOException {

    String command = "dither 2";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Function: dither and noOfColorsToReduceTo: 2\n", log.toString());
    assertEquals("dither command is executed.\n", out.toString());
  }

  @Test
  public void TestMosaic() throws IOException {

    String command = "mosaic 6600";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Function: mosaic and noOfSeeds: 6600\n", log.toString());
    assertEquals("mosaic command is executed.\n", out.toString());
  }

  @Test
  public void TestPixelate() throws IOException {

    String command = "pixelate 30";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Function: pixelate and noOfSquaresAcross: 30\n", log.toString());
    assertEquals("pixelate command is executed.\n", out.toString());
  }

  @Test
  public void TestPattern() throws IOException {

    String command = "pattern";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Function: pattern\n", log.toString());
    assertEquals("pattern command is executed.\n", out.toString());
  }

  @Test
  public void TestSavePattern() throws IOException {

    String command = "savePattern Test.txt";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Function: savePattern and fileName: Test.txt\n", log.toString());
    assertEquals("savePattern command is executed.\n", out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidCommand() throws IOException {

    String command = "saveImage Test.png";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);
  }

  @Test(expected = NullPointerException.class)
  public void TestNullIn() throws IOException {

    Readable reader = null;

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);
  }

  @Test(expected = NullPointerException.class)
  public void TestNullOut() throws IOException {

    String command = "savePattern Test.txt";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, null);
  }

  @Test(expected = NullPointerException.class)
  public void TestNullModel() throws IOException {
    ImageModelInterface mockmodel = null;

    String command = "savePattern Test.txt";
    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);
  }
}
