package zxh.demo.anagram;

import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * AnagramFinderTest:
 * @author zhangxuhai
 * @date 2019/11/14
*/
public class AnagramFinderTest {
    
    @Test
    public void should_get_anagram_when_given_anagrams() {
        List<String> given = Arrays.asList("kinship", "enlist", "boaster",
                "fresher", "sinks", "knits",
                "rots", "pinkish", "inlets",
                "boaters", "refresh", "skins",
                "stink", "sort", "listen",
                "borates", "silent", "Algonkins",
                "Blomkest", "antependiums", "eunuchizing",
                "lambkill", "plumier", "shafting");

        Set<String> expect = new HashSet<>(Arrays.asList(
                "kinship pinkish",
                "enlist inlets listen silent",
                "boaster boaters borates",
                "fresher refresh",
                "sinks skins",
                "knits stink",
                "rots sort"
        ));

        Set<String> resultSet = AnagramFinder.find(given);
        assertEquals(expect, resultSet);
    }

    @Test
    public void should_get_empty_set_when_given_no_anagrams() {
        List<String> given = Arrays.asList("borates", "silent", "Algonkins",
                "Blomkest", "antependiums", "eunuchizing",
                "lambkill", "plumier", "shafting");

        Set<String> expect = Collections.emptySet();

        Set<String> resultSet = AnagramFinder.find(given);
        assertEquals(expect, resultSet);
    }

    @Test
    public void should_read_from_file() throws IOException {
        AnagramFinder.doAnagrams(
                "/Users/ZhangXuhai/Personal/code/java-learning/anagram/src/test/resources/wordlist.txt");
    }
}
