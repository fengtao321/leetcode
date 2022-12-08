import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

class PrintHashMap {
    private void printHashMap(HashMap<Integer, ArrayList<Integer>> sourceMap) {
        sourceMap.forEach((k, v) -> {
            System.out.println("");
            System.out.print("key: " + k + " value: ");
            printArrayList(v);
            System.out.println("");
        });
    }

    private void printTreeMap(TreeMap<Integer, Integer> sourceMap) {
        System.out.println("==============");
        sourceMap.forEach((k, v) -> {
            System.out.print("key: " + k + " value: " + v);
            System.out.println("");
        });
    }

    private void printArrayList(ArrayList<Integer> object) {
        object.forEach(value -> System.out.print(value + "->"));
    }

    private void print2D(int mat[][]) {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

}
