import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KnapsackDP {

    public KnapsackDP() {
//        int[] values = {1, 6, 18, 22, 28};
//        int[] weights = {1, 2, 5, 6, 7};
//        int totalWeight = 11;

        int[] values = {30, 40, 45, 100};
        int[] weights = {1, 2, 3, 4};
        int totalWeight = 6;



        int[][] result = buildDpArray(values, weights, totalWeight);
        print(result);
        System.out.println("\nThe maximum answer is: " + result[result.length-1][result[0].length-1] + "\n");
        List<Integer> chosen = chosenNumber(result, values, weights);
        System.out.print(chosen);
    }

    private int[][] buildDpArray(int[] values, int[] weight, int totalWeight) {
        int[][] result = new int[values.length][totalWeight+1];
        for (int j=0; j<=totalWeight; j++) {
            if (j >= weight[0] && weight[0] < totalWeight) {
                result[0][j] = values[0];
            }
        }
        for (int i=1; i<weight.length; i++) {
            for (int j=0; j<=totalWeight; j++) {
                int currentWeight = weight[i];

                if (j < currentWeight) {
                    result[i][j] = result[i-1][j];
                } else if (currentWeight <= totalWeight) {
                    result[i][j] = Math.max(result[i-1][j], values[i] + result[i-1][j-currentWeight]);
                }
            }
        }
        return result;
    }

    private List<Integer> chosenNumber(int[][] result, int[] values, int[] weights) {
        List<Integer> chosen = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int maxValue = result[result.length-1][result[0].length-1];
        int i = values.length-1, j = result[0].length-1;

        while (i > 0 && j > 0) {
            int curValue = values[i];
            int curWeight = weights[i];

            if (result[i][j] == result[i-1][j]) {
                i--;
            } else {
                stack.push(curValue);
                maxValue -= curValue;
                j -= curWeight;
            }
        }
        if (maxValue != 0) {
            stack.push(values[0]);
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
