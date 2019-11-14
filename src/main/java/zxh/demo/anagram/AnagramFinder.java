package zxh.demo.anagram;

import com.google.common.collect.ArrayListMultimap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * AnagramFinder:
 * @author zhangxuhai
 * @date 2019/11/14
*/
public class AnagramFinder {

    public static void doAnagrams(String path) throws IOException {
        Instant start = Instant.now();
        Set<String> resultSet = find(Files.readAllLines(Paths.get(path)));
        Instant end = Instant.now();
        System.out.println("Total Count: " + resultSet.size());
        System.out.println("Longest Anagram: " + resultSet.stream().mapToInt(s -> s.split(" ").length).max().getAsInt());
        System.out.println("Time Consuming: " + (end.toEpochMilli() - start.toEpochMilli()) + " milliseconds");
    }

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
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
