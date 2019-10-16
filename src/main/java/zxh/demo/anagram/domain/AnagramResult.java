package zxh.demo.anagram.domain;

import lombok.Builder;
import lombok.Data;

/**
 * AnagramResult:
 * @author zhangxuhai
 * @date 2019-10-16
*/
@Data
@Builder
public class AnagramResult {

    private String[] anagramArray;
    private int setCount;
    private int longestSetLength;
}
