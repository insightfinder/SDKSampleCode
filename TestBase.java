package SDK;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Before;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;

// This is a temporary addition to ensure our unit tests are trying not instantiating an actual cassandra session. This will need
// to be replaced by proper unit testing practices moving forward
@PowerMockIgnore("javax.management.*")
public class TestBase {


  @Before
  public void setup() throws Exception {
  }

  protected String readDataFromFile(String fileName) {
    StringBuilder content = new StringBuilder();
    File file = new File(fileName);
    try {
      String strCurrentLine;
      BufferedReader br = new BufferedReader(new FileReader(file));
      while ((strCurrentLine = br.readLine()) != null) {
        content.append(strCurrentLine);
        content.append("\n");
      }
      br.close();
    } catch (FileNotFoundException e) {
      System.out.println(fileName + " not found.");
    } catch (IOException e) {
      System.out.println("Empty file.");
    }
    return content.toString();
  }
}