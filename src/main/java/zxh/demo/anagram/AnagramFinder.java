package zxh.demo.anagram;

import zxh.demo.anagram.domain.AnagramResult;
import zxh.demo.anagram.internal.strategy.FindAlgorithmFactory;
import zxh.demo.anagram.internal.util.TextFileProcessHelper;

import java.util.stream.Stream;

/**
 * AnagramFinder: find anagrams
 * @author zhangxuhai
 * @date 2019-10-16
 */
public class AnagramFinder {

    /**
     * find anagram from input file, and give the anagrams to output file
     * @param inputFilePath input file path
     * @param outputFilePath output file path
     * @throws NullPointerException if input file path is null
     */
    public void find(String inputFilePath, String outputFilePath) {
        AnagramResult result = find(TextFileProcessHelper.readFromTextFile(inputFilePath));
        TextFileProcessHelper.writeToFile(
                result.getAnagramArray(),
                outputFilePath);

        System.out.println("Result set count: " + result.getSetCount());
        System.out.println("Longest set length: " + result.getLongestSetLength());
    }

    private AnagramResult find(String[] inputArray) {

        String[] anagramArray = FindAlgorithmFactory
                .create(FindAlgorithmFactory.Algorithm.SLOWEST)
                .findAnagram(inputArray);

        int longestSetLength = Stream.of(anagramArray)
                .map(element -> element.split(" ").length)
                .reduce((pre, current) -> pre > current ? pre : current)
                .orElse(0);

        return AnagramResult.builder()
                .anagramArray(anagramArray)
                .setCount(anagramArray.length)
                .longestSetLength(longestSetLength)
                .build();
    }
}
