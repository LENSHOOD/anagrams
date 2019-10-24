package zxh.demo.anagram.internal.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * HashSetStore:
 * @author zhangxuhai
 * @date 2019/10/24
*/
public class HashMapStore implements FindAlgorithm {

    private class AnotherString {
        private String value;
        private int hashCode;

        AnotherString(String value) {
            this.value = checkNotNull(value, "Value cannot be null.");

            char[] valueOfChar = value.toCharArray();
            Arrays.sort(valueOfChar);
            hashCode = new String(valueOfChar).hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof AnotherString) {
                return compareCharacters(value, ((AnotherString) o).value);
            }

            return false;
        }

        @Override
        public int hashCode() {
            return hashCode;
        }
    }

    @Override
    public String[] findAnagram(String[] inputArray) {
        Map<AnotherString, StringBuilder> resultMap = new HashMap<>();
        Stream.of(inputArray).forEach(s-> {
            AnotherString key = new AnotherString(s);
            StringBuilder current = resultMap.get(key);
            resultMap.put(key, Objects.isNull(current) ? new StringBuilder(s) : current.append(" ").append(s));
        });

        return resultMap.values().stream().map(StringBuilder::toString).filter(s -> s.contains(" ")).toArray(String[]::new);
    }
}
