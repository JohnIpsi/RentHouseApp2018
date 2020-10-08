import java.util.ArrayList;

public class BuildingCollection {
    private boolean isEmpty = true;

    private ArrayList<Building> building_collection = new ArrayList<>();

    public void insert(Building building) {
        building_collection.add(building);
        isEmpty = false;
    }


    public void print_elements_of_building_collection() {
        if (!isEmpty) {
            for (Building aBuilding_collection : building_collection) {
                System.out.println(aBuilding_collection);
            }
        } else {
            System.out.println("Building collection is empty/There is no building to show");
        }
    }

    public Building getBuilding(String id) {
        for (Building b : building_collection) {
            if (b.getBuilding_id().equals(id)) {
                return b;
            }
        }
        return null;
    }
}