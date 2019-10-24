package zxh.demo.anagram.internal.strategy;

import zxh.demo.anagram.AnagramFinderException;

/**
 * FindAlgorithmFactory:
 * @author zhangxuhai
 * @date 2019/10/24
*/
public class FindAlgorithmFactory {

    public enum Algorithm {
        SLOWEST;
    }

    public static FindAlgorithm create(Algorithm algorithm) {
        switch (algorithm) {
            case SLOWEST:
                return new Slowest();
            default:
                throw new AnagramFinderException("No suitable find algorithm find.");
        }
    }
}
