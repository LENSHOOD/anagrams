package zxh.demo.anagram;

import zxh.demo.anagram.domain.AnagramResult;

import java.util.*;
import java.util.stream.Stream;

/**
 * AnagramFinder: find anagrams
 * @author zhangxuhai
 * @date 2019-10-16
 */
public class AnagramFinder {
    public AnagramResult find(String[] inputArray) {

        String[] anagramArray = findAnagram(inputArray);

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

    private String[] findAnagram(String[] inputArray) {
        List<List<String>> anagrams = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i++) {
            List<String> innerList = new ArrayList<>();
            innerList.add(inputArray[i]);
            anagrams.add(innerList);
            for (int j = i + 1; j < inputArray.length; j++) {
                final String current = inputArray[j];
                if (anagrams.stream().anyMatch(inner -> inner.contains(current))) {
                    continue;
                }
                if (compareCharacters(inputArray[i], inputArray[j])) {
                    anagrams.get(i).add(inputArray[j]);
                }
            }
        }

        return anagrams
                .stream()
                .filter(s -> s.size() > 1)

                .map(s -> s.stream().sorted().reduce((pre, current) -> pre + " " + current).orElse(""))
                .toArray(String[]::new);
    }

    private boolean compareCharacters(String s1, String s2) {
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
