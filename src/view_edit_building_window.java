import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class view_edit_building_window extends JFrame implements MouseListener {
    private JList list;
    private JList list2;
    private JButton save =new JButton("Save Changes");
    private JButton cancel= new JButton("Cancel");
    private DefaultListModel listModel;
    private DefaultListModel listModel2;
    boolean check0, check1, check2 , check3, check4 =false;

    String edited_code_id;
    String edited_description;
    String edited_address;
    double edited_zone_price;
    int edited_square_meters;

    public view_edit_building_window(Building_Reader read_building_from_file, String id) {
        setTitle("Details for building");
        JTextArea title =new JTextArea("New information");
        setBounds(300, 300, 700, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listModel = new DefaultListModel();
        listModel2= new DefaultListModel();
        list = new JList(listModel);
        list.setSelectedIndex(0);
        list2=new JList(listModel2);
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
        new_list_panel.add(title , BorderLayout.NORTH);
        new_list_panel.add(list2,BorderLayout.CENTER );
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
                if (e.getClickCount() >= 2 ) { //double click showing info for buildings
                    if(list.getSelectedIndex()== 0 && !check0){
                        edited_code_id = JOptionPane.showInputDialog(null,"Enter new ID Code for this building");
                        listModel2.addElement("Code ID: " + edited_code_id);
                        check0=true;
                    }else if(list.getSelectedIndex()== 1&& !check1){
                        edited_description = JOptionPane.showInputDialog(null,"Enter new Description for this building");
                        listModel2.addElement("Description: "+ edited_description);
                        check1=true;
                    }else if(list.getSelectedIndex()== 2 && !check2){
                        edited_address = JOptionPane.showInputDialog(null,"Enter new Address for this building");
                        listModel2.addElement("Address: " +edited_address);
                        check2=true;
                    }else if(list.getSelectedIndex()== 3 && !check3){
                        edited_zone_price = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter new Zone Price for this building"));
                        listModel2.addElement("Zone Price: "+edited_zone_price);
                        check3=true;
                    }else if(list.getSelectedIndex()== 4 && !check4){
                        edited_square_meters = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter new Square Meters for this building"));
                        listModel2.addElement("Square Meters: "+edited_square_meters);
                        check4=true;
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
                        if (bc.getBuilding().getBuilding_id().equals(id)) {
                            if (check2) {
                                System.out.println(edited_address);
                                bc.getBuilding().setAddress(edited_address);
                                check2 = false;
                            } else if (check0) {
                                bc.getBuilding().setBuilding_id(edited_code_id);
                                check0 = false;
                            } else if (check1) {
                                bc.getBuilding().setDescription(edited_description);
                                check1 = false;
                            } else if (check4) {
                                bc.getBuilding().setSquare_meters(edited_square_meters);
                                check4 = false;
                            } else if (check3) {
                                bc.getBuilding().setZone_price(edited_zone_price);
                                check3 = false;
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
                        edited_address = null;
                    } else if (check3) {
                        edited_zone_price = 0;
                        check3 = false;
                    } else if (check4) {
                        edited_square_meters = 0;
                        check4 = false;
                    }
                    JOptionPane.showMessageDialog(null, "All inserted information has been deleted.", "Information Cleared", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Not new info has been inserted.", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        for (BuildingCost m : read_building_from_file.getBuildingCostCollection().getBCollection()) {
            if (m.getBuilding().getBuilding_id().equals(id)) {
                listModel.addElement("Code ID: " + m.getBuilding().getBuilding_id());
                listModel.addElement("Description: " +m.getBuilding().getDescription());
                listModel.addElement("Address " + m.getBuilding().getAddress());
                listModel.addElement("Zone Price: " +m.getBuilding().getZone_price());
                listModel.addElement("Square Meters: " + m.getBuilding().getSquare_meters());
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