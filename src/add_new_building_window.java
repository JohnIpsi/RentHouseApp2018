import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class add_new_building_window extends JFrame implements MouseListener {
    private JButton save;
    private JButton cancel;
    private JButton add;
    private JList list;
    private JLabel label;
    private DefaultListModel listModel;
    Building building = null;
    BuildingCost building_cost = null;
    boolean check = false;

    public add_new_building_window(Building_Reader read_building_from_file, DefaultListModel list_with_buildings) {
        setTitle("Add new bulding");


        setBounds(300, 300, 700, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add = new JButton("Add a Building");
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
                    String code = JOptionPane.showInputDialog(null, "Please type the id code of the building");
                    String description = JOptionPane.showInputDialog(null, "Please type the description of the building");
                    String address = JOptionPane.showInputDialog(null, "Please type address of the building");
                    double zone_price = Double.parseDouble(JOptionPane.showInputDialog(null, "Please type the zone price of the building"));
                    int square_meters = Integer.parseInt(JOptionPane.showInputDialog(null, "Please type the Square meters of the building"));
                    JOptionPane.showMessageDialog(null, "New Building Added. Press save to confirm, press cancel to delete it.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    listModel.addElement("ID Code: " + code);
                    listModel.addElement("Description: " + description);
                    listModel.addElement("Address: " + address);
                    listModel.addElement("Zone Price: " + zone_price);
                    listModel.addElement("Square meters: " + square_meters);

                    building = new Building(code, description, address, zone_price, square_meters);
                    building_cost = new BuildingCost(building, null, 0);
                    check = true;
                }
            }
        });


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    read_building_from_file.getBuildingCostCollection().getBCollection().add(building_cost);
                    list_with_buildings.addElement("ID Code: " + building_cost.getBuilding().getBuilding_id() + " Description: " + building_cost.getBuilding().getDescription());
                    check = false;
                    JOptionPane.showMessageDialog(null, "Building successfully saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
                    listModel.clear(); //empty the list so we can add the info for the next building
                } else if (!check) {
                    JOptionPane.showMessageDialog(null, "Please add a new building before you save", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    building = null;
                    building_cost = null;
                    listModel.clear();  //empty the list as we choose that we dont want to add this building
                    JOptionPane.showMessageDialog(null, "Information that you added have been deleted.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                    check = false;
                } else if (!check) {
                    JOptionPane.showMessageDialog(null, "You need to add information for a building first.", "Warning", JOptionPane.INFORMATION_MESSAGE);
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