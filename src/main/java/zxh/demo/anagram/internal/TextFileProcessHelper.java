package zxh.demo.anagram.internal;

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

    public static String[] readFromTextFile(String inputFilePath) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(checkNotNull(inputFilePath, "File path cannot be null")))) {
              return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new AnagramFinderException("Read text file error: " + e.getMessage());
        }
    }

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
