package zxh.demo.anagram.internal.util;

import zxh.demo.anagram.AnagramFinderException;

import java.io.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TextFileProcessHelper:
 * @author zhangxuhai
 * @date 2019/10/22
*/
public class TextFileProcessHelper {

    private TextFileProcessHelper() {
        // should be empty
    }

    /**
     * Read from file, take every line as one element of array
     * @param inputFilePath input file path
     * @return string array
     * @throws NullPointerException if input file path is null
     * @throws AnagramFinderException if any IOException throw
     */
    public static String[] readFromTextFile(String inputFilePath) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(checkNotNull(inputFilePath, "File path cannot be null")))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new AnagramFinderException("Read text file error: " + e.getMessage());
        }
    }

    /**
     * Write string array to file, take every element as one line
     * @param outputArray output string array
     * @param outputFilePath output file path
     * @throws AnagramFinderException if any IOException throw
     */
    public static void writeToFile(String[] outputArray, String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(checkNotNull(outputFilePath, "File path cannot be null")))) {
            for (String s : outputArray) {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new AnagramFinderException("Read text file error: " + e.getMessage());
        }
    }
}
