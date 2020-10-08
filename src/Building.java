
public class Building {
    private String building_id, description, address;
    private double zone_price;
    private int square_meters;

    public Building() {
    }

    public Building(String building_id, String description, String address, double zone_price, int square_meters) {
        this.building_id = building_id;
        this.description = description;
        this.address = address;
        this.zone_price = zone_price;
        this.square_meters = square_meters;
    }

    public void setBuilding_id(String building_id) {
        this.building_id = building_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZone_price(double zone_price) {
        this.zone_price = zone_price;
    }

    public void setSquare_meters(int square_meters) {
        this.square_meters = square_meters;
    }

    public String getBuilding_id() {
        return building_id;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public double getZone_price() {
        return zone_price;
    }

    public int getSquare_meters() {
        return square_meters;
    }

    public String toString() {
        return "\u001B[32m" + "Building " + ":" + building_id + "\u001B[0m" + "\n" + "Description : " + description + "\n" + "Address :" + address + "\n" + "Zone Price :" + zone_price + "\n" + "Square Meters :" + square_meters;
    }
}
