
public class Cleaning extends Costs implements common_methods {

    public Cleaning() {
    }

    public Cleaning(String id, String description, double price_per_square_meter) {
        super(id, description, price_per_square_meter);
    }

    public String toString() {
        return "Cost type " + ": " + "Cleaning" + "\n" + super.toString() + "Price per square meter " + this.price_per_square_meter + "\n";
    }


    @Override
    public double calculate_cost(Building building) {
        return this.price_per_square_meter * building.getSquare_meters();
    }
}
