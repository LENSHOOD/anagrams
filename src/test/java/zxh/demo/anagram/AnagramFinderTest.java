package zxh.demo.anagram;

import org.junit.Test;
import zxh.demo.anagram.domain.AnagramResult;

import static org.junit.Assert.*;

public class AnagramFinderTest {

    @Test
    public void validate_one_word_input_array_then_output_same_array() {
        String[] givenArray = new String[] {
                "a", "b", "c", "d", "e", "f"
        };

        AnagramFinder anagramFinder = new AnagramFinder();
        AnagramResult anagramResult = anagramFinder.find(givenArray);

        assertEquals(givenArray[0], anagramResult.getAnagramArray()[0]);
        assertEquals(givenArray[1], anagramResult.getAnagramArray()[1]);
        assertEquals(givenArray[2], anagramResult.getAnagramArray()[2]);
        assertEquals(givenArray[3], anagramResult.getAnagramArray()[3]);
        assertEquals(givenArray[4], anagramResult.getAnagramArray()[4]);

        assertEquals(6, anagramResult.getSetCount());
        assertEquals(1, anagramResult.getLongestSetLength());
    }

    @Test
    public void validate_empty_input_array_then_output_same_array() {
        String[] givenArray = new String[] {};

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