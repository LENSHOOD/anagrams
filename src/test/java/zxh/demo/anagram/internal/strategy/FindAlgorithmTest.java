package zxh.demo.anagram.internal.strategy;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
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
    public void validate_compareCharacters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        FindAlgorithm anonymous = dummy -> dummy;

        assertTrue(anonymous.compareCharacters("kinship", "pinkish"));
        assertTrue(anonymous.compareCharacters("boaster", "boaters"));
        assertTrue(anonymous.compareCharacters("ABC", "CAB"));

        assertFalse(anonymous.compareCharacters("kinship", "refresh"));
        assertFalse(anonymous.compareCharacters("ABC", "AB"));
        assertFalse(anonymous.compareCharacters("123", ""));
        assertFalse(anonymous.compareCharacters(null, null));
    }

    @Test
    public void validate_find_anagram_by_slowest() {
        FindAlgorithm findAlgorithm = FindAlgorithmFactory.create(FindAlgorithmFactory.Algorithm.SLOWEST);
        String[] anagramResult = findAlgorithm.findAnagram(givenArraySmall);

        assertEquals(new HashSet<>(Arrays.asList(expectArraySmall)), new HashSet<>(Arrays.asList(anagramResult)));
    }

    @Test
    public void validate_find_anagram_by_hash_map() {
        FindAlgorithm findAlgorithm = FindAlgorithmFactory.create(FindAlgorithmFactory.Algorithm.Hash_MAP_STORE);
        String[] anagramResult = findAlgorithm.findAnagram(givenArraySmall);

        assertEquals(new HashSet<>(Arrays.asList(expectArraySmall)), new HashSet<>(Arrays.asList(anagramResult)));
    }

    @Test
    public void validate_no_anagram_array_by_slowest() {
        String[] givenArray = new String[] {
                "a", "b", "c", "d", "e"
        };

        FindAlgorithm findAlgorithm = FindAlgorithmFactory.create(FindAlgorithmFactory.Algorithm.SLOWEST);
        String[] anagramResult = findAlgorithm.findAnagram(givenArray);

        assertEquals(0, anagramResult.length);
    }

    @Test
    public void validate_no_anagram_array_by_hash_map() {
        String[] givenArray = new String[] {
                "a", "b", "c", "d", "e"
        };

        FindAlgorithm findAlgorithm = FindAlgorithmFactory.create(FindAlgorithmFactory.Algorithm.Hash_MAP_STORE);
        String[] anagramResult = findAlgorithm.findAnagram(givenArray);

        assertEquals(0, anagramResult.length);
    }
}