package zxh.demo.anagram;

import org.junit.Test;
import zxh.demo.anagram.domain.AnagramResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class AnagramFinderTest {

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
        AnagramFinder anagramFinder = new AnagramFinder();
        AnagramResult anagramResult = anagramFinder.find(givenArraySmall);

        assertEquals(new HashSet<>(Arrays.asList(expectArraySmall)), new HashSet<>(Arrays.asList(anagramResult.getAnagramArray())));
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
    public void validate_compareCharacters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method compareCharacters = AnagramFinder.class.getDeclaredMethod("compareCharacters", String.class, String.class);
        compareCharacters.setAccessible(true);
        AnagramFinder anagramFinder = new AnagramFinder();

        assertTrue((boolean) compareCharacters.invoke(anagramFinder, "kinship", "pinkish"));
        assertTrue((boolean) compareCharacters.invoke(anagramFinder, "boaster", "boaters"));
        assertTrue((boolean) compareCharacters.invoke(anagramFinder, "ABC", "CAB"));

        assertFalse((boolean) compareCharacters.invoke(anagramFinder, "kinship", "refresh"));
        assertFalse((boolean) compareCharacters.invoke(anagramFinder, "ABC", "AB"));
        assertFalse((boolean) compareCharacters.invoke(anagramFinder, "123", ""));
        assertFalse((boolean) compareCharacters.invoke(anagramFinder, null, null));
    }
}