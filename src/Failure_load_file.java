import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Failure_load_file  extends JFrame implements ActionListener {

        private JButton but1;
        private JTextArea resultArea = new JTextArea("File failed to load!", 1, 20);
        public Failure_load_file() {
            setTitle("Too bad!!");
            drawFrame();
            but1.addActionListener(this);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == but1) {
                setVisible(false);
            }
        }

        private void drawFrame() {
            setBounds(500, 400, 400, 250);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            but1 = new JButton("Back");
            Container cp = getContentPane();
            cp.setLayout(new BorderLayout());
            JPanel paneButton = new JPanel();
            paneButton.setLayout(new FlowLayout());
            paneButton.add(but1);
            resultArea.setFont(new Font("Serif", Font.ITALIC, 20));
            resultArea.setFont(new Font("Serif", Font.BOLD, 20));
            resultArea.setForeground(Color.BLUE);
            resultArea.setEditable(false);
            paneButton.add(resultArea);
            cp.add(paneButton, BorderLayout.LINE_START);
            pack();
        }
    }
