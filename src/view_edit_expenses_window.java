import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class view_edit_expenses_window extends JFrame implements MouseListener {
    private JList list;
    private JButton add =new JButton("Add Expense");
    private JButton view_edit =new JButton("View/Edit Expense");
    private JButton delete= new JButton("Delete Expense");
    private DefaultListModel listModel;
    String final_id;
    String final_id2;
    Building building=null;
    ArrayList<Costs> to_remove=new ArrayList<>();
    int counter=-2;

    public view_edit_expenses_window(Costs_Reader read_cost_from_file , Building_Reader read_building_from_file, String id) {
        Container cp= getContentPane();
        cp.setLayout(new FlowLayout());
        setTitle("View expenses catalog");
        setBounds(300, 300, 700, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextArea title_tab2 = new JTextArea("Expenses CATALOG ");
        title_tab2.setFont(new Font("Serif", Font.BOLD, 17));
        title_tab2.setBackground(Color.CYAN);
        title_tab2.setEditable(false);
        JPanel tab2panel = new JPanel();
        JPanel buttonPanel =new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        tab2panel.setPreferredSize(new Dimension(180, 200));
        tab2panel.setLayout(new BorderLayout());
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectedIndex(0);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(150, 100));
        list.setVisibleRowCount(-1);
        list.setVisible(true);
        tab2panel.add(title_tab2,BorderLayout.NORTH);
        tab2panel.add(list ,BorderLayout.CENTER);
        buttonPanel.add(add);
        buttonPanel.add(view_edit);
        buttonPanel.add(delete);
        cp.add(tab2panel);
        cp.add(buttonPanel);
        cp.setPreferredSize(new Dimension(450, 330));
        setVisible(true);
        pack();
        final_id=id;
        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String information_expense = (String) list.getSelectedValue(); //returns string of the list choice
                String idcodefrombutton = information_expense.substring(information_expense.indexOf("ID Code: ") + 8, information_expense.indexOf(" Description: ")); //we get the string that is in the option that we choose from the list and then we get only the id from it
                final_id2 = idcodefrombutton.replaceAll("\\s+", ""); //removing blank characters from the string

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() >= 2) { //double click showing info for expenses
                    edit_expense_window create_edit_window= new edit_expense_window(read_building_from_file,final_id, final_id2);
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

        int cheack10=0;
        for (BuildingCost m : read_building_from_file.getBuildingCostCollection().getBCollection()) {
            if (m.getBuilding().getBuilding_id().equals(id)) {
                building=m.getBuilding();
                for (Costs c : read_cost_from_file.getCostsCollection().getCollection()) {
                    try {
                        if (m.getCost_type().getID().equals(c.getID())) {
                            listModel.addElement("Code ID: " + c.getID() + " Description: " + c.getDescription());
                            cheack10=0;
                        }
                    } catch (NullPointerException n) {
                        if(cheack10==0){
                        JOptionPane.showMessageDialog(null, "This building has no expenses " , "No expenses", JOptionPane.INFORMATION_MESSAGE);
                        cheack10+=1;
                        }
                    }
                } tab2panel.add(list);
            }
        }


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               add_new_Expense_window create_Expense = new add_new_Expense_window(read_building_from_file, listModel,building );
            }
        });


        view_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open view edit window expenses
                edit_expense_window create_edit_window= new edit_expense_window(read_building_from_file, final_id, final_id2);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //delete expense from list
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the expense?", "Delete expense confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {  //here we check if the user is sure about his choice to delete the building
                    for (BuildingCost b : read_building_from_file.getBuildingCostCollection().getBCollection()) {
                        counter += 1;
                        if (b.getBuilding().getBuilding_id().equals(final_id)) {
                            to_remove.add(b.getCost_type());
                            listModel.remove(counter);
                            counter = -1;
                            JOptionPane.showMessageDialog(null, "Expense Successfully deleted", "Deleted Successfully", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    read_building_from_file.getBuildingCostCollection().getBCollection().removeAll(to_remove); //i had to do this this way because if i remove it immediatly i was getting concurrent modification exception
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