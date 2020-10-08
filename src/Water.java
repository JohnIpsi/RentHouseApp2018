
public class Water extends Costs implements common_methods {
    private double final_price = 0; //the chosen price depending on the cube meters consumption , can be either price per unit of consumption or price per cube meter
    private double price_per_cube_meter; //special characteristic of this specific cost

    public Water() {
    }

    public Water(String id, String description, double usage, double price_per_unit_of_consumption, String unit_of_consumption, double monthly_fixed, double price_per_cube_meter) {
        super(id, description, usage, price_per_unit_of_consumption, unit_of_consumption, monthly_fixed);
        this.price_per_cube_meter = price_per_cube_meter;
        if (this.getUsage() <= 100) {
            this.final_price = price_per_unit_of_consumption;     //under 100 cube meters consumption
        } else {
            this.final_price = price_per_cube_meter;              //over 100 cube meters consumption
        }
    }

    public double getPrice_per_cube_meter() {
        return price_per_cube_meter;
    }

    public void setPrice_per_cube_meter(double price_per_cube_meter){
        this.price_per_cube_meter=price_per_cube_meter;
    }


    public String toString() {
        return "Cost type " + ": " + "Water" + "\n" + super.toString() + "Price per cube meter (over 100 cube meters consumption price) :" + price_per_cube_meter + "\n";
    }

    @Override
    public double calculate_cost(Building building) {
        return this.getMonthly_fixed() + final_price * this.getUsage();
    }
}
