import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPage extends JFrame implements ActionListener {
    private JButton librarianButton;
    private JButton normalUserButton;

    public MainPage() {

        // creating the frame
        // title of the app
        setTitle("Library Management System");
        // window size of the application
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a label
        JLabel label = new JLabel("Library Management System");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(JLabel.CENTER);
        // Create buttons
        librarianButton = new JButton("Librarian");
        normalUserButton = new JButton("Normal User");

        // Add action listeners to buttons
        librarianButton.addActionListener(this);
        normalUserButton.addActionListener(this);

        // Create a panel to hold the label and buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(librarianButton);
        buttonPanel.add(normalUserButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        // Add the main panel to the frame
        add(mainPanel);
        // Make the frame visible
        setVisible(true);
    }

    // ovrride so it displays if either button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == librarianButton) {
            JOptionPane.showMessageDialog(this, "You selected Librarian");
        } else if (e.getSource() == normalUserButton) {
            JOptionPane.showMessageDialog(this, "You selected Normal User");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainPage::new);
    }
}
