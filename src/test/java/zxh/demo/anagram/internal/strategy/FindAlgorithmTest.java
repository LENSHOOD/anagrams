package zxh.demo.anagram.internal.strategy;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class FindAlgorithmTest {
    private static String[] givenArraySmall = new String[] {
            "kinship", "pinkish", "enlist", "Abeokuta",
            "inlets", "listen", "silent", "coordinator",
            "boaster", "boaters", "borates", "lacerated",
            "fresher", "refresh", "sinks", "tourings",
            "skins", "knits", "stink", "urali",
            "rots", "sort", "professoriat", "sheriffship"
    };
    private static String[] expectArraySmall = new String[] {
            "kinship pinkish",
            "enlist inlets listen silent",
            "boaster boaters borates",
            "fresher refresh",
            "sinks skins",
            "knits stink",
            "rots sort"
    };

    @Test
    public void validate_one_word_input_array_then_output_anagram_array() {
        FindAlgorithm findAlgorithm = FindAlgorithmFactory.create(FindAlgorithmFactory.Algorithm.SLOWEST);
        String[] anagramResult = findAlgorithm.findAnagram(givenArraySmall);

        assertEquals(new HashSet<>(Arrays.asList(expectArraySmall)), new HashSet<>(Arrays.asList(anagramResult)));
    }

    @Test
    public void validate_no_anagram_input_array_then_output_empty_array() {
        String[] givenArray = new String[] {
                "a", "b", "c", "d", "e"
        };

        FindAlgorithm findAlgorithm = FindAlgorithmFactory.create(FindAlgorithmFactory.Algorithm.SLOWEST);
        String[] anagramResult = findAlgorithm.findAnagram(givenArray);

        assertEquals(0, anagramResult.length);
    }
}