import java.io.*;

public class Building_Reader {
    private static int BuildingInstances = 0;
    private static int BuildingCostInstances = 0;
    private BuildingCollection StoreBuilding = new BuildingCollection();
    private BuildingCostCollection StoreBuildingCost = new BuildingCostCollection();
    private CostsCollection costs_collection = new CostsCollection();

    public void loadFile(String path) {
        int counter = 0;
        File f = null;
        BufferedReader reader = null;
        Building building = null;
        BuildingCost buildingCost = null;
        String line;
        boolean hasExpense=false;

        try {
            f = new File(path);
        } catch (NullPointerException e) {
            System.err.println("File not found.");
        }
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file!");
        }
        try {
            line = reader.readLine();
            counter++;
            if (line != null)
                if (line.trim().equalsIgnoreCase("BUILDING_LIST")) {
                    line = reader.readLine();
                    counter++;
                    if (line != null)
                        if (line.trim().equals("{")) {
                            line = reader.readLine();
                            counter++;
                            while (line != null) {
                                if (line.trim().equalsIgnoreCase("BUILDING")) {
                                    line = reader.readLine();
                                    counter++;
                                    if (line != null) {
                                        if (line.trim().equals("{")) {
                                            line = reader.readLine();
                                            counter++;
                                            if (line != null) {
                                                building = new Building();
                                                BuildingInstances++;
                                                try {
                                                    while (!line.trim().equals("}")) {                                                        if (line.trim().toUpperCase().startsWith("BUILDING_CODE ")) {
                                                            building.setBuilding_id(line.trim().substring(13).replaceAll("\"", "").replaceAll("\\W", ""));
                                                        } else if (line.trim().toUpperCase().startsWith("BUILDING_DESCR ")) {
                                                            building.setDescription(line.trim().substring(15).replaceAll("\"", ""));
                                                        } else if (line.trim().toUpperCase().startsWith("ADDRESS ")) {
                                                            building.setAddress(line.trim().substring(7).replaceAll("\"", ""));
                                                        } else if (line.trim().toUpperCase().startsWith("SQUARE_METERS ")) {
                                                            building.setSquare_meters(Integer.parseInt(line.trim().substring(13).replaceAll("\"", "").replaceAll("\\W", "")));
                                                        } else if (line.trim().toUpperCase().startsWith("ZONE_PRICE ")) {
                                                            building.setZone_price(Double.parseDouble(line.trim().substring(10).replaceAll("\"", "")));
                                                        }
                                                        line = reader.readLine();
                                                        counter++;
                                                        if (line != null) {
                                                            if (line.trim().equalsIgnoreCase("EXPENSES")) {
                                                                line = reader.readLine();
                                                                counter++;
                                                                if (line != null) {
                                                                    if (line.trim().toUpperCase().startsWith("{")) {
                                                                        line = reader.readLine();
                                                                        counter++;
                                                                        if (line != null) {
                                                                            while (!line.trim().equals("}")) {
                                                                                if (line.trim().equalsIgnoreCase("EXPENSE")) {
                                                                                    line = reader.readLine();
                                                                                    counter++;
                                                                                    if (line != null) {
                                                                                        if (line.trim().startsWith("{")) {
                                                                                            line = reader.readLine();
                                                                                            counter++;
                                                                                            if (line != null) {
                                                                                                BuildingCostInstances++;
                                                                                                while (!line.trim().equals("}")) {                                                                                                   String id = null;
                                                                                                    Costs costs1 = null;
                                                                                                    double consumption = 0;
                                                                                                    if (line.trim().substring(0, 4).equalsIgnoreCase("TYPE")) {
                                                                                                        if (line.trim().substring(4).trim().replaceAll("\"", "").equalsIgnoreCase("Water")) {
                                                                                                            line = reader.readLine();
                                                                                                            counter++;
                                                                                                            if (line.trim().toUpperCase().startsWith("EXPENSE_TYPE_CODE")) {
                                                                                                                id = line.trim().substring(17).replaceAll("\"", "").replaceAll("\\W", "");
                                                                                                                for (Costs b : costs_collection.getCollection()) {
                                                                                                                    if (b.getID().equals(id)) {
                                                                                                                        costs1 = b;
                                                                                                                    }
                                                                                                                }
                                                                                                                line = reader.readLine();
                                                                                                                counter++;
                                                                                                            }
                                                                                                            if (line.trim().toUpperCase().startsWith("CONSUMPTION")) {
                                                                                                                consumption = Double.parseDouble(line.trim().substring(11).replaceAll("\"", ""));
                                                                                                                costs1.setUsage(consumption); //sending consumption to costs objects
                                                                                                            }
                                                                                                            buildingCost = new BuildingCost(building, costs1, consumption);
                                                                                                            StoreBuildingCost.insert(buildingCost);
                                                                                                        } else if (line.trim().substring(4).trim().replaceAll("\"", "").equalsIgnoreCase("Cleaning")) {
                                                                                                            line = reader.readLine();
                                                                                                            counter++;
                                                                                                            if (line.trim().toUpperCase().startsWith("EXPENSE_TYPE_CODE")) {
                                                                                                                id = line.trim().substring(17).replaceAll("\"", "").replaceAll("\\W", "");
                                                                                                                for (Costs b : costs_collection.getCollection()) {
                                                                                                                    if (b.getID().equals(id)) {
                                                                                                                        costs1 = b;
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                            buildingCost = new BuildingCost(building, costs1, 0);
                                                                                                            StoreBuildingCost.insert(buildingCost);
                                                                                                        } else if (line.trim().substring(4).trim().replaceAll("\"", "").equalsIgnoreCase("Telephone")) {
                                                                                                            line = reader.readLine();
                                                                                                            counter++;
                                                                                                            if (line.trim().toUpperCase().startsWith("EXPENSE_TYPE_CODE")) {
                                                                                                                id = line.trim().substring(17).replaceAll("\"", "").replaceAll("\\W", "");
                                                                                                                for (Costs b : costs_collection.getCollection()) {
                                                                                                                    if (b.getID().equals(id)) {
                                                                                                                        costs1 = b;
                                                                                                                    }
                                                                                                                }
                                                                                                                line = reader.readLine();
                                                                                                                counter++;
                                                                                                            }
                                                                                                            if (line.trim().toUpperCase().startsWith("CONSUMPTION")) {
                                                                                                                consumption = Double.parseDouble(line.trim().substring(11).replaceAll("\"", ""));
                                                                                                                costs1.setUsage(consumption); //sending consumption to costs objects
                                                                                                            }

                                                                                                            buildingCost = new BuildingCost(building, costs1, consumption);
                                                                                                            StoreBuildingCost.insert(buildingCost);
                                                                                                        } else if (line.trim().substring(4).trim().replaceAll("\"", "").equalsIgnoreCase("Rent")) {
                                                                                                            line = reader.readLine();
                                                                                                            counter++;
                                                                                                            if (line.trim().toUpperCase().startsWith("EXPENSE_TYPE_CODE")) {
                                                                                                                id = line.trim().substring(17).replaceAll("\"", "").replaceAll("\\W", "");
                                                                                                                for (Costs b : costs_collection.getCollection()) {
                                                                                                                    if (b.getID().equals(id)) {
                                                                                                                        costs1 = b;
                                                                                                                    }
                                                                                                                }
                                                                                                                line = reader.readLine();
                                                                                                                counter++;
                                                                                                            }
                                                                                                            buildingCost = new BuildingCost(building, costs1, 0);
                                                                                                            StoreBuildingCost.insert(buildingCost);
                                                                                                        } else if (line.trim().substring(4).trim().replaceAll("\"", "").equalsIgnoreCase("Energy")) {
                                                                                                            line = reader.readLine();
                                                                                                            counter++;
                                                                                                            if (line.trim().toUpperCase().startsWith("EXPENSE_TYPE_CODE")) {
                                                                                                                id = line.trim().substring(17).replaceAll("\"", "").replaceAll("\\W", "");
                                                                                                                for (Costs b : costs_collection.getCollection()) {
                                                                                                                    if (b.getID().equals(id)) {
                                                                                                                        costs1 = b;

                                                                                                                    }
                                                                                                                }
                                                                                                                line = reader.readLine();
                                                                                                                counter++;
                                                                                                            }

                                                                                                            if (line.trim().toUpperCase().startsWith("CONSUMPTION")) {
                                                                                                                consumption = Double.parseDouble(line.trim().substring(11).replaceAll("\"", ""));
                                                                                                                costs1.setUsage(consumption); //sending consumption to costs objects
                                                                                                            }
                                                                                                            buildingCost = new BuildingCost(building, costs1, consumption);
                                                                                                            StoreBuildingCost.insert(buildingCost);

                                                                                                        }
                                                                                                    }
                                                                                                    line = reader.readLine();
                                                                                                    counter++;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }//if expense
                                                                                line = reader.readLine();
                                                                                counter++;
                                                                            }//while}
                                                                            line = reader.readLine();
                                                                            counter++;
                                                                        }//if null
                                                                    }
                                                                }//if null
                                                           }

                                                            //if expenses
                                                        }//if null
                                                    }//while
                                                } catch (NullPointerException o) {
                                                    System.out.println(line.trim());
                                                    System.out.println("Line " + counter + ": Sudden end.");
                                                }
                                                StoreBuilding.insert(building);
                                                if(hasExpense){
                                                }else{
                                                    buildingCost=new BuildingCost(building,null,0);
                                                    StoreBuildingCost.insert(buildingCost);
                                                }
                                            }
                                        }
                                    }//if
                                }
                                line = reader.readLine();
                                counter++;
                            }//while
                        }
                }
        } catch (IOException | NullPointerException e) {
            System.out.println("Line " + counter + ": Sudden end.");
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
    }

    public int getBuildingInstances() {
        return BuildingInstances;
    }

    public int getBuildingCostInstances() {
        return BuildingCostInstances;
    }

    public BuildingCollection getBuildingCollection() {
        return StoreBuilding;
    }

    public BuildingCostCollection getBuildingCostCollection() {
        return StoreBuildingCost;
    }

    public CostsCollection getCostCollection() {
        return costs_collection;
    }

    public void setCollection(CostsCollection costCollection) {
        this.costs_collection = costCollection;
    }

}