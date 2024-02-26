package leetcode.graph;

import java.util.*;

public class GreatestCommonDivisorTraversal2709 {

    class Solution {

        public boolean canTraverseAllPairs(int[] nums) {
            int n = nums.length;

            UnionFind unionFind = new UnionFind(n);
            Map<Integer, Integer> factorToIndexMap = new HashMap<>();

            for (int i = 0; i < n; i++) {
                int factor = 2;
                int number = nums[i];
                while (factor * factor <= number) {
                    if (number % factor == 0) {
                        if (factorToIndexMap.containsKey(factor)) {
                            unionFind.union(factorToIndexMap.get(factor), i);
                        } else {
                            factorToIndexMap.put(factor, i);
                        }
                        while (number % factor == 0) {
                            number /= factor;
                        }
                    }
                    factor++;
                }
                if (number > 1) {
                    if (factorToIndexMap.containsKey(number)) {
                        unionFind.union(factorToIndexMap.get(number), i);
                    } else {
                        factorToIndexMap.put(number, i);
                    }
                }
            }

            return unionFind.count == 1;
        }

        public static class UnionFind {

            public int count;
            private int[] parentMap;
            private int[] sizeMap;

            public UnionFind(int n) {
                this.count = n;
                this.parentMap = new int[n];
                this.sizeMap = new int[n];
                for (int i = 0; i < n; i++) {
                    this.parentMap[i] = i;
                    this.sizeMap[i] = 1;
                }
            }

            public int find(int index) {
                if (index != parentMap[index]) {
                    parentMap[index] = find(parentMap[index]);
                }
                return parentMap[index];
            }

            public void union(int x, int y) {
                int px = find(x);
                int py = find(y);
                if (px == py) {
                    return;
                }
                if (sizeMap[px] < sizeMap[py]) {
                    parentMap[px] = py;
                    sizeMap[py] += sizeMap[px];
                } else {
                    parentMap[py] = px;
                    sizeMap[px] += sizeMap[py];
                }
                this.count--;
            }
        }
    }
}
