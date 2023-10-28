package leetcode;

import java.util.*;

public class MinimumGeneticMutation433 {

    class Solution {

        public int minMutation(
            String startGene,
            String endGene,
            String[] bank
        ) {
            char[] bits = new char[] { 'A', 'C', 'G', 'T' };
            Set<String> set = new HashSet<>();
            Queue<String> queue = new ArrayDeque<>();

            Collections.addAll(set, bank);
            if (!set.contains(endGene)) {
                return -1;
            }

            queue.offer(startGene);
            int mutateCount = 0;

            while (queue.size() > 0) {
                List<String> genes = new ArrayList<>(queue.stream().toList());
                queue.clear();
                mutateCount++;
                for (String gene : genes) {
                    for (int i = 0; i < gene.length(); i++) {
                        for (char bit : bits) {
                            if (bit == gene.charAt(i)) {
                                continue;
                            }
                            char[] geneArray = gene.toCharArray();
                            geneArray[i] = bit;
                            String newGene = new String(geneArray);

                            if (!set.contains(newGene)) {
                                continue;
                            }
                            if (endGene.equals(newGene)) {
                                return mutateCount;
                            }
                            set.remove(newGene);
                            queue.offer(newGene);
                        }
                    }
                }
            }
            return -1;
        }
    }
}
