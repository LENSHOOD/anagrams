package zxh.demo.anagram;

import zxh.demo.anagram.domain.AnagramResult;

import java.util.Arrays;
import java.util.Objects;
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

    public boolean compareCharacters(String s1, String s2) {
        if (Objects.isNull(s1) || Objects.isNull(s2)) {
            return false;
        }

        char[] s1c = s1.toCharArray();
        char[] s2c = s2.toCharArray();

        if (s1c.length != s2c.length) {
            return false;
        }

        Arrays.sort(s1c);
        Arrays.sort(s2c);
        return new String(s1c).equalsIgnoreCase(new String(s2c));
    }
}
