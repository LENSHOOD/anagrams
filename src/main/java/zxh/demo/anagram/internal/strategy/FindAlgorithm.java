package zxh.demo.anagram.internal.strategy;

import java.util.Arrays;
import java.util.Objects;

/**
 * FindAlgorithm:
 * @author zhangxuhai
 * @date 2019/10/24
*/
public interface FindAlgorithm {

    /**
     * Find anagram
     * @param inputArray input string array
     * @return output string array
     */
    String[] findAnagram(String[] inputArray);

    /**
     * provide method to compare if two string have same characters
     * @param s1 one string
     * @param s2 another string
     * @return true if same
     */
    default boolean compareCharacters(String s1, String s2) {
        if (Objects.isNull(s1) || Objects.isNull(s2)) {
            return false;
        }

        char[] s1c = s1.toCharArray();
        char[] s2c = s2.toCharArray();

        if (s1c.length != s2c.length) {
            return false;
        }

        Arrays.sort(s1c);
        Arrays.sort(s2c);
        return new String(s1c).equalsIgnoreCase(new String(s2c));
    }
}
