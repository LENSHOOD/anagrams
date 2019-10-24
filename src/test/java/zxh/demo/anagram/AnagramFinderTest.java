package zxh.demo.anagram;

import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AnagramFinderTest {

    @Test
    public void validate_find_anagram_from_file() throws IOException {
        String inputFilePath = "src/test/resources" + File.separator + "inputAnagramSmall";
        String outputFilePath = "src/test/resources" + File.separator + "outputAnagramSmall";
        String expectFilePath = "src/test/resources" + File.separator + "expectOutputAnagramSmall";

        AnagramFinder anagramFinder = new AnagramFinder();
        anagramFinder.find(inputFilePath, outputFilePath);

        try (BufferedReader outputReader = new BufferedReader(new FileReader(outputFilePath));
             BufferedReader expectReader = new BufferedReader(new FileReader(expectFilePath))
        ) {
            String expectString = expectReader.readLine();
            String outputString = outputReader.readLine();
            while (Objects.nonNull(expectString)) {
                assertEquals(expectString, outputString);
                expectString = expectReader.readLine();
                outputString = outputReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Files.delete(Paths.get(outputFilePath));
    }

    @Test
    public void validate_no_anagram_from_file() throws IOException {
        String inputFilePath = "src/test/resources" + File.separator + "inputNoAnagram";
        String outputFilePath = "src/test/resources" + File.separator + "outputNoAnagram";

        AnagramFinder anagramFinder = new AnagramFinder();
        anagramFinder.find(inputFilePath, outputFilePath);

        try (BufferedReader outputReader = new BufferedReader(new FileReader(outputFilePath))) {
            assertNull(outputReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Files.delete(Paths.get(outputFilePath));
    }

    @Test
    @Ignore
    public void validate_find_anagram_from_real_word_list() throws IOException {
        String inputFilePath = "src/test/resources" + File.separator + "wordlist.txt";
        String outputFilePath = "src/test/resources" + File.separator + "outputWordlist";

        AnagramFinder anagramFinder = new AnagramFinder();
        System.out.println("Start at: " + LocalDateTime.now());
        anagramFinder.find(inputFilePath, outputFilePath);
        System.out.println("End at: " + LocalDateTime.now());
    }

}