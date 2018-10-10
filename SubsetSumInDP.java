import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SubsetSumInDP {

    public SubsetSumInDP() {
        // Use array to represent a set
//        int[] set = {2, 4, 7, 10};
//        int totalWeight = 17;

        int[] set = {2, 3};
        int totalWeight = 10;

        int[][] result = buildDpArray(set, totalWeight);
        print(result);
        System.out.println("\nThe maximum answer is: " + result[result.length-1][result[0].length-1] + "\n");
        List<Integer> chosen = chosenNumber(result, set);
        System.out.print(chosen);
    }

    private int[][] buildDpArray(int[] set, int totalWeight) {
        int[][] result = new int[set.length][totalWeight+1];
        for (int j=0; j<=totalWeight; j++) {
            if (j >= set[0] && set[0] < totalWeight) {
                result[0][j] = set[0];
            }
        }
        for (int i=1; i<set.length; i++) {
            for (int j=0; j<=totalWeight; j++) {
                int currentWeight = set[i];

                if (j < currentWeight) {
                    result[i][j] = result[i-1][j];
                } else if (currentWeight <= totalWeight) {
                    result[i][j] = Math.max(result[i-1][j], currentWeight + result[i-1][j-currentWeight]);
                }
            }
        }
        return result;
    }

    private List<Integer> chosenNumber(int[][] result, int[] set) {
        List<Integer> chosen = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int maxValue = result[result.length-1][result[0].length-1];
        int i = set.length-1, j = result[0].length-1;

        while (i > 0 && j > 0) {
            int curValue = set[i];

            if (result[i][j] != result[i-1][j]) {
                stack.push(curValue);
                maxValue -= curValue;
                j -= curValue;
            }
            i--;
        }
        if (maxValue != 0) {
            stack.push(set[i]);
        }
        while (!stack.isEmpty()) {
            chosen.add(stack.pop());
        }
        return chosen;
    }

    public void print(int[][] arr) {
        for (int i=arr.length-1; i>=0; i--) {
            for (int j=0; j<arr[0].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
