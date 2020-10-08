import java.io.*;

public class Costs_Reader {
    private static int WaterInstances = 0;
    private static int RentInstances = 0;
    private static int CleaningInstances = 0;
    private static int EnergyInstances = 0;
    private static int TelephoneInstances = 0;
    private CostsCollection StoreCosts = new CostsCollection();

    public void loadFile(String path) {
        int counter = 0;
        File f = null;
        BufferedReader reader = null;
        Costs cost = null;
        String line;
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
                if (line.trim().equalsIgnoreCase("EXPENSE_TYPE_LIST")) {
                    line = reader.readLine();
                    counter++;
                    if (line != null)
                        if (line.trim().equals("{")) {
                            line = reader.readLine();
                            counter++;
                            while (line != null) {
                                if (line.trim().equalsIgnoreCase("EXPENSE_TYPE")) {
                                    line = reader.readLine();
                                    counter++;
                                    if (line != null) {
                                        if (line.trim().equals("{")) {
                                            line = reader.readLine();
                                            counter++;
                                            if (line != null)
                                                if (line.trim().substring(0, 4).toUpperCase().startsWith("TYPE")) {
                                                    if (line.trim().substring(4).trim().replaceAll("\"", "").equalsIgnoreCase("Water")) {
                                                        cost = new Water();
                                                        WaterInstances++;
                                                        line = reader.readLine();
                                                        counter++;
                                                        while (!line.trim().equals("}")) {
                                                            if (line.trim().toUpperCase().startsWith("CODE ")) {
                                                                cost.setID(line.trim().substring(4).replaceAll("\"", "").replaceAll("\\W", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("EXPENSE_TYPE_DECSR ")) {
                                                                cost.setDescription(line.trim().substring(18).replaceAll("\"", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("MEASUREMENT_UNIT ")) {
                                                                cost.setUnit_of_consumption(line.trim().substring(16).replaceAll("\"", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("PRICE_PER_MEASUREMENT_UNIT ")) {
                                                                cost.setPrice_per_unit_of_consumption(Double.parseDouble(line.trim().substring(26).replaceAll("\"", "")));
                                                            } else if (line.trim().toUpperCase().startsWith("MONTHLY_FIXED ")) {
                                                                cost.setMonthly_fixed(Double.parseDouble(line.trim().substring(13).replaceAll("\"", "")));
                                                            } else if (line.trim().toUpperCase().startsWith("PRICE_PER_CUBE_METER ")) {
                                                                ((Water) cost).setPrice_per_cube_meter(Double.parseDouble(line.trim().substring(20).replaceAll("\"", "")));
                                                            }
                                                            line = reader.readLine();
                                                            counter++;
                                                        }
                                                        StoreCosts.insert(cost);
                                                    }//rent
                                                    else if (line.trim().substring(4).trim().replaceAll("\"", "").equalsIgnoreCase("Rent")) {
                                                        cost = new Rent();
                                                        RentInstances++;
                                                        line = reader.readLine();
                                                        counter++;
                                                        while (!line.trim().equals("}")) {
                                                            if (line.trim().toUpperCase().startsWith("CODE")) {
                                                                cost.setID(line.trim().substring(4).replaceAll("\"", "").replaceAll("\\W", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("EXPENSE_TYPE_DECSR ")) {
                                                                cost.setDescription(line.trim().substring(18).replaceAll("\"", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("PRICE_PER_SQUARE_METER ")) {
                                                                cost.setPrice_per_square_meter(Integer.parseInt(line.trim().substring(22).replaceAll("\"", "").replaceAll("\\W", "")));
                                                            }
                                                            line = reader.readLine();
                                                            counter++;
                                                        }
                                                        StoreCosts.insert(cost);
                                                    } //Cleaning
                                                    else if (line.trim().substring(4).trim().replaceAll("\"", "").equalsIgnoreCase("Cleaning")) {
                                                        cost = new Cleaning();
                                                        CleaningInstances++;
                                                        line = reader.readLine();
                                                        counter++;
                                                        while (!line.trim().equals("}")) {
                                                            if (line.trim().toUpperCase().startsWith("CODE")) {
                                                                cost.setID(line.trim().substring(4).replaceAll("\"", "").replaceAll("\\W", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("EXPENSE_TYPE_DECSR ")) {
                                                                cost.setDescription(line.trim().substring(18).replaceAll("\"", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("PRICE_PER_SQUARE_METER ")) {
                                                                cost.setPrice_per_square_meter(Integer.parseInt(line.trim().substring(22).replaceAll("\"", "").replaceAll("\\W", "")));
                                                            }
                                                            line = reader.readLine();
                                                            counter++;
                                                        }
                                                        StoreCosts.insert(cost);
                                                    }//Energy
                                                    else if (line.trim().substring(6).replaceAll("\"", "").equalsIgnoreCase("Energy")) {
                                                        cost = new Energy();
                                                        EnergyInstances++;
                                                        line = reader.readLine();
                                                        counter++;
                                                        while (!line.trim().equals("}")) {
                                                            if (line.trim().toUpperCase().startsWith("CODE ")) {
                                                                cost.setID(line.trim().substring(4).replaceAll("\"", "").replaceAll("\\W", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("EXPENSE_TYPE_DECSR ")) {
                                                                cost.setDescription(line.trim().substring(18).replaceAll("\"", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("MEASUREMENT_UNIT ")) {
                                                                cost.setUnit_of_consumption(line.trim().substring(16).replaceAll("\"", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("PRICE_PER_MEASUREMENT_UNIT ")) {
                                                                cost.setPrice_per_unit_of_consumption(Double.parseDouble(line.trim().substring(26).replaceAll("\"", "")));
                                                            } else if (line.trim().toUpperCase().startsWith("MONTHLY_FIXED ")) {
                                                                cost.setMonthly_fixed(Double.parseDouble(line.trim().substring(13).replaceAll("\"", "")));
                                                            } else if (line.trim().toUpperCase().startsWith("MONTHLY_ERT_COST ")) {
                                                                ((Energy) cost).setMonthly_ERT_cost(Double.parseDouble(line.trim().substring(16).replaceAll("\"", "")));
                                                            }
                                                            line = reader.readLine();
                                                            counter++;
                                                        }
                                                        StoreCosts.insert(cost);
                                                    }//telephone
                                                    else if (line.trim().substring(4).trim().replaceAll("\"", "").equalsIgnoreCase("Telephone")) {
                                                        cost = new Telephone();
                                                        TelephoneInstances++;
                                                        line = reader.readLine();
                                                        counter++;
                                                        while (!line.trim().equals("}")) {
                                                            if (line.trim().toUpperCase().startsWith("CODE ")) {
                                                                cost.setID(line.trim().substring(4).replaceAll("\"", "").replaceAll("\\W", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("EXPENSE_TYPE_DECSR ")) {
                                                                cost.setDescription(line.trim().substring(18).replaceAll("\"", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("MEASUREMENT_UNIT ")) {
                                                                cost.setUnit_of_consumption(line.trim().substring(16).replaceAll("\"", ""));
                                                            } else if (line.trim().toUpperCase().startsWith("PRICE_PER_MEASUREMENT_UNIT ")) {
                                                                cost.setPrice_per_unit_of_consumption(Double.parseDouble(line.trim().substring(26).replaceAll("\"", "")));
                                                            } else if (line.trim().toUpperCase().startsWith("MONTHLY_FIXED ")) {
                                                                cost.setMonthly_fixed(Double.parseDouble(line.trim().substring(13).replaceAll("\"", "")));
                                                            } else if (line.trim().toUpperCase().startsWith("MONTHLY_ERT_COST ")) {
                                                                ((Telephone) cost).setMonthly_telephone_cost(Double.parseDouble(line.trim().substring(17).replaceAll("\"", "")));
                                                            }
                                                            line = reader.readLine();
                                                            counter++;
                                                        }
                                                        StoreCosts.insert(cost);
                                                    }
                                                }
                                        }//if
                                    }
                                }
                                line = reader.readLine();
                                counter++;
                            }
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

    public int getWaterInstances() {
        return WaterInstances;
    }

    public int getRentInstances() {
        return RentInstances;
    }

    public int getCleaningInstances() {
        return CleaningInstances;
    }

    public int getEnergyInstances() {
        return EnergyInstances;
    }

    public int getTelephoneInstances() {
        return TelephoneInstances;
    }


    public CostsCollection getCostsCollection() {
        return StoreCosts;
    }

}

