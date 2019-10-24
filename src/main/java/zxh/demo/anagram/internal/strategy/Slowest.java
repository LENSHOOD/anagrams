package zxh.demo.anagram.internal.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Slowest:
 * @author zhangxuhai
 * @date 2019/10/24
*/
public class Slowest implements FindAlgorithm {

    @Override
    public String[] findAnagram(String[] inputArray) {
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
}
