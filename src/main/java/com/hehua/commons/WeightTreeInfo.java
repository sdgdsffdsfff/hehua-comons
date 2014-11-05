package com.hehua.commons;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.math.RandomUtils;

/**
 * 带权重的树
 * 
 * @author w.vela <wangtianzhou@diandian.com>
 * 
 * @Date Apr 13, 2011 3:46:42 PM
 * @param <T>
 */
public class WeightTreeInfo<T> implements Iterable<T> {

    private static final int DEFAULT_WEIGHT = 100;

    private final SortedMap<Integer, T> nodes = new TreeMap<Integer, T>();

    private final IdentityHashMap<Integer, T> originalWeightMap = new IdentityHashMap<>();

    private int maxWeight = 0;

    public void putNode(T node, int weight) {
        maxWeight += weight;
        nodes.put(maxWeight, node);
        originalWeightMap.put(new Integer(weight), node);
    }

    public T getNode() {
        int resultIndex = RandomUtils.nextInt(maxWeight) + 1;
        return nodes.get(nodes.tailMap(resultIndex).firstKey());
    }

    public static <T> WeightTreeInfo<T> buildSingleOne(T one) {
        WeightTreeInfo<T> result = new WeightTreeInfo<>();
        result.putNode(one, DEFAULT_WEIGHT);
        return result;
    }

    /**
     * 按照概率排序的list表
     * 
     * @return
     */
    public List<T> getWeightShuffleNodes() {
        SortedMap<Integer, T> sortMap = new TreeMap<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                int result = o2.compareTo(o1);
                if (result == 0) {
                    result = 1;
                }
                return result;
            }
        });
        for (Entry<Integer, T> entry : originalWeightMap.entrySet()) {
            sortMap.put(RandomUtils.nextInt(entry.getKey()), entry.getValue());
        }
        return new ArrayList<>(sortMap.values());
    }

    public static void main(String[] args) {
        WeightTreeInfo<String> aInfo = new WeightTreeInfo<String>();
        aInfo.putNode("test1", 10);
        aInfo.putNode("test2", 40);
        aInfo.putNode("test3", 50);
        int a1 = 0, a2 = 0, a3 = 0;
        for (int i = 0; i < 100000; i++) {
            List<String> list = aInfo.getWeightShuffleNodes();
            System.out.println(list);
            if (list.get(0).equals("test1")) {
                a1++;
            }
            if (list.get(1).equals("test1")) {
                a2++;
            }
            if (list.get(2).equals("test1")) {
                a3++;
            }
            assert (list.size() == 3);
        }
        System.out.println(a1 + ", " + a2 + ", " + a3);
    }

    @Override
    public Iterator<T> iterator() {
        return nodes.values().iterator();
    }

    public Set<Entry<Integer, T>> entrySet() {
        return nodes.entrySet();
    }

    @Override
    public String toString() {
        return nodes.toString();
    }

}
