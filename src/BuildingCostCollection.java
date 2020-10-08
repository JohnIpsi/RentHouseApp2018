import java.util.ArrayList;

public class BuildingCostCollection {

    private boolean isEmpty = true;

    private ArrayList<BuildingCost> building_cost_collection = new ArrayList<>();

    public void insert(BuildingCost building_cost) {
        building_cost_collection.add(building_cost);
        isEmpty = false;
    }


    public void show_all_buildings() {
        if (!isEmpty) {
            for (BuildingCost bu : building_cost_collection) {
                System.out.println(bu.getBuilding());
            }
        } else {
            System.out.print("Building Cost collection is empty/There is no Building Cost to show");
        }
    }

    public void show_building_all_costs(Building building) {  //using this method , we give a building , and all pf this building costs are shown
        if (!isEmpty) {
            for (BuildingCost bc : building_cost_collection) {
                if (bc.getBuilding().getBuilding_id().equals(building.getBuilding_id()))
                    System.out.println(bc.getBuilding()+ "\n");
                    System.out.println(bc.getCost_type());
            }
        } else {
            System.out.print("Building Cost collection is empty/There is no Building Cost to show");
        }
    }

    public Costs getBuildingCost(Building building) {
        if (!isEmpty) {
            for (BuildingCost bc : building_cost_collection) {
                if (bc.getBuilding().getBuilding_id().equals(building.getBuilding_id())) {
                    return bc.getCost_type();
                }
            }
        } else {
            System.out.print("Building does not have expenses");
        }
        return null;
    }

    public double getBuildingConsumption(Building building) {
        if (!isEmpty) {
            for (BuildingCost bc : building_cost_collection) {
                if (bc.getBuilding().getBuilding_id().equals(building.getBuilding_id())) {
                    return bc.getConsumption();
                }
            }
        } else {
            System.out.print("Building does not have expenses");
        }
        return 0;
    }


    public double calculate_total_cost_of_building(Building building) {  //using this method we can calculate the sum of all the cost types costs of a building
        double sum = 0;
        Costs temp;
        for (BuildingCost bc : building_cost_collection) {
            if (bc.getBuilding().getBuilding_id().equals(building.getBuilding_id())) {    //we check all the collection for objects with the building id given
                temp = bc.getCost_type();  //depending on the cost type we use different method every time
                if (temp instanceof Water) {
                    sum = sum + ((Water) temp).calculate_cost(building);
                } else if (temp instanceof Rent) {
                    sum = sum + ((Rent) temp).calculate_cost(building);
                } else if (temp instanceof Telephone) {
                    sum = sum + ((Telephone) temp).calculate_cost(building);
                } else if (temp instanceof Cleaning) {
                    sum = sum + ((Cleaning) temp).calculate_cost(building);
                } else if (temp instanceof Energy) {
                    sum = sum + ((Energy) temp).calculate_cost(building);
                }
            }
        }
        return sum;
    }

    public Building get_specific_id_depending_on_cost_type() {  //using this method we can get the specific id of a building depending on its cost type
        Costs temp;
        for (BuildingCost bc : building_cost_collection) {
            temp = bc.getCost_type();
            if (temp instanceof Water) {
                return bc.getBuilding();
            } else if (temp instanceof Rent) {
                return bc.getBuilding();
            } else if (temp instanceof Telephone) {
                return bc.getBuilding();
            } else if (temp instanceof Cleaning) {
                return bc.getBuilding();
            } else if (temp instanceof Energy) {
                return bc.getBuilding();
            }
        }
        return null;
    }

    public ArrayList<BuildingCost> getBCollection() {
        return building_cost_collection;
    }
}
