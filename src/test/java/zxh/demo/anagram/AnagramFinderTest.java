package zxh.demo.anagram;

import org.junit.Test;
import zxh.demo.anagram.domain.AnagramResult;
import zxh.demo.anagram.internal.strategy.FindAlgorithmFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.Assert.*;

public class AnagramFinderTest {

    @Test
    public void validate_find_anagram_from_file() throws IOException {
        String inputFilePath = "src/test/resources" + File.separator + "inputAnagramSmall";
        String outputFilePath = "src/test/resources" + File.separator + "outputAnagramSmall";
        String expectFilePath = "src/test/resources" + File.separator + "expectOutputAnagramSmall";

        AnagramFinder anagramFinder = new AnagramFinder();
        anagramFinder.find(inputFilePath, outputFilePath, FindAlgorithmFactory.Algorithm.Hash_MAP_STORE);

        Set<String> expectSet = new HashSet<>();
        Set<String> outputSet = new HashSet<>();
        try (BufferedReader outputReader = new BufferedReader(new FileReader(outputFilePath));
             BufferedReader expectReader = new BufferedReader(new FileReader(expectFilePath))
        ) {
            String expectString = expectReader.readLine();
            expectSet.add(expectString);
            String outputString = outputReader.readLine();
            outputSet.add(outputString);
            while (Objects.nonNull(expectString)) {
                expectSet.add(expectString);
                if (Objects.nonNull(outputString)) {
                    outputSet.add(outputString);
                }
                expectString = expectReader.readLine();
                outputString = outputReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        assertEquals(expectSet, outputSet);

        Files.delete(Paths.get(outputFilePath));
    }

    @Test
    public void validate_no_anagram_from_file() throws IOException {
        String inputFilePath = "src/test/resources" + File.separator + "inputNoAnagram";
        String outputFilePath = "src/test/resources" + File.separator + "outputNoAnagram";

        AnagramFinder anagramFinder = new AnagramFinder();
        anagramFinder.find(inputFilePath, outputFilePath, FindAlgorithmFactory.Algorithm.Hash_MAP_STORE);

        try (BufferedReader outputReader = new BufferedReader(new FileReader(outputFilePath))) {
            assertNull(outputReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Files.delete(Paths.get(outputFilePath));
    }

    @Test
    public void validate_find_anagram_from_real_word_list() throws IOException {
        String inputFilePath = "src/test/resources" + File.separator + "wordlist.txt";
        String outputFilePath = "src/test/resources" + File.separator + "outputWordlist";

        AnagramFinder anagramFinder = new AnagramFinder();
        Instant start = Instant.now();
        AnagramResult result = anagramFinder.find(inputFilePath, outputFilePath, FindAlgorithmFactory.Algorithm.Hash_MAP_STORE);
        Instant end = Instant.now();

        System.out.println("Start at: " + start.toString());
        System.out.println("End at: " + end);
        System.out.println("Duration: " + (end.toEpochMilli() - start.toEpochMilli()) + " milliseconds");

        assertEquals(20683, result.getSetCount());
        assertEquals(13, result.getLongestSetLength());

        Files.delete(Paths.get(outputFilePath));
    }

}