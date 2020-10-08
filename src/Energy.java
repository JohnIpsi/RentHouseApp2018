
public class Energy extends Costs implements common_methods {
    private double monthly_ERT_cost;  //special characteristic of this specific cost

    public Energy() {
    }

    public Energy(String id, String description, double usage, double price_per_unit_of_consumption, String unit_of_consumption, double monthly_fixed, double monthly_ERT_cost) {
        super(id, description, usage, price_per_unit_of_consumption, unit_of_consumption, monthly_fixed);
        this.monthly_ERT_cost = monthly_ERT_cost;
    }

    public double getMonthly_ERT_cost() {
        return monthly_ERT_cost;
    }

    public void setMonthly_ERT_cost(double monthly_ERT_cost) {
        this.monthly_ERT_cost = monthly_ERT_cost;
    }

    public String toString() {
        return "Cost type " + ": " + "Energy" + "\n" + super.toString() + "Monthly ERT cost :" + monthly_ERT_cost + "\n";
    }


    @Override
    public double calculate_cost(Building building) {
        return this.getMonthly_fixed() + getMonthly_ERT_cost() + building.getZone_price() * building.getSquare_meters() + this.getPrice_per_unit_of_consumption() * this.getUsage(); //usage here is referenced to kwh spent
    }
}
