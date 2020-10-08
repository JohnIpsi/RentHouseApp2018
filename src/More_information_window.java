import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class More_information_window extends JFrame{

    public More_information_window(String costType, Costs_Reader costs_collection, String id, Building_Reader buildingCost_collection) {
        Container containerMain = getContentPane();   //creating the new container window
        containerMain.setLayout(new FlowLayout());   //specify how to display elements in this window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("More Information");
        setBounds(600, 200, 230, 500);
        setVisible(true);

        if (costType.equals("Rent")) {
            JTextArea type_text = new JTextArea("Type: ");
            JTextArea code_text = new JTextArea("Code: ");
            JTextArea description_text = new JTextArea("Description: ");
            JTextArea price_per_square_meter_text = new JTextArea("Price per square meter: ");

            JTextArea actual_type_text = new JTextArea(costType);
            JTextArea actual_code_text = new JTextArea(id);
            JTextArea actual_description_text = new JTextArea();
            JTextArea actual_price_per_square_meter_text = new JTextArea();

            for (Costs p : costs_collection.getCostsCollection().getCollection()) {
                if (p instanceof Rent) {
                    actual_description_text = new JTextArea(p.getDescription());
                    actual_price_per_square_meter_text = new JTextArea(String.valueOf(p.getPricePerSquareMeter()));
                }
            }

            actual_type_text.setEditable(false);
            actual_code_text.setEditable(false);
            actual_description_text.setEditable(false);
            actual_price_per_square_meter_text.setEditable(false);
            type_text.setEditable(false);
            code_text.setEditable(false);
            description_text.setEditable(false);
            price_per_square_meter_text.setEditable(false);

            type_text.setFont(new Font("Serif", Font.BOLD, 18));
            code_text.setFont(new Font("Serif", Font.BOLD, 18));
            description_text.setFont(new Font("Serif", Font.BOLD, 18));
            price_per_square_meter_text.setFont(new Font("Serif", Font.BOLD, 18));
            actual_type_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_code_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_description_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_price_per_square_meter_text.setFont(new Font("Serif", Font.PLAIN, 18));

            containerMain.add(type_text);
            containerMain.add(actual_type_text);
            containerMain.add(code_text);
            containerMain.add(actual_code_text);
            containerMain.add(description_text);
            containerMain.add(actual_description_text);
            containerMain.add(price_per_square_meter_text);
            containerMain.add(actual_price_per_square_meter_text);

            JButton calculateBtn = new JButton("Calculate total cost");  //creating calculation total cost button object
            containerMain.add(calculateBtn);   //adding it to the window-container

            calculateBtn.addActionListener(new ActionListener() {   //what to do when this button is pressed
                @Override
                public void actionPerformed(ActionEvent e) {
                    double sum_of_expense=0;
                    for (Costs d : costs_collection.getCostsCollection().getCollection()) {
                        for (BuildingCost u : buildingCost_collection.getBuildingCostCollection().getBCollection()) {
                            if (d instanceof Rent) {
                                sum_of_expense += ((Rent) d).calculate_cost(u.getBuilding());

                            }
                        }
                    }infoBox(String.valueOf(sum_of_expense), "Calculation Result");
                }
            });

        } else if (costType.equals("Cleaning")) {
            JTextArea type_text = new JTextArea("Type: ");
            JTextArea code_text = new JTextArea("Code: ");
            JTextArea description_text = new JTextArea("Description: ");
            JTextArea price_per_square_meter_text = new JTextArea("Price per square meter: ");

            JTextArea actual_type_text = new JTextArea(costType);
            JTextArea actual_code_text = new JTextArea(id);
            JTextArea actual_description_text = new JTextArea();
            JTextArea actual_price_per_square_meter_text = new JTextArea();

            for (Costs p : costs_collection.getCostsCollection().getCollection()) {
                if (p instanceof Cleaning) {
                    actual_description_text = new JTextArea(p.getDescription());
                    actual_price_per_square_meter_text = new JTextArea(String.valueOf(p.getPricePerSquareMeter()));
                }
            }

            actual_type_text.setEditable(false);
            actual_code_text.setEditable(false);
            actual_description_text.setEditable(false);
            actual_price_per_square_meter_text.setEditable(false);
            type_text.setEditable(false);
            code_text.setEditable(false);
            description_text.setEditable(false);
            price_per_square_meter_text.setEditable(false);

            type_text.setFont(new Font("Serif", Font.BOLD, 18));
            code_text.setFont(new Font("Serif", Font.BOLD, 18));
            description_text.setFont(new Font("Serif", Font.BOLD, 18));
            price_per_square_meter_text.setFont(new Font("Serif", Font.BOLD, 18));
            actual_type_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_code_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_description_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_price_per_square_meter_text.setFont(new Font("Serif", Font.PLAIN, 18));

            containerMain.add(type_text);
            containerMain.add(actual_type_text);
            containerMain.add(code_text);
            containerMain.add(actual_code_text);
            containerMain.add(description_text);
            containerMain.add(actual_description_text);
            containerMain.add(price_per_square_meter_text);
            containerMain.add(actual_price_per_square_meter_text);

            JButton calculateBtn = new JButton("Calculate total cost");  //creating calculation total cost button object
            containerMain.add(calculateBtn);   //adding it to the window-container

            calculateBtn.addActionListener(new ActionListener() {   //what to do when this button is pressed
                @Override
                public void actionPerformed(ActionEvent e) {
                    double sum_of_expense=0;
                    for (Costs d : costs_collection.getCostsCollection().getCollection()) {
                        for (BuildingCost u : buildingCost_collection.getBuildingCostCollection().getBCollection()) {
                            if (d instanceof Cleaning) {
                                sum_of_expense += ((Cleaning) d).calculate_cost(u.getBuilding());

                            }
                        }
                    }infoBox(String.valueOf(sum_of_expense), "Calculation Result");
                }
            });

        } else if (costType.equals("Water")) {
            JTextArea type_text = new JTextArea("Type: ");
            JTextArea code_text = new JTextArea("Code: ");
            JTextArea description_text = new JTextArea("Description: ");
            JTextArea measurement_unit_text = new JTextArea("Measurement Unit: ");
            JTextArea price_per_measurement_unit_text = new JTextArea("Price per measurement unit: ");
            JTextArea monthly_fixed_text = new JTextArea("Monthly Fixed: ");
            JTextArea price_per_cube_meter_text = new JTextArea("Price per cube meter: ");

            JTextArea actual_type_text = new JTextArea(costType);
            JTextArea actual_code_text = new JTextArea(id);
            JTextArea actual_description_text = new JTextArea();
            JTextArea actual_measurement_unit_text = new JTextArea();
            JTextArea actual_price_per_measurement_unit_text = new JTextArea();
            JTextArea actual_monthly_fixed_text = new JTextArea();
            JTextArea actual_price_per_cube_meter_text = new JTextArea();

            for (Costs p : costs_collection.getCostsCollection().getCollection()) {
                if (p instanceof Water) {
                    actual_description_text = new JTextArea(p.getDescription());
                    actual_measurement_unit_text = new JTextArea(p.getUnit_of_consumption());
                    actual_price_per_measurement_unit_text = new JTextArea(String.valueOf(p.getPrice_per_unit_of_consumption()));
                    actual_monthly_fixed_text = new JTextArea(String.valueOf(p.getMonthly_fixed()));
                    actual_price_per_cube_meter_text = new JTextArea(String.valueOf((((Water) p).getPrice_per_cube_meter())));
                }
            }

            actual_type_text.setEditable(false);
            actual_code_text.setEditable(false);
            actual_description_text.setEditable(false);
            actual_measurement_unit_text.setEditable(false);
            actual_price_per_measurement_unit_text.setEditable(false);
            actual_monthly_fixed_text.setEditable(false);
            actual_price_per_cube_meter_text.setEditable(false);

            type_text.setEditable(false);
            code_text.setEditable(false);
            description_text.setEditable(false);
            measurement_unit_text.setEditable(false);
            price_per_measurement_unit_text.setEditable(false);
            monthly_fixed_text.setEditable(false);
            price_per_cube_meter_text.setEditable(false);

            type_text.setFont(new Font("Serif", Font.BOLD, 18));
            code_text.setFont(new Font("Serif", Font.BOLD, 18));
            description_text.setFont(new Font("Serif", Font.BOLD, 18));
            measurement_unit_text.setFont(new Font("Serif", Font.BOLD, 18));
            price_per_measurement_unit_text.setFont(new Font("Serif", Font.BOLD, 18));
            monthly_fixed_text.setFont(new Font("Serif", Font.BOLD, 18));
            price_per_cube_meter_text.setFont(new Font("Serif", Font.BOLD, 18));

            actual_type_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_code_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_description_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_measurement_unit_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_price_per_measurement_unit_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_monthly_fixed_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_price_per_cube_meter_text.setFont(new Font("Serif", Font.PLAIN, 18));

            containerMain.add(type_text);
            containerMain.add(actual_type_text);
            containerMain.add(code_text);
            containerMain.add(actual_code_text);
            containerMain.add(description_text);
            containerMain.add(actual_description_text);
            containerMain.add(measurement_unit_text);
            containerMain.add(actual_measurement_unit_text);
            containerMain.add(price_per_measurement_unit_text);
            containerMain.add(actual_price_per_measurement_unit_text);
            containerMain.add(monthly_fixed_text);
            containerMain.add(actual_monthly_fixed_text);
            containerMain.add(price_per_cube_meter_text);
            containerMain.add(actual_price_per_cube_meter_text);

            JButton calculateBtn = new JButton("Calculate total cost");  //creating calculation total cost button object
            containerMain.add(calculateBtn);   //adding it to the window-container

            calculateBtn.addActionListener(new ActionListener() {   //what to do when this button is pressed
                @Override
                public void actionPerformed(ActionEvent e) {
                    double sum_of_expense=0;
                    for (Costs d : costs_collection.getCostsCollection().getCollection()) {
                        for (BuildingCost u : buildingCost_collection.getBuildingCostCollection().getBCollection()) {
                            if (d instanceof Water) {
                                sum_of_expense += ((Water) d).calculate_cost(u.getBuilding());

                            }
                        }
                    } infoBox(String.valueOf(sum_of_expense), "Calculation Result");
                }
            });


        } else if (costType.equals("Energy")) {
            JTextArea type_text = new JTextArea("Type: ");
            JTextArea code_text = new JTextArea("Code: ");
            JTextArea description_text = new JTextArea("Description: ");
            JTextArea measurement_unit_text = new JTextArea("Measurement Unit: ");
            JTextArea price_per_measurement_unit_text = new JTextArea("Price per measurement unit: ");
            JTextArea monthly_fixed_text = new JTextArea("Monthly Fixed: ");
            JTextArea monthly_ert_cost_text = new JTextArea("Monthly ERT cost: ");

            JTextArea actual_type_text = new JTextArea(costType);
            JTextArea actual_code_text = new JTextArea(id);
            JTextArea actual_description_text = new JTextArea();
            JTextArea actual_measurement_unit_text = new JTextArea();
            JTextArea actual_price_per_measurement_unit_text = new JTextArea();
            JTextArea actual_monthly_fixed_text = new JTextArea();
            JTextArea actual_monthly_ert_cost_text = new JTextArea();

            for (Costs p : costs_collection.getCostsCollection().getCollection()) {
                if (p instanceof Energy) {
                    actual_description_text = new JTextArea(p.getDescription());
                    actual_measurement_unit_text = new JTextArea(p.getUnit_of_consumption());
                    actual_price_per_measurement_unit_text = new JTextArea(String.valueOf(p.getPrice_per_unit_of_consumption()));
                    actual_monthly_fixed_text = new JTextArea(String.valueOf(p.getMonthly_fixed()));
                    actual_monthly_ert_cost_text = new JTextArea(String.valueOf(((Energy) p).getMonthly_ERT_cost()));
                }
            }

            actual_type_text.setEditable(false);
            actual_code_text.setEditable(false);
            actual_description_text.setEditable(false);
            actual_measurement_unit_text.setEditable(false);
            actual_price_per_measurement_unit_text.setEditable(false);
            actual_monthly_fixed_text.setEditable(false);
            actual_monthly_ert_cost_text.setEditable(false);

            type_text.setEditable(false);
            code_text.setEditable(false);
            description_text.setEditable(false);
            measurement_unit_text.setEditable(false);
            price_per_measurement_unit_text.setEditable(false);
            monthly_fixed_text.setEditable(false);
            monthly_ert_cost_text.setEditable(false);

            type_text.setFont(new Font("Serif", Font.BOLD, 18));
            code_text.setFont(new Font("Serif", Font.BOLD, 18));
            description_text.setFont(new Font("Serif", Font.BOLD, 18));
            measurement_unit_text.setFont(new Font("Serif", Font.BOLD, 18));
            price_per_measurement_unit_text.setFont(new Font("Serif", Font.BOLD, 18));
            monthly_fixed_text.setFont(new Font("Serif", Font.BOLD, 18));
            monthly_ert_cost_text.setFont(new Font("Serif", Font.BOLD, 18));

            actual_type_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_code_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_description_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_measurement_unit_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_price_per_measurement_unit_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_monthly_fixed_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_monthly_ert_cost_text.setFont(new Font("Serif", Font.PLAIN, 18));

            containerMain.add(type_text);
            containerMain.add(actual_type_text);
            containerMain.add(code_text);
            containerMain.add(actual_code_text);
            containerMain.add(description_text);
            containerMain.add(actual_description_text);
            containerMain.add(measurement_unit_text);
            containerMain.add(actual_measurement_unit_text);
            containerMain.add(price_per_measurement_unit_text);
            containerMain.add(actual_price_per_measurement_unit_text);
            containerMain.add(monthly_fixed_text);
            containerMain.add(actual_monthly_fixed_text);
            containerMain.add(monthly_ert_cost_text);
            containerMain.add(actual_monthly_ert_cost_text);

            JButton calculateBtn = new JButton("Calculate total cost");  //creating calculation total cost button object
            containerMain.add(calculateBtn);   //adding it to the window-container

            calculateBtn.addActionListener(new ActionListener() {   //what to do when this button is pressed
                @Override
                public void actionPerformed(ActionEvent e) {
                    double sum_of_expense=0;
                    for (Costs d : costs_collection.getCostsCollection().getCollection()) {
                        for (BuildingCost u : buildingCost_collection.getBuildingCostCollection().getBCollection()) {
                            if (d instanceof Energy) {
                                sum_of_expense += ((Energy) d).calculate_cost(u.getBuilding());

                            }
                        }infoBox(String.valueOf(sum_of_expense), "Calculation Result");
                    }
                }
            });

        } else if (costType.equals("Telephone")) {
            JTextArea type_text = new JTextArea("Type: ");
            JTextArea code_text = new JTextArea("Code: ");
            JTextArea description_text = new JTextArea("Description: ");
            JTextArea measurement_unit_text = new JTextArea("Measurement Unit: ");
            JTextArea price_per_measurement_unit_text = new JTextArea("Price per measurement unit: ");
            JTextArea monthly_fixed_text = new JTextArea("Monthly Fixed: ");
            JTextArea monthly_telephone_cost_text = new JTextArea("Monthly telephone cost: ");

            JTextArea actual_type_text = new JTextArea(costType);
            JTextArea actual_code_text = new JTextArea(id);
            JTextArea actual_description_text = new JTextArea();
            JTextArea actual_measurement_unit_text = new JTextArea();
            JTextArea actual_price_per_measurement_unit_text = new JTextArea();
            JTextArea actual_monthly_fixed_text = new JTextArea();
            JTextArea actual_monthly_telephone_cost_text = new JTextArea();

            for (Costs p : costs_collection.getCostsCollection().getCollection()) {
                if (p instanceof Telephone) {
                    actual_description_text = new JTextArea(p.getDescription());
                    actual_measurement_unit_text = new JTextArea(p.getUnit_of_consumption());
                    actual_price_per_measurement_unit_text = new JTextArea(String.valueOf(p.getPrice_per_unit_of_consumption()));
                    actual_monthly_fixed_text = new JTextArea(String.valueOf(p.getMonthly_fixed()));
                    actual_monthly_telephone_cost_text = new JTextArea(String.valueOf((((Telephone) p).getMonthly_telephone_cost())));
                }
            }

            actual_type_text.setEditable(false);
            actual_code_text.setEditable(false);
            actual_description_text.setEditable(false);
            actual_measurement_unit_text.setEditable(false);
            actual_price_per_measurement_unit_text.setEditable(false);
            actual_monthly_fixed_text.setEditable(false);
            actual_monthly_telephone_cost_text.setEditable(false);

            type_text.setEditable(false);
            code_text.setEditable(false);
            description_text.setEditable(false);
            measurement_unit_text.setEditable(false);
            price_per_measurement_unit_text.setEditable(false);
            monthly_fixed_text.setEditable(false);
            monthly_telephone_cost_text.setEditable(false);

            type_text.setFont(new Font("Serif", Font.BOLD, 18));
            code_text.setFont(new Font("Serif", Font.BOLD, 18));
            description_text.setFont(new Font("Serif", Font.BOLD, 18));
            measurement_unit_text.setFont(new Font("Serif", Font.BOLD, 18));
            price_per_measurement_unit_text.setFont(new Font("Serif", Font.BOLD, 18));
            monthly_fixed_text.setFont(new Font("Serif", Font.BOLD, 18));
            monthly_telephone_cost_text.setFont(new Font("Serif", Font.BOLD, 18));

            actual_type_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_code_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_description_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_measurement_unit_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_price_per_measurement_unit_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_monthly_fixed_text.setFont(new Font("Serif", Font.PLAIN, 18));
            actual_monthly_telephone_cost_text.setFont(new Font("Serif", Font.PLAIN, 18));

            containerMain.add(type_text);
            containerMain.add(actual_type_text);
            containerMain.add(code_text);
            containerMain.add(actual_code_text);
            containerMain.add(description_text);
            containerMain.add(actual_description_text);
            containerMain.add(measurement_unit_text);
            containerMain.add(actual_measurement_unit_text);
            containerMain.add(price_per_measurement_unit_text);
            containerMain.add(actual_price_per_measurement_unit_text);
            containerMain.add(monthly_fixed_text);
            containerMain.add(actual_monthly_fixed_text);
            containerMain.add(monthly_telephone_cost_text);
            containerMain.add(actual_monthly_telephone_cost_text);

            JButton calculateBtn = new JButton("Calculate total cost");  //creating calculation total cost button object
            containerMain.add(calculateBtn);   //adding it to the window-container

            calculateBtn.addActionListener(new ActionListener() {   //what to do when this button is pressed
                @Override
                public void actionPerformed(ActionEvent e) {
                    double sum_of_expense=0;
                    for (Costs d : costs_collection.getCostsCollection().getCollection()) {
                        for (BuildingCost u : buildingCost_collection.getBuildingCostCollection().getBCollection()) {
                            if (d instanceof Telephone) {
                                sum_of_expense += ((Telephone) d).calculate_cost(u.getBuilding());

                            }

                        }
                    }infoBox(String.valueOf(sum_of_expense), "Calculation Result");


                }
            });

        }
    }

    public  static void infoBox(String infoMessage, String titleBar)  //method for message window popup for calculation of total cost
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Total cost: " + titleBar, JOptionPane.INFORMATION_MESSAGE);

    }


}