import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class add_new_Expense_window extends JFrame implements MouseListener {
    private JButton save;
    private JButton cancel;
    private JButton add;
    private JList list;
    private JLabel label;
    private DefaultListModel listModel;
    Building building = null;
    BuildingCost building_cost = null;
    boolean check = false;
    Costs cost;
    Building chosen_building=building;

    String code;
    String type;
    String description;
    String measurement_unit;
    double monthly_fixed;
    double monthly_ert_cost;
    double monthly_telephone_cost;
    double price_per_square_meter;
    double price_per_measurement_unit;
    double price_per_cube_meter;
    double usage;

    public add_new_Expense_window(Building_Reader read_building_from_file, DefaultListModel list_with_expenses, Building building) {
        setTitle("Add new expense");
        setBounds(300, 300, 700, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add = new JButton("Add new expense");
        save = new JButton("Save");
        cancel = new JButton("Cancel");
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectedIndex(0);
        label = new JLabel();
        Container cp = getContentPane();
        JPanel northpanel = new JPanel();
        JPanel southpanel = new JPanel();
        southpanel.setLayout(new BorderLayout());
        northpanel.setLayout(new FlowLayout());
        cp.setLayout(new BorderLayout());
        JPanel paneButton = new JPanel();
        paneButton.setLayout(new FlowLayout());
        paneButton.setPreferredSize(new Dimension(400, 235));
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(150, 100));
        paneButton.add(add);
        paneButton.add(save);
        paneButton.add(cancel);
        northpanel.setPreferredSize(new Dimension(100, 50));
        northpanel.add(paneButton);
        label.setPreferredSize(new Dimension(100, 200));
        northpanel.add(label);
        southpanel.setPreferredSize(new Dimension(100, 200));
        southpanel.add(listScroller, BorderLayout.SOUTH);
        southpanel.add(list);
        cp.add(southpanel, BorderLayout.SOUTH);
        cp.add(northpanel, BorderLayout.NORTH);
        cp.setPreferredSize(new Dimension(450, 330));
        pack();

        list.addMouseListener(this);
        setVisible(true);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!check) {
                    type = JOptionPane.showInputDialog(null, "Please type the type of the expense. (Rent,Water,Cleaning,Energy,Telephone)");
                    while ((!type.equalsIgnoreCase("Rent")  || !type.equalsIgnoreCase("Cleaning") || !type.equalsIgnoreCase("Energy") || !type.equalsIgnoreCase("Telephone") || !type.equalsIgnoreCase("Water"))&&!check) {
                        if (type.equalsIgnoreCase("Rent")) {
                            code = JOptionPane.showInputDialog(null, "Please type the id code of the expense");
                            description = JOptionPane.showInputDialog(null, "Please type the description of the expense");
                            price_per_square_meter = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the price per square meter of the expense."));
                            listModel.addElement("ID Code: " + code);
                            listModel.addElement("Description: " + description);
                            listModel.addElement("Price Per Square Meter: " + price_per_square_meter);
                            cost = new Rent(code, description, price_per_square_meter);
                            building_cost = new BuildingCost(chosen_building, cost, 0);
                            check = true;
                        } else if (type.equalsIgnoreCase("Cleaning")) {
                            code = JOptionPane.showInputDialog(null, "Please type the id code of the expense");
                            description = JOptionPane.showInputDialog(null, "Please type the description of the expense");
                            price_per_square_meter = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the price per square meter of the expense."));
                            listModel.addElement("ID Code: " + code);
                            listModel.addElement("Description: " + description);
                            listModel.addElement("Price Per Square Meter: " + price_per_square_meter);
                            cost = new Cleaning(code, description, price_per_square_meter);
                            building_cost = new BuildingCost(chosen_building, cost, 0);
                            check = true;
                        } else if (type.equalsIgnoreCase("Energy")) {
                            code = JOptionPane.showInputDialog(null, "Please type the id code of the expense");
                            description = JOptionPane.showInputDialog(null, "Please type the description of the expense");
                            usage = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the consumption of the expense."));
                            measurement_unit = JOptionPane.showInputDialog(null, "Please type the price per square meter of the expense.");
                            price_per_measurement_unit = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the price per measurement unit of the expense."));
                            monthly_fixed = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the monthly fixed of the expense."));
                            monthly_ert_cost = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the monthly ERT cost of the expense."));
                            listModel.addElement("ID Code: " + code);
                            listModel.addElement("Description: " + description);
                            listModel.addElement("Consumption: " + usage);
                            listModel.addElement("Measurement Unit: " + measurement_unit);
                            listModel.addElement("Price Per Measurement Meter: " + price_per_measurement_unit);
                            listModel.addElement("Monthly Fixed: " + monthly_fixed);
                            listModel.addElement("Monthly ERT cost: " + monthly_ert_cost);
                            cost = new Energy(code, description, usage, price_per_measurement_unit, measurement_unit, monthly_fixed, monthly_ert_cost);
                            building_cost = new BuildingCost(chosen_building, cost, usage);
                            check = true;
                        } else if (type.equalsIgnoreCase("Water")) {
                            code = JOptionPane.showInputDialog(null, "Please type the id code of the expense");
                            description = JOptionPane.showInputDialog(null, "Please type the description of the expense");
                            usage = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the consumption of the expense."));
                            measurement_unit = JOptionPane.showInputDialog(null, "Please type the price per square meter of the expense.");
                            price_per_measurement_unit = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the price per measurement unit of the expense."));
                            monthly_fixed = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the monthly fixed of the expense."));
                            price_per_cube_meter = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the price per cube meter of the expense."));
                            listModel.addElement("ID Code: " + code);
                            listModel.addElement("Description: " + description);
                            listModel.addElement("Consumption: " + usage);
                            listModel.addElement("Measurement Unit: " + measurement_unit);
                            listModel.addElement("Price Per Measurement Meter: " + price_per_measurement_unit);
                            listModel.addElement("Monthly Fixed: " + monthly_fixed);
                            listModel.addElement("Price per Cube meter: " + price_per_cube_meter);
                            cost = new Water(code, description, usage, price_per_measurement_unit, measurement_unit, monthly_fixed, monthly_ert_cost);
                            building_cost = new BuildingCost(chosen_building, cost, usage);
                            check = true;
                        } else if (type.equalsIgnoreCase("Telephone")) {
                            code = JOptionPane.showInputDialog(null, "Please type the id code of the expense");
                            description = JOptionPane.showInputDialog(null, "Please type the description of the expense");
                            usage = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the consumption of the expense."));
                            measurement_unit = JOptionPane.showInputDialog(null, "Please type the price per square meter of the expense.");
                            price_per_measurement_unit = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the price per measurement unit of the expense."));
                            monthly_fixed = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the monthly fixed of the expense."));
                            monthly_telephone_cost = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the monthly telephone cost of the expense."));
                            listModel.addElement("ID Code: " + code);
                            listModel.addElement("Description: " + description);
                            listModel.addElement("Consumption: " + usage);
                            listModel.addElement("Measurement Unit: " + measurement_unit);
                            listModel.addElement("Price Per Measurement Meter: " + price_per_measurement_unit);
                            listModel.addElement("Monthly Fixed: " + monthly_fixed);
                            listModel.addElement("Monthly Telephone cost: " + monthly_telephone_cost);
                            cost = new Telephone(code, description, usage, price_per_measurement_unit, measurement_unit, monthly_fixed, monthly_ert_cost);
                            building_cost = new BuildingCost(chosen_building, cost, usage);
                            check = true;
                        } else {
                            type = JOptionPane.showInputDialog(null, "Please insert the type correctly(Rent,Water,Cleaning,Energy,Telephone).", "Warning", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }JOptionPane.showMessageDialog(null, "New Expense Added. Press save to confirm, press cancel to delete it.", "Success", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    read_building_from_file.getBuildingCostCollection().getBCollection().add(building_cost);
                    list_with_expenses.addElement("ID Code: " + building_cost.getCost_type().getID() + " Description: " + building_cost.getCost_type().getDescription());
                    check = false;
                    JOptionPane.showMessageDialog(null, "Building successfully saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
                    listModel.clear(); //empty the list so we can add the info for the next expense
                } else if (!check) {
                    JOptionPane.showMessageDialog(null, "Please add a new expense before you save", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    building_cost = null;
                    cost=null;
                    listModel.clear();  //empty the list as we choose that we dont want to add this expense
                    JOptionPane.showMessageDialog(null, "Information that you added have been deleted.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                    check = false;
                } else if (!check) {
                    JOptionPane.showMessageDialog(null, "You need to add information for an expense first.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public void mouseClicked(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
    }

    public void mousePressed(MouseEvent event) {
    }

}