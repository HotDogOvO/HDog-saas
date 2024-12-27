package com.hotdog.saas.domain.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统工具类
 * @author hotdog
 * @date 2024/12/27 10:08
 */
public class SystemUtils {

    /**
     * 对比两个List，返回不重复的部分
     * @param firstList list
     * @param secondList list
     * @return 不重复的内容
     */
    public static <T> Set<T> difference(List<T> firstList, List<T> secondList) {
        Set<T> firstSet = new HashSet<>(firstList);
        Set<T> secondSet = new HashSet<>(secondList);
        return difference(firstSet, secondSet);
    }

    /**
     * 对比两个set，返回不重复的部分
     * @param firstSet set
     * @param secondSet set
     * @return 不重复的内容
     */
    public static <T> Set<T> difference(Set<T> firstSet, Set<T> secondSet) {
        Set<T> difference = new HashSet<>(firstSet);
        difference.removeAll(secondSet);
        return difference;
    }

}
