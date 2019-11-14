package zxh.demo.anagram;

import com.google.common.collect.ArrayListMultimap;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * AnagramFinder:
 * @author zhangxuhai
 * @date 2019/11/14
*/
public class AnagramFinder {

    public static Set<String> find(List<String> input) {
        ArrayListMultimap<String, String> resultMultiMap = ArrayListMultimap.create();
        input.forEach(word -> resultMultiMap.put(sortByChars(word), word));
        return resultMultiMap
                .asMap()
                .values()
                .stream()
                .filter(strings -> strings.size() > 1)
                .map(strings -> String.join(" ", strings))
                .collect(Collectors.toSet());
    }

    private static String sortByChars(String s) {
        return s.codePoints().sorted().mapToObj(String::valueOf).collect(Collectors.joining());
    }
}
