
public class BuildingCost {  // Dapani ktiriou
    private Building building;
    private Costs cost_type;
    private double consumption;

    public BuildingCost() {
    }

    public BuildingCost(Building building, Costs cost_type) {  // for the cost types that dont have monthly consumption
        this.building = building;
        this.cost_type = cost_type;

    }

    public BuildingCost(Building building, Costs cost_type, double consumption) { // for the cost types that have monthly consumption
        this.building = building;
        this.cost_type = cost_type;
        this.consumption = consumption;
    }

    public Building getBuilding() {
        return building;
    }

    public Costs getCost_type() {
        return cost_type;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setCost_type(Costs cost_type) {
        this.cost_type = cost_type;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }


}
