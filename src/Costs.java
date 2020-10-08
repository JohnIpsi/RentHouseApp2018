public abstract class Costs {
    private String id, description; // the variables that are common for all other classes of costs
    protected double price_per_square_meter;  // the variables that are common for all other classes of costs with fixed
    private double usage;
    private double price_per_unit_of_consumption, monthly_fixed; // the variables that are common for all other classes of costs with fixed and usage
    private String unit_of_consumption; // the variables that are common for all other classes of costs with fixed and usage

    public Costs() {
    }

    public Costs(String id, String description, double price_per_square_meter) {
        this.id = id;
        this.description = description;
        this.price_per_square_meter = price_per_square_meter;
    }

    public Costs(String id, String description, double usage, double price_per_unit_of_consumption, String unit_of_consumption, double monthly_fixed) {
        this.id = id;
        this.description = description;
        this.usage = usage;
        this.monthly_fixed = monthly_fixed;
        this.price_per_unit_of_consumption = price_per_unit_of_consumption;
        this.unit_of_consumption = unit_of_consumption;
    }

    public String getID() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getUsage() {
        return usage;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    public double getPrice_per_unit_of_consumption() {
        return price_per_unit_of_consumption;
    }

    public String getUnit_of_consumption() {
        return unit_of_consumption;
    }

    public double getMonthly_fixed() {
        return monthly_fixed;
    }

    public void setPrice_per_unit_of_consumption(double price_per_unit_of_consumption) {
        this.price_per_unit_of_consumption = price_per_unit_of_consumption;
    }
    public void setUnit_of_consumption(String unit_of_consumption) {
        this.unit_of_consumption = unit_of_consumption;
    }

    public void setMonthly_fixed(double monthly_fixed) {
        this.monthly_fixed = monthly_fixed;
    }

    public double getPricePerSquareMeter() {
        return this.price_per_square_meter;
    }

    public void setPrice_per_square_meter(double price_per_square_meter) {
        this.price_per_square_meter = price_per_square_meter;
    }
    public String toString() {
        return "id :" + id + "\n" + "Description : " + description + "\n" + "Consumption :" + usage + "\n" + "Price per unit of consumption :" + price_per_unit_of_consumption + "\n" + "Unit of consumption :" + unit_of_consumption + "\n" + "Monthly Fixed :" + monthly_fixed + "\n";
    }
}

