package zxh.demo.anagram.internal.strategy;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SlowestTest {

    @Test
    public void validate_compareCharacters() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method compareCharacters = Slowest.class.getDeclaredMethod("compareCharacters", String.class, String.class);
        compareCharacters.setAccessible(true);
        Slowest slowest = new Slowest();

        assertTrue((boolean) compareCharacters.invoke(slowest, "kinship", "pinkish"));
        assertTrue((boolean) compareCharacters.invoke(slowest, "boaster", "boaters"));
        assertTrue((boolean) compareCharacters.invoke(slowest, "ABC", "CAB"));

        assertFalse((boolean) compareCharacters.invoke(slowest, "kinship", "refresh"));
        assertFalse((boolean) compareCharacters.invoke(slowest, "ABC", "AB"));
        assertFalse((boolean) compareCharacters.invoke(slowest, "123", ""));
        assertFalse((boolean) compareCharacters.invoke(slowest, null, null));
    }

}