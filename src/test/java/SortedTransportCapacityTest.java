import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @author Maria.Guseva
 */
public class SortedTransportCapacityTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final String JSON_TEST_PATH = "src/test/resources/correctJsonTest.txt";
    private final String JSON_TEST_RESULT_PATH = "src/test/resources/correctJsonTestResult.txt";
    private StringBuilder contentBuilder = new StringBuilder();


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        try (Stream<String> stream = Files.lines( Paths.get(JSON_TEST_RESULT_PATH), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldPrintResult() {
        String filePath = new File(JSON_TEST_PATH).getAbsolutePath();
        SortedTransportCapacity.main(new String[]{filePath});

        assertEquals(contentBuilder.toString().replace("\r\n", "\n"), outContent.toString().replace("\r\n", "\n"));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }
}