package zxh.demo.anagram.internal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

public class TextFileProcessHelperTest {

    private static String inputFilePath;
    private static String outputFilePath;

    private static String[] expectStringArray = new String[] {
            "kinship", "pinkish", "enlist", "Abeokuta",
            "inlets", "listen", "silent", "coordinator",
            "boaster", "boaters", "borates", "lacerated",
            "fresher", "refresh", "sinks", "tourings",
            "skins", "knits", "stink", "urali",
            "rots", "sort", "professoriat", "sheriffship"
    };

    @Before
    public void init() {
        inputFilePath = this.getClass().getResource("/").getPath() + "tmpInput";
        outputFilePath = this.getClass().getResource("/").getPath() + "tmpOutput";
        String inputString = Stream.of(expectStringArray).reduce((pre, curr) -> pre + "\n" + curr).orElse("");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(inputFilePath)))) {
            writer.write(inputString);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Test
    public void validate_read_file() {
        assertArrayEquals(expectStringArray, TextFileProcessHelper.readFromTextFile(inputFilePath));
    }

    @Test
    public void validate_write_file() {
        TextFileProcessHelper.writeToFile(expectStringArray, outputFilePath);
        assertArrayEquals(expectStringArray, TextFileProcessHelper.readFromTextFile(outputFilePath));
    }

    @After
    public void destroy() throws IOException {
        Files.deleteIfExists(Paths.get(inputFilePath));
        Files.deleteIfExists(Paths.get(outputFilePath));
    }
}