package zxh.demo.anagram;

import zxh.demo.anagram.domain.AnagramResult;

import java.util.stream.Stream;

/**
 * AnagramFinder: find anagrams
 * @author zhangxuhai
 * @date 2019-10-16
*/
public class AnagramFinder {
    public AnagramResult find(String[] inputArray) {

        int longestSetLength = Stream.of(inputArray)
                .map(element -> element.split(" ").length)
                .reduce((pre, current) -> pre > current ? pre : current)
                .orElse(0);

        return AnagramResult.builder()
                .anagramArray(inputArray)
                .setCount(inputArray.length)
                .longestSetLength(longestSetLength)
                .build();
    }
}
