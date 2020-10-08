import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class main_menu_GUI extends JFrame implements MouseListener {

    Building_Reader read_building_from_file = new Building_Reader();
    Costs_Reader read_cost_from_file = new Costs_Reader();
    private JList list_building;
    private DefaultListModel listModel;
    String final_id;
    int counter = -1;
    ArrayList<BuildingCost> to_remove = new ArrayList<>();


    public main_menu_GUI() {

        Container containerMain = getContentPane();
        containerMain.setLayout(new FlowLayout());
        final JFileChooser fileChooser = new JFileChooser();

        JPanel tab1 = new JPanel();
        JPanel tab2 = new JPanel();
        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Costs", tab1);
        tabs.addTab("Building Costs", tab2);


        JButton LoadCostsBtn = new JButton("Load Costs File");
        containerMain.add(LoadCostsBtn);

        JButton LoadBuildingCostBtn = new JButton("Load Building and Building Costs File");
        containerMain.add(LoadBuildingCostBtn);

        JButton WriteNewBuildingBtn = new JButton("Write new Building Costs File");
        containerMain.add(WriteNewBuildingBtn);


        JButton ViewEditBtn = new JButton("View and edit");
        containerMain.add(ViewEditBtn);

        LoadCostsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    read_cost_from_file.getCostsCollection().getCollection().clear();
                    read_cost_from_file.loadFile("C:\\Users\\Giannis\\Desktop\\java22018\\src\\COSTS_FILE.txt"); //the path to load the cost file
                    SwingUtilities.updateComponentTreeUI(containerMain);
                    new Succesful_load_file();
                } catch (NullPointerException k) {
                    new Failure_load_file();
                }
            }
        });

        LoadBuildingCostBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    read_building_from_file.loadFile("C:\\Users\\Giannis\\Desktop\\java22018\\src\\BUILDING_FILE.txt"); //the path to load the building file
                    SwingUtilities.updateComponentTreeUI(containerMain);
                    new Succesful_load_file();  //window that informs about the success of new file reading
                } catch (NullPointerException k) {
                    new Failure_load_file();    //window that informs about the success of new file reading
                }
            }
        });


        WriteNewBuildingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    read_building_from_file.getBuildingCostCollection().getBCollection().clear();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                            "txt files", "txt");    //creating a filter so we can choose only txt files
                    fileChooser.setFileFilter(filter);   //sets the filter to file chooser
                    fileChooser.showOpenDialog(getParent());   //shows the window where we choose the new file
                    read_building_from_file.loadFile(fileChooser.getSelectedFile().getAbsolutePath()); //the path to load the new building file
                    SwingUtilities.updateComponentTreeUI(containerMain); //refresh window
                    new Succesful_load_file();  //window that informs about the success of new file reading
                } catch (NullPointerException k) {
                    new Failure_load_file();   //window that informs about the failure of new file reading
                }
            }
        });


        ViewEditBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton telephoneBtn = new JButton("Telephone");
                JButton energyBtn = new JButton("Energy");
                JButton rentBtn = new JButton("Rent");
                JButton cleaningBtn = new JButton("Cleaning");
                JButton waterBtn = new JButton("Water");
                try {
                    containerMain.removeAll(); //clear the window
                    containerMain.setLayout(new BorderLayout());
                    setBounds(400, 400, 600, 500);
                    containerMain.add(tabs);

                    JPanel panePortion1 = new JPanel();
                    JLabel panePortion2 = new JLabel();
                    panePortion1.setLayout(new FlowLayout());
                    panePortion1.setBackground(Color.GRAY);
                    panePortion2.setLayout(new FlowLayout());
                    panePortion1.add(telephoneBtn);
                    panePortion1.add(energyBtn);
                    panePortion1.add(rentBtn);
                    panePortion1.add(cleaningBtn);
                    panePortion1.add(waterBtn);
                    panePortion1.setPreferredSize(new Dimension(100, 200));
                    panePortion2.setPreferredSize(new Dimension(400, 200));
                    tab1.add(panePortion1, BorderLayout.WEST);
                    panePortion2.add(new JTextArea("EXPENSES TYPES"));
                    tab1.add(panePortion2, BorderLayout.EAST);
                    pack();

                    //END OF TAB1

                    //START OF TAB2

                    JTextArea title_tab2 = new JTextArea("BUILDING CATALOG ");
                    title_tab2.setFont(new Font("Serif", Font.BOLD, 17));
                    title_tab2.setBackground(Color.CYAN);
                    title_tab2.setEditable(false);
                    JPanel tab2panel = new JPanel();
                    tab2panel.setPreferredSize(new Dimension(180, 900));


                    listModel = new DefaultListModel();
                    list_building = new JList(listModel);
                    list_building.setSelectedIndex(0);
                    JScrollPane listScroller = new JScrollPane(list_building);
                    listScroller.setPreferredSize(new Dimension(150, 100));

                    list_building.setVisibleRowCount(-1);
                    list_building.setVisible(true);
                    list_building.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            String information_building = (String) list_building.getSelectedValue(); //returns string of the list choice
                            String idcodefrombutton = information_building.substring(information_building.indexOf("ID Code: ") + 8, information_building.indexOf(" Description: ")); //we get the string that is in the option that we choose from the list and then we get only the id from it
                            final_id = idcodefrombutton.replaceAll("\\s+", ""); //removing blank characters from the string
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

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

                    tab2.add(tab2panel);
                    tab2panel.add(title_tab2);


                    for (BuildingCost m : read_building_from_file.getBuildingCostCollection().getBCollection()) {  //here we add elements to the list , building elements showing id code and description for each one of them
                        listModel.addElement("ID Code: " + m.getBuilding().getBuilding_id() + " Description: " + m.getBuilding().getDescription());
                        tab2panel.add(list_building);
                    }


                    JButton add_button = new JButton("Add building");
                    JButton edit_button = new JButton("View/Edit building");
                    JButton delete_button = new JButton("Delete building");
                    JButton edit_Expenses_of_building_button = new JButton("Edit expenses");
                    JButton calculate_Expenses_of_building_button = new JButton("Calculate total cost");

                    tab2panel.add(add_button);
                    tab2panel.add(edit_button);
                    tab2panel.add(delete_button);
                    tab2panel.add(edit_Expenses_of_building_button);
                    tab2panel.add(calculate_Expenses_of_building_button);


                    add_button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            add_new_building_window add_building = new add_new_building_window(read_building_from_file, listModel);
                            SwingUtilities.updateComponentTreeUI(tab2panel); //refresh window
                            SwingUtilities.updateComponentTreeUI(list_building);
                        }
                    });

                    edit_button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {  //press view/edit button showing info for buildings
                            view_edit_building_window create_the_window = new view_edit_building_window(read_building_from_file, final_id);
                            SwingUtilities.updateComponentTreeUI(tab2panel); //refresh window
                            SwingUtilities.updateComponentTreeUI(list_building);
                        }
                    });


                    list_building.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            if (e.getClickCount() >= 2) { //double click showing info for buildings
                                view_edit_building_window create_the_window = new view_edit_building_window(read_building_from_file, final_id);
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
                    delete_button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //delete building
                            int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the building?", "Delete building confirmation", JOptionPane.YES_NO_OPTION);
                            if (confirmed == JOptionPane.YES_OPTION) {  //here we check if the user is sure about his choice to delete the building
                                for (BuildingCost b : read_building_from_file.getBuildingCostCollection().getBCollection()) {
                                    counter += 1;
                                    if (b.getBuilding().getBuilding_id().equals(final_id)) {
                                        to_remove.add(b);
                                        listModel.remove(counter);
                                        counter = -1;
                                        JOptionPane.showMessageDialog(null, "Building Successfully deleted", "Deleted Successfully", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                                read_building_from_file.getBuildingCostCollection().getBCollection().removeAll(to_remove); //i had to do this this way because if i remove it immediatly i was getting concurrent modification exception
                            }
                        }
                    });

                    edit_Expenses_of_building_button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            view_edit_expenses_window create_new_expenses_window = new view_edit_expenses_window(read_cost_from_file, read_building_from_file, final_id);
                        }
                    });

                    calculate_Expenses_of_building_button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (BuildingCost bc : read_building_from_file.getBuildingCostCollection().getBCollection()) {
                                if (bc.getBuilding().getBuilding_id().equals(final_id)) {
                                    if (bc.getCost_type() != null) {
                                        JOptionPane.showMessageDialog(null, "The total expenses of this building is: " + read_building_from_file.getBuildingCostCollection().calculate_total_cost_of_building(bc.getBuilding()) + " Euro", "Total Expenses of building", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "This building does not have any expenses so the total cost is 0 Euro", "No Expenses Found", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                        }
                    });


                    //END OF TAB2
                    energyBtn.addActionListener(new ActionListener() {  //action to take when energy button is pressed
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panePortion2.removeAll();
                            SwingUtilities.updateComponentTreeUI(containerMain); //refresh window
                            JTextArea textArea = new JTextArea("Energy Expenses");
                            textArea.setEditable(false);
                            textArea.setFont(new Font("Serif", Font.BOLD, 18));
                            panePortion2.add(textArea);
                            for (Costs d : read_cost_from_file.getCostsCollection().getCollection()) {
                                if (d instanceof Energy) {
                                    JPanel info = new JPanel();
                                    JTextArea Id_code_text = new JTextArea("ID Code: ");
                                    Id_code_text.setEditable(false);
                                    info.add(Id_code_text);
                                    JButton selectionBtn = new JButton(d.getID());
                                    info.add(selectionBtn);
                                    JTextArea desc_text = new JTextArea("Description: ");
                                    desc_text.setEditable(false);
                                    JTextArea desc_actualtext = new JTextArea(d.getDescription());
                                    desc_actualtext.setEditable(false);
                                    info.add(desc_text);
                                    info.add(desc_actualtext);
                                    panePortion2.add(info);  //add information jpanel depending on the type cost clicked
                                    panePortion2.setPreferredSize((new Dimension(400, 200)));

                                    selectionBtn.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            More_information_window new_window_more_info = new More_information_window("Energy", read_cost_from_file, d.getID(), read_building_from_file);
                                        }
                                    });

                                }
                            }
                        }
                    });

                    rentBtn.addActionListener(new ActionListener() {    //action to take when rent button is pressed
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panePortion2.removeAll();
                            SwingUtilities.updateComponentTreeUI(containerMain); //refresh window
                            JTextArea textArea = new JTextArea("Rent Expenses");
                            textArea.setEditable(false);
                            textArea.setFont(new Font("Serif", Font.BOLD, 18));
                            panePortion2.add(textArea);
                            for (Costs d : read_cost_from_file.getCostsCollection().getCollection()) {
                                if (d instanceof Rent) {
                                    JPanel info = new JPanel();
                                    JTextArea Id_code_text = new JTextArea("ID Code: ");
                                    Id_code_text.setEditable(false);
                                    info.add(Id_code_text);
                                    JButton selectionBtn = new JButton(d.getID());
                                    info.add(selectionBtn);
                                    JTextArea desc_text = new JTextArea("Description: ");
                                    desc_text.setEditable(false);
                                    JTextArea desc_actualtext = new JTextArea(d.getDescription());
                                    desc_actualtext.setEditable(false);
                                    info.add(desc_text);
                                    info.add(desc_actualtext);
                                    panePortion2.add(info);  //add information jpanel depending on the type cost clicked
                                    panePortion2.setPreferredSize((new Dimension(400, 200)));

                                    selectionBtn.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            More_information_window new_window_more_info = new More_information_window("Rent", read_cost_from_file, d.getID(), read_building_from_file);
                                        }
                                    });

                                }
                            }

                        }

                    });

                    waterBtn.addActionListener(new ActionListener() { //action to take when water button is pressed
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panePortion2.removeAll();
                            SwingUtilities.updateComponentTreeUI(containerMain); //refresh window
                            JTextArea textArea = new JTextArea("Water Expenses");
                            textArea.setEditable(false);
                            textArea.setFont(new Font("Serif", Font.BOLD, 18));
                            panePortion2.add(textArea);
                            for (Costs d : read_cost_from_file.getCostsCollection().getCollection()) {
                                if (d instanceof Water) {
                                    JPanel info = new JPanel();
                                    JTextArea Id_code_text = new JTextArea("ID Code: ");
                                    Id_code_text.setEditable(false);
                                    info.add(Id_code_text);
                                    JButton selectionBtn = new JButton(d.getID());
                                    info.add(selectionBtn);
                                    JTextArea desc_text = new JTextArea("Description: ");
                                    desc_text.setEditable(false);
                                    JTextArea desc_actualtext = new JTextArea(d.getDescription());
                                    desc_actualtext.setEditable(false);
                                    info.add(desc_text);
                                    info.add(desc_actualtext);
                                    panePortion2.add(info);  //add information jpanel depending on the type cost clicked
                                    panePortion2.setPreferredSize((new Dimension(400, 200)));

                                    selectionBtn.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            More_information_window new_window_more_info = new More_information_window("Water", read_cost_from_file, d.getID(), read_building_from_file);
                                        }
                                    });
                                }
                            }
                        }
                    });

                    cleaningBtn.addActionListener(new ActionListener() { //action to take when cleaning button is pressed
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panePortion2.removeAll();
                            SwingUtilities.updateComponentTreeUI(containerMain); //refresh window
                            JTextArea textArea = new JTextArea("Cleaning Expenses");
                            textArea.setEditable(false);
                            textArea.setFont(new Font("Serif", Font.BOLD, 18));
                            panePortion2.add(textArea);
                            for (Costs d : read_cost_from_file.getCostsCollection().getCollection()) {
                                if (d instanceof Cleaning) {
                                    JPanel info = new JPanel();
                                    JTextArea Id_code_text = new JTextArea("ID Code: ");
                                    Id_code_text.setEditable(false);
                                    info.add(Id_code_text);
                                    JButton selectionBtn = new JButton(d.getID());
                                    info.add(selectionBtn);
                                    JTextArea desc_text = new JTextArea("Description: ");
                                    desc_text.setEditable(false);
                                    JTextArea desc_actualtext = new JTextArea(d.getDescription());
                                    desc_actualtext.setEditable(false);
                                    info.add(desc_text);
                                    info.add(desc_actualtext);
                                    panePortion2.add(info);  //add information jpanel depending on the type cost clicked
                                    panePortion2.setPreferredSize((new Dimension(400, 200)));

                                    selectionBtn.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            More_information_window new_window_more_info = new More_information_window("Cleaning", read_cost_from_file, d.getID(), read_building_from_file);
                                        }
                                    });

                                }
                            }

                        }
                    });

                    telephoneBtn.addActionListener(new ActionListener() { //action to take when telephone button is pressed
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            panePortion2.removeAll();
                            SwingUtilities.updateComponentTreeUI(containerMain); //refresh window
                            JTextArea textArea = new JTextArea("Telephone Expenses");
                            textArea.setEditable(false);
                            textArea.setFont(new Font("Serif", Font.BOLD, 18));
                            panePortion2.add(textArea);
                            for (Costs d : read_cost_from_file.getCostsCollection().getCollection()) {
                                if (d instanceof Telephone) {
                                    JPanel info = new JPanel();
                                    JTextArea Id_code_text = new JTextArea("ID Code: ");
                                    Id_code_text.setEditable(false);
                                    info.add(Id_code_text);
                                    JButton selectionBtn = new JButton(d.getID());
                                    info.add(selectionBtn);
                                    JTextArea desc_text = new JTextArea("Description: ");
                                    desc_text.setEditable(false);
                                    JTextArea desc_actualtext = new JTextArea(d.getDescription());
                                    desc_actualtext.setEditable(false);
                                    info.add(desc_text);
                                    info.add(desc_actualtext);
                                    panePortion2.add(info);  //add information jpanel depending on the type cost clicked
                                    panePortion2.setPreferredSize((new Dimension(400, 200)));

                                    selectionBtn.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            More_information_window new_window_more_info = new More_information_window("Telephone", read_cost_from_file, d.getID(), read_building_from_file);
                                        }
                                    });

                                }
                            }
                        }
                    });

                    SwingUtilities.updateComponentTreeUI(containerMain); //refresh window
                } catch (NullPointerException k) {
                    new Failure_load_file();   //window that informs about the failure of new file reading
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setBounds(400, 400, 860, 80);
        setVisible(true);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}


