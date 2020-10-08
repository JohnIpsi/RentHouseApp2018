
public class Telephone extends Costs implements common_methods {
    private double monthly_telephone_cost;  //special characteristic of this specific cost

    public Telephone() {
    }

    public Telephone(String id, String description, double usage, double price_per_unit_of_consumption, String unit_of_consumption, double monthly_fixed, double monthly_telephone_cost) {
        super(id, description, usage, price_per_unit_of_consumption, unit_of_consumption, monthly_fixed);
        this.monthly_telephone_cost = monthly_telephone_cost;
    }

    public double getMonthly_telephone_cost() {
        return monthly_telephone_cost;
    }

    public void setMonthly_telephone_cost(double monthly_telephone_cost) {
        this.monthly_telephone_cost = monthly_telephone_cost;
    }

    public String toString() {
        return "Cost type " + ": " + "Telephone" + "\n" + super.toString() + "Monthly telephone cost:" + monthly_telephone_cost + "\n";
    }

    @Override
    public double calculate_cost(Building building) {
        return this.getMonthly_fixed() + monthly_telephone_cost + this.getUsage() * this.getPrice_per_unit_of_consumption(); //PRICE is price per unit of consumption
    }
}
