/**
 * 
 */
package com.hehua.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * 
 * @author zhouzhihua <zhihua@afanda.com>
 * @version 1.0 create at Oct 10, 2012 3:42:35 PM
 */
public class ProbabilityUtils {

    public static final int BASE_PROBABILITY = 10000;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static boolean randomByProbability(int probability) {
        return (1 + RANDOM.nextInt(BASE_PROBABILITY)) <= probability;
    }

    public static <T extends Weightable> T randomByWeight(T[] weightables) {

        Preconditions.checkArgument(ArrayUtils.isNotEmpty(weightables),
                "target array must not be empty!");

        int totalWeight = 0;
        for (T t : weightables) {
            totalWeight += t.getWeight();
        }

        int result = RANDOM.nextInt(totalWeight);
        for (T t : weightables) {
            result -= t.getWeight();
            if (result <= 0) {
                return t;
            }
        }
        throw new RuntimeException("dead code");
    }

    public static <T extends Weightable> T randomByWeight(Collection<T> weightables) {

        Preconditions.checkArgument(CollectionUtils.isNotEmpty(weightables),
                "target collection must not be empty!");

        int totalWeight = 0;
        for (T t : weightables) {
            totalWeight += t.getWeight();
        }

        int result = RANDOM.nextInt(totalWeight);
        for (T t : weightables) {
            // 权重为0认为不应该出现
            if (t.getWeight() <= 0) {
                continue;
            }
            result -= t.getWeight();
            if (result <= 0) {
                return t;
            }
        }
        throw new RuntimeException("dead code");
    }

    public static <T> T randomByWeight(Collection<T> collection, Function<T, Integer> weightFunction) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(collection),
                "target collection must not be empty!");

        int totalWeight = 0;
        for (T t : collection) {
            totalWeight += weightFunction.apply(t);
        }

        int result = RANDOM.nextInt(totalWeight);
        for (T t : collection) {
            // 权重为0认为不应该出现
            int weight = weightFunction.apply(t);
            if (weight <= 0) {
                continue;
            }
            result -= weight;
            if (result <= 0) {
                return t;
            }
        }
        throw new RuntimeException("dead code");
    }

    @Deprecated
    public static <T> T random(Collection<T> collection) {
        if (collection == null) {
            return null;
        }

        int size = collection.size();
        if (size == 0) {
            return null;
        }

        if (collection instanceof List) {
            return randomList((List<T>) collection);
        }

        int rand = RANDOM.nextInt(size);
        for (T t : collection) {
            if (rand == 0) {
                return t;
            }
            rand--;
        }
        return null;

    }

    public static <T> T randomList(List<T> collection) {
        if (collection == null) {
            return null;
        }

        int size = collection.size();
        if (size == 0) {
            return null;
        }

        int rand = RANDOM.nextInt(size);
        return collection.get(rand);

    }

    public static <T> T randomList(List<T> collection, int limitSize) {
        if (collection == null) {
            return null;
        }

        int size = Math.min(limitSize, collection.size());
        if (size == 0) {
            return null;
        }

        int rand = RANDOM.nextInt(size);
        return collection.get(rand);

    }

    public static int randomByWeights(int[] probability) {
        if (probability == null || probability.length == 0) {
            throw new RuntimeException("weights is 0");
        }
        int sum = 0;
        for (int i = 0; i < probability.length; i++) {
            sum += probability[i];
        }
        int result = RANDOM.nextInt(sum) + 1;
        for (int i = 0; i < probability.length; i++) {
            result -= probability[i];
            if (result <= 0) {
                return i;
            }
        }
        throw new RuntimeException("dead code");
    }

    public static int[] randomGroupByWeights(int[] weights, int number) {
        if (number <= 0) {
            throw new RuntimeException("Want to get random number is error!");
        }
        if (weights == null || weights.length < number) {
            throw new RuntimeException("Random array length is less than number");
        }

        int[] targetWeights = new int[weights.length];

        System.arraycopy(weights, 0, targetWeights, 0, targetWeights.length);

        int[] indexs = new int[number];

        for (int i = 0; i < number; i++) {
            int index = randomByWeights(targetWeights);
            indexs[i] = index;
            targetWeights[index] = 0;
        }
        return indexs;
    }

    public static int randomBetweenMinInMax(int min, int max) {
        int randomNum = max - min + 1;
        return RANDOM.nextInt(randomNum) + min;
    }

    public static <T> List<T> randomElements(List<T> elements, int randomSize) {

        if (CollectionUtils.isEmpty(elements)) {
            return Collections.emptyList();
        }

        if (randomSize == 0) {
            return Collections.emptyList();
        }

        if (randomSize < 0) {
            return Lists.newArrayList(elements);
        }

        if (randomSize >= elements.size()) {
            return Lists.newArrayList(elements);
        }

        ArrayList<T> randomElements = Lists.newArrayList(elements);
        Collections.shuffle(randomElements);
        return randomElements.subList(0, randomSize);
    }
}
