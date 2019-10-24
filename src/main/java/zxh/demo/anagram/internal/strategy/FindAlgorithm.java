package zxh.demo.anagram.internal.strategy;

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
}
