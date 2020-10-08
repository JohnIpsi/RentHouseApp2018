import java.io.*;


public class Building_write {

    private BuildingCostCollection StoreBuildingsAndCosts = new BuildingCostCollection();

    public void saveFile(String path) {

        File f = null;
        BufferedWriter writer = null;

        try {
            f = new File(path);
        } catch (NullPointerException e) {
            System.err.println("File not found.");
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f.getName(), true)));
        } catch (FileNotFoundException | NullPointerException e) {
            System.err.println("Error opening file for writing!");

        }
        try {
            for (BuildingCost buildingcost : StoreBuildingsAndCosts.getBCollection()) {  //here we access the BuildingCostCollection , and getting info to write them
                writer.write("BUILDING" + "\n" + "{" + "\n \t" + "BUILDING_CODE " + buildingcost.getBuilding().getBuilding_id() + "\n \t" + "BUILDING_DESCR " + buildingcost.getBuilding().getDescription() + "\n \t" + "ADDRESS " + buildingcost.getBuilding().getAddress() + "\n \t" + "SQUARE_METERS " + buildingcost.getBuilding().getSquare_meters() + "\n \t" + "ZONE_PRICE " + buildingcost.getBuilding().getZone_price() + "\n \t" + "EXPENSES " + "\n \t");
                //look if the specific building has any expenses check with if
                if (buildingcost.getCost_type() != null) {
                    writer.write("{" + "\n \t \t" + "EXPENSE" + "\n \t \t" + "{" + "\n \t \t" + "TYPE ");
                    if (buildingcost.getCost_type() instanceof Water) {
                        writer.write("Water" + "\n \t \t" + "EXPENSE_TYPE_CODE " + buildingcost.getCost_type().getID() + "\n \t \t" + "CONSUMPTION " + buildingcost.getConsumption() + "\n \t \t");
                    } else if (buildingcost.getCost_type() instanceof Rent) {
                        writer.write("Rent" + "\n \t \t" + "EXPENSE_TYPE_CODE " + buildingcost.getCost_type().getID() + "\n \t \t");
                    } else if (buildingcost.getCost_type() instanceof Cleaning) {
                        writer.write("Cleaning" + "\n \t \t" + "EXPENSE_TYPE_CODE " + buildingcost.getCost_type().getID() + "\n \t \t");
                    } else if (buildingcost.getCost_type() instanceof Energy) {
                        writer.write("Energy" + "\n \t \t" + "EXPENSE_TYPE_CODE " + buildingcost.getCost_type().getID() + "\n \t \t" + "CONSUMPTION " + buildingcost.getConsumption() + "\n \t \t");
                    } else if (buildingcost.getCost_type() instanceof Telephone) {
                        writer.write("Telephone" + "\n \t \t" + "EXPENSE_TYPE_CODE " + buildingcost.getCost_type().getID() + "\n \t \t" + "CONSUMPTION " + buildingcost.getConsumption() + "\n \t \t");
                    }
                    writer.write("}" + "\n");
                } else {
                    writer.write("{" + "\n \t");
                    writer.write("}" + "\n");
                }
                writer.write("}" + "\n");
            }
        } catch (IOException | NullPointerException e) {
            System.err.println("Error writing file.");
        }

        try {
            writer.close();
        } catch (IOException | NullPointerException e) {
            System.err.println("Error closing file.");
        }
    }

    public BuildingCostCollection getCostCollection() {
        return StoreBuildingsAndCosts;
    }

    public void setCollection(BuildingCostCollection buildingcostCollection) {
        this.StoreBuildingsAndCosts = buildingcostCollection;
    }
}



