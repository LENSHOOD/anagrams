package zxh.demo.anagram.internal.strategy;

import zxh.demo.anagram.AnagramFinderException;

/**
 * FindAlgorithmFactory:
 * @author zhangxuhai
 * @date 2019/10/24
*/
public class FindAlgorithmFactory {

    public enum Algorithm {
        SLOWEST,
        Hash_MAP_STORE;
    }

    public static FindAlgorithm create(Algorithm algorithm) {
        switch (algorithm) {
            case SLOWEST:
                return new Slowest();
            case Hash_MAP_STORE:
                return new HashMapStore();
            default:
                throw new AnagramFinderException("No suitable find algorithm find.");
        }
    }
}
