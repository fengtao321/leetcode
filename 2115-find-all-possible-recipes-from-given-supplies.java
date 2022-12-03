import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        HashSet<String> supplySet = new HashSet<>();
        HashMap<String, ArrayList<String>> warehouse = new HashMap<>();
        HashMap<String, Integer> traceMap = new HashMap<>();

        for (String supply : supplies) {
            supplySet.add(supply);
        }

        List<String> supplyQueue = new ArrayList<>();

        for (int i = 0; i < ingredients.size(); i++) {
            String recipe = recipes[i];
            List<String> ingredientList = ingredients.get(i);

            boolean allSupplied = true;
            int notSuppliedNum = 0;

            for (String ingredient : ingredientList) {
                if (!supplySet.contains(ingredient)) {
                    allSupplied = false;
                    notSuppliedNum++;

                    ArrayList<String> list = warehouse.getOrDefault(ingredient, new ArrayList<String>());
                    list.add(recipe);
                    warehouse.put(ingredient, list);
                }
            }

            if (allSupplied) {
                supplyQueue.add(recipe);
                supplySet.add(recipe);
            } else {
                traceMap.put(recipe, notSuppliedNum);
            }
        }

        for (int i = 0; i < supplyQueue.size(); i++) {
            String supply = supplyQueue.get(i);
            if (!warehouse.containsKey(supply))
                continue;

            for (String receipt : warehouse.get(supply)) {
                int counter = traceMap.get(receipt) - 1;
                traceMap.put(receipt, counter);

                if (counter == 0 && !supplySet.contains(receipt)) {
                    supplyQueue.add(receipt);
                }
            }

            warehouse.remove(supply);
        }

        return supplyQueue;
    }
}