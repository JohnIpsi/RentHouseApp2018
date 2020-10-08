import java.io.*;

public class Costs_writer {

    private CostsCollection StoreCostsToPrint = new CostsCollection();

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
            writer.write("EXPENSE_TYPE_LIST" + "\n" + "{" + "\n");
            for (Costs costs : StoreCostsToPrint.getCollection()) {
                if (costs instanceof Water) {
                    writer.write("EXPENSE_TYPE" + "\n" + "{" + "\n \t" + "TYPE " + "Water" + "\n \t" + "CODE " + costs.getID() + "\n \t" + "EXPENSE_TYPE_DECSR " + costs.getDescription() + "\n \t" + "MEASUREMENT_UNIT " + costs.getUnit_of_consumption() + "\n \t" + "PRICE_PER_MEASUREMENT_UNIT" + costs.getPrice_per_unit_of_consumption() + "\n \t" + "MONTHLY_FIXED " + costs.getMonthly_fixed() + "\n \t" + "PRICE_PER_CUBE_METER " + ((Water) costs).getPrice_per_cube_meter() + "\n" + "}" + "\n");
                } else if (costs instanceof Rent) {
                    writer.write("EXPENSE_TYPE" + "\n" + "{" + "\n \t" + "TYPE " + "Rent" + "\n \t" + "CODE " + costs.getID() + "\n \t" + "EXPENSE_TYPE_DECSR " + costs.getDescription() + "\n \t" + "PRICE_PER_SQUARE_METER " + costs.getPricePerSquareMeter() + "\n" + "}" + "\n");
                } else if (costs instanceof Energy) {
                    writer.write("EXPENSE_TYPE" + "\n" + "{" + "\n \t" + "TYPE " + "Energy" + "\n \t" + "CODE " + costs.getID() + "\n \t" + "EXPENSE_TYPE_DECSR " + costs.getDescription() + "\n \t" + "MEASUREMENT_UNIT " + costs.getUnit_of_consumption() + "\n \t" + "PRICE_PER_MEASUREMENT_UNIT" + costs.getPrice_per_unit_of_consumption() + "\n \t" + "MONTHLY_FIXED " + costs.getMonthly_fixed() + "\n \t" + "MONTHLY_ERT_COST " + ((Energy) costs).getMonthly_ERT_cost() + "\n" + "}" + "\n");
                } else if (costs instanceof Telephone) {
                    writer.write("EXPENSE_TYPE" + "\n" + "{" + "\n \t" + "TYPE " + "Telephone" + "\n \t" + "CODE " + costs.getID() + "\n \t" + "EXPENSE_TYPE_DECSR " + costs.getDescription() + "\n \t" + "MEASUREMENT_UNIT " + costs.getUnit_of_consumption() + "\n \t" + "PRICE_PER_MEASUREMENT_UNIT" + costs.getPrice_per_unit_of_consumption() + "\n \t" + "MONTHLY_FIXED " + costs.getMonthly_fixed() + "\n \t" + "MONTHLY_TELEPHONE_COST " + ((Telephone) costs).getMonthly_telephone_cost() + "\n" + "}" + "\n");
                } else if (costs instanceof Cleaning) {
                    writer.write("EXPENSE_TYPE" + "\n" + "{" + "\n \t" + "TYPE " + "Cleaning" + "\n \t" + "CODE " + costs.getID() + "\n \t" + "EXPENSE_TYPE_DECSR " + costs.getDescription() + "\n \t" + "PRICE_PER_SQUARE_METER " + costs.getPricePerSquareMeter() + "\n" + "}" + "\n");
                }
            }
            writer.write("}" + "\n");
        } catch (IOException | NullPointerException e) {
            System.err.println("Error writing file.");
        }

        try {
            writer.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }

    }

    public CostsCollection getCostCollection() {
        return StoreCostsToPrint;
    }

    public void setCollection(CostsCollection costCollection) {
        this.StoreCostsToPrint = costCollection;
    }
}



