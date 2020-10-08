import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class edit_expense_window extends JFrame implements MouseListener {
    private JList list;
    private JList list2;
    private JButton save = new JButton("Save Changes");
    private JButton cancel = new JButton("Cancel");
    private DefaultListModel listModel;
    private DefaultListModel listModel2;
    boolean check0, check1, check2, check3, check4, Check5, check6, check7, check8, check9 = false;

    String edited_code_id;
    String edited_description;
    double edited_price_per_square_meter;
    double edited_monthly_fixed;
    double edited_price_per_measurement_unit;
    String edited_measurement_unit;
    double edited_monthly_ert_cost, edited_monthly_telephone_cost, edited_price_per_cube_meter;

    String final_id;
    String final_id2;

    public edit_expense_window(Building_Reader read_building_from_file, String id, String id2) {
        final_id = id; //expense id
        final_id2 = id2;//building id
        setTitle("Details for expense");
        JTextArea title = new JTextArea("New information");
        setBounds(300, 300, 700, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listModel = new DefaultListModel();
        listModel2 = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectedIndex(0);
        list2 = new JList(listModel2);
        list2.setSelectedIndex(0);
        Container cp = getContentPane();
        JPanel new_list_panel = new JPanel();
        JPanel old_list_panel = new JPanel();
        old_list_panel.setLayout(new FlowLayout());
        new_list_panel.add(title);
        new_list_panel.setLayout(new BorderLayout());
        cp.setLayout(new BorderLayout());
        JPanel paneButton = new JPanel();
        paneButton.setLayout(new BorderLayout());
        old_list_panel.add(list, BorderLayout.CENTER);
        old_list_panel.add(save, BorderLayout.CENTER);
        old_list_panel.add(cancel, BorderLayout.CENTER);
        new_list_panel.add(title, BorderLayout.NORTH);
        new_list_panel.add(list2, BorderLayout.CENTER);
        paneButton.add(old_list_panel, BorderLayout.NORTH);

        paneButton.add(new_list_panel, BorderLayout.SOUTH);
        cp.add(paneButton, BorderLayout.CENTER);
        cp.setPreferredSize(new Dimension(450, 330));
        pack();


        setVisible(true);

        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() >= 2) { //double click showing info for buildings
                    if (list.getSelectedIndex() == 0 && !check0) {
                        edited_code_id = JOptionPane.showInputDialog(null, "Enter new ID Code for this expense");
                        listModel2.addElement("Code ID: " + edited_code_id);
                        check0 = true;
                    } else if (list.getSelectedIndex() == 1 && !check1) {
                        edited_description = JOptionPane.showInputDialog(null, "Enter new Description for this expense");
                        listModel2.addElement("Description: " + edited_description);
                        check1 = true;
                    } else if (list.getSelectedIndex() == 2 && !check2) {
                        edited_measurement_unit = JOptionPane.showInputDialog(null, "Enter new measurement unit for this expense");
                        listModel2.addElement("Measurement unit: " + edited_measurement_unit);
                        check2 = true;
                    } else if (list.getSelectedIndex() == 3 && !check3) {
                        edited_price_per_measurement_unit = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter new price per measurement unit for this expense"));
                        listModel2.addElement("price per measurement unit: " + edited_price_per_measurement_unit);
                        check3 = true;
                    } else if (list.getSelectedIndex() == 4 && !check4) {
                        edited_monthly_fixed = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new Monthly fixed for this expense"));
                        listModel2.addElement("monthly fixed: " + edited_monthly_fixed);
                        check4 = true;
                    }
                }

            }


            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check0 || check1 || check2 || check3 || check4) {
                    for (BuildingCost bc : read_building_from_file.getBuildingCostCollection().getBCollection()) {
                        if (bc.getCost_type().equals(final_id)) {
                            if (check2) {
                                bc.getCost_type().setUnit_of_consumption(edited_measurement_unit);
                                check2 = false;
                            } else if (check0) {
                                bc.getCost_type().setID(edited_code_id);
                                check0 = false;
                            } else if (check1) {
                                bc.getCost_type().setDescription(edited_description);
                                check1 = false;
                            } else if (check3) {
                                bc.getCost_type().setPrice_per_unit_of_consumption(edited_price_per_measurement_unit);
                                check3 = false;
                            } else if (check4) {
                                bc.getCost_type().setMonthly_fixed(edited_monthly_fixed);
                                check4 = false;
                            }
                            listModel2.clear();
                            JOptionPane.showMessageDialog(null, "Building Information Updated", "Updated Successfully", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Not new info has been inserted.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check0 || check1 || check2 || check3 || check4) {
                    listModel2.clear();
                    if (check0) {
                        edited_code_id = null;
                        check0 = false;
                    } else if (check1) {
                        edited_description = null;
                        check1 = false;
                    } else if (check2) {
                        edited_measurement_unit = null;
                    } else if (check3) {
                        edited_price_per_measurement_unit = 0;
                        check3 = false;
                    } else if (check4) {
                        edited_monthly_fixed = 0;
                        check4 = false;
                    }
                    JOptionPane.showMessageDialog(null, "All inserted information has been deleted.", "Information Cleared", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Not new info has been inserted.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        for (BuildingCost m : read_building_from_file.getBuildingCostCollection().getBCollection()) {
            if (m.getBuilding().getBuilding_id().equals(final_id2)) {
                if (m.getCost_type().getID().equals(final_id)) {
                    if (m.getCost_type() instanceof Rent) {
                        listModel.addElement("Code ID: " + m.getCost_type().getID());
                        listModel.addElement("Description: " + m.getCost_type().getDescription());
                        listModel.addElement("Price per square meter: " + m.getCost_type().getPricePerSquareMeter());
                    } else if (m.getCost_type() instanceof Cleaning) {
                        listModel.addElement("Code ID: " + m.getCost_type().getID());
                        listModel.addElement("Description: " + m.getCost_type().getDescription());
                        listModel.addElement("Price per square meter: " + m.getCost_type().getPricePerSquareMeter());
                    } else if (m.getCost_type() instanceof Water) {
                        listModel.addElement("Code ID: " + m.getCost_type().getID());
                        listModel.addElement("Description: " + m.getCost_type().getDescription());
                        listModel.addElement("Measurement unit: " + m.getCost_type().getUnit_of_consumption());
                        listModel.addElement("Price per measurement unit: " + m.getCost_type().getPrice_per_unit_of_consumption());
                        listModel.addElement("Monthly Fixed: " + m.getCost_type().getMonthly_fixed());
                        listModel.addElement("Price per cube meter: " + ((Water) m.getCost_type()).getPrice_per_cube_meter());
                    } else if (m.getCost_type() instanceof Telephone) {
                        listModel.addElement("Code ID: " + m.getCost_type().getID());
                        listModel.addElement("Description: " + m.getCost_type().getDescription());
                        listModel.addElement("Measurement unit: " + m.getCost_type().getUnit_of_consumption());
                        listModel.addElement("Price per measurement unit: " + m.getCost_type().getPrice_per_unit_of_consumption());
                        listModel.addElement("Monthly Fixed: " + m.getCost_type().getMonthly_fixed());
                        listModel.addElement("Monthly telephone cost: " + ((Telephone) m.getCost_type()).getMonthly_telephone_cost());
                    } else if (m.getCost_type() instanceof Energy) {
                        listModel.addElement("Code ID: " + m.getCost_type().getID());
                        listModel.addElement("Description: " + m.getCost_type().getDescription());
                        listModel.addElement("Measurement unit: " + m.getCost_type().getUnit_of_consumption());
                        listModel.addElement("Price per measurement unit: " + m.getCost_type().getPrice_per_unit_of_consumption());
                        listModel.addElement("Monthly Fixed: " + m.getCost_type().getMonthly_fixed());
                        listModel.addElement("Monthly ERT cost: " + ((Energy) m.getCost_type()).getMonthly_ERT_cost());
                    }

                }
            }
        }
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