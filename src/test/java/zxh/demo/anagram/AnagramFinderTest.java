package zxh.demo.anagram;

import org.junit.Test;
import zxh.demo.anagram.domain.AnagramResult;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class AnagramFinderTest {

    @Test
    public void validate_one_word_input_array_then_output_anagram_array() {
        String[] givenArray = new String[] {
                "kinship", "pinkish", "enlist", "Abeokuta",
                "inlets", "listen", "silent", "coordinator",
                "boaster", "boaters", "borates", "lacerated",
                "fresher", "refresh", "sinks", "tourings",
                "skins", "knits", "stink", "urali",
                "rots", "sort", "professoriat", "sheriffship"
        };

        String[] expectArray = new String[] {
                "kinship pinkish",
                "enlist inlets listen silent",
                "boaster boaters borates",
                "fresher refresh",
                "sinks skins",
                "knits stink",
                "rots sort"
        };

        AnagramFinder anagramFinder = new AnagramFinder();
        AnagramResult anagramResult = anagramFinder.find(givenArray);

        assertEquals(new HashSet<>(Arrays.asList(expectArray)), new HashSet<>(Arrays.asList(anagramResult.getAnagramArray())));
        assertEquals(7, anagramResult.getSetCount());
        assertEquals(4, anagramResult.getLongestSetLength());
    }

    @Test
    public void validate_no_anagram_input_array_then_output_empty_array() {
        String[] givenArray = new String[] {
                "a", "b", "c", "d", "e"
        };

        AnagramFinder anagramFinder = new AnagramFinder();
        AnagramResult anagramResult = anagramFinder.find(givenArray);

        assertEquals(0, anagramResult.getAnagramArray().length);
        assertEquals(0, anagramResult.getSetCount());
        assertEquals(0, anagramResult.getLongestSetLength());
    }

    @Test
    public void validate_compareCharacters() {
        AnagramFinder anagramFinder = new AnagramFinder();

        assertTrue(anagramFinder.compareCharacters("kinship", "pinkish"));
        assertTrue(anagramFinder.compareCharacters("boaster", "boaters"));
        assertTrue(anagramFinder.compareCharacters("ABC", "CAB"));

        assertFalse(anagramFinder.compareCharacters("kinship", "refresh"));
        assertFalse(anagramFinder.compareCharacters("ABC", "AB"));
        assertFalse(anagramFinder.compareCharacters("123", ""));
        assertFalse(anagramFinder.compareCharacters(null, null));
    }
}