import java.util.ArrayList;

public class CostsCollection {
    private boolean isEmpty = true;
    double sum = 0;
    private ArrayList<Costs> costs_collection = new ArrayList<>();

    public void insert(Costs costs) {
        costs_collection.add(costs);
        isEmpty = false;
    }


    public void print_elements_of_costs_collection() {  //using this method all costs are shown
        if (!isEmpty) {
            for (Costs aCosts_collection : costs_collection) {
                System.out.println(aCosts_collection + "\n");
            }
        } else {
            System.out.println("Costs collection is empty/There is no cost to show");
        }
    }

    public Costs getCostUsingIDCode(String id) {  //we get one expense from costs collection depending on id
        if (!isEmpty) {
            for (Costs aCosts_collection : costs_collection) {
                if (aCosts_collection.getID().equals(id)) {
                    return aCosts_collection;
                }
            }
        } else {
            System.out.println("Costs collection is empty/Can not retrieve cost by its ID");
        }
        return null;
    }

    public ArrayList<Costs> getCollection() {
        return costs_collection;
    }

}

