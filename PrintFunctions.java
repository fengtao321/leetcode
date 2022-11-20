import java.util.ArrayList;
import java.util.HashMap;

class PrintHashMap {
    private void printHashMap(HashMap<Integer, ArrayList<Integer>> sourceMap) {
        sourceMap.forEach((k, v) -> {
            System.out.println("");
            System.out.print("key: " + k + " value: ");
            printArrayList(v);
            System.out.println("");
        });
    }

    private void printArrayList(ArrayList<Integer> object) {
        object.forEach(value -> System.out.print(value + "->"));
    }

}
