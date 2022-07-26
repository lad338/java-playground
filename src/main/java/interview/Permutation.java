package interview;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    static void permutate(java.util.List<Integer> arr, int k){
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permutate(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            System.out.println(java.util.Arrays.toString(arr.toArray()));
        }
    }
    public static void main(String[] args){
//        permute(java.util.Arrays.asList(1,2,3), 0);

        showAllPossibleOrder(java.util.Arrays.asList(1,2,3,4), new ArrayList<>());
    }

    public static void showAllPossibleOrder (List<Integer> arr, List<Integer> searched) {
        if (arr.size() == 1) {
            searched.add(arr.get(0));
            System.out.println(searched);
            return;
        }

        for (int i = 0; i < arr.size(); i++) {
            ArrayList<Integer> accessArray = new ArrayList<>(arr);
            ArrayList<Integer> accessSearched = new ArrayList<>(searched);
            accessSearched.add(accessArray.get(i));
            accessArray.remove(i);
            showAllPossibleOrder(accessArray, accessSearched);
        }
    }
}
