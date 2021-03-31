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

  @Before
  public void setUp() {
    log = new StringBuffer();

    out = new StringBuffer();
  }

  @Test
  public void TestLoad() throws IOException {

    ImageModelInterface mockmodel = new MockImageModel(log, 12345);
    String command = "load Test.png";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Filename: Test.png and UniqueCode: 12345", log.toString());
    assertEquals("load command is executed.\n", out.toString());
  }

  @Test
  public void TestSaveImage() throws IOException {

    ImageModelInterface mockmodel = new MockImageModel(log, 12346);

    String command = "save Test.png";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Filename: Test.png and UniqueCode: 12346", log.toString());
    assertEquals("save command is executed.\n", out.toString());
  }

  @Test
  public void TestGrayScale() throws IOException {
    ImageModelInterface mockmodel = new MockImageModel(log, 12347);

    String command = "grayscale";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("UniqueCode: 12347", log.toString());
    assertEquals("grayscale command is executed.\n", out.toString());
  }

  @Test
  public void TestSepia() throws IOException {
    ImageModelInterface mockmodel = new MockImageModel(log, 12348);

    String command = "sepia";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("UniqueCode: 12348", log.toString());
    assertEquals("sepia command is executed.\n", out.toString());
  }

  @Test
  public void TestBlur() throws IOException {
    ImageModelInterface mockmodel = new MockImageModel(log, 12349);

    String command = "blur";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("UniqueCode: 12349", log.toString());
    assertEquals("blur command is executed.\n", out.toString());
  }

  @Test
  public void TestSharpen() throws IOException {
    ImageModelInterface mockmodel = new MockImageModel(log, 12350);

    String command = "sharpen";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("UniqueCode: 12350", log.toString());
    assertEquals("sharpen command is executed.\n", out.toString());
  }

  @Test
  public void TestDither() throws IOException {
    ImageModelInterface mockmodel = new MockImageModel(log, 12351);

    String command = "dither 2";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("noOfColorsToReduceTo: 2 and UniqueCode: 12351", log.toString());
    assertEquals("dither command is executed.\n", out.toString());
  }

  @Test
  public void TestMosaic() throws IOException {
    ImageModelInterface mockmodel = new MockImageModel(log, 12352);

    String command = "mosaic 6600";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("noOfSeeds: 6600 and UniqueCode: 12352", log.toString());
    assertEquals("mosaic command is executed.\n", out.toString());
  }

  @Test
  public void TestPixelate() throws IOException {
    ImageModelInterface mockmodel = new MockImageModel(log, 12353);

    String command = "pixelate 30";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("noOfSquaresAcross: 30 and UniqueCode: 12353", log.toString());
    assertEquals("pixelate command is executed.\n", out.toString());
  }

  @Test
  public void TestPattern() throws IOException {
    ImageModelInterface mockmodel = new MockImageModel(log, 12354);

    String command = "pattern";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("UniqueCode: 12354", log.toString());
    assertEquals("pattern command is executed.\n", out.toString());
  }

  @Test
  public void TestSavePattern() throws IOException {
    ImageModelInterface mockmodel = new MockImageModel(log, 12355);

    String command = "savePattern Test.txt";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);

    assertEquals("Filename: Test.txt and UniqueCode: 12355", log.toString());
    assertEquals("savePattern command is executed.\n", out.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void TestInvalidCommand() throws IOException {
    ImageModelInterface mockmodel = new MockImageModel(log, 12355);

    String command = "saveImage Test.png";

    Readable reader = new BufferedReader(new StringReader(command));

    ImageControllerInterface control = new ImageController(reader, out);

    control.start(mockmodel);
  }
  
  @Test(expected = NullPointerException.class)
  public void TestNullIn() throws IOException {
    ImageModelInterface mockmodel = new MockImageModel(log, 12355);

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
