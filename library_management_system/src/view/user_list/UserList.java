package view.user_list;

import model.User;
import view.auth_page.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserList extends JFrame implements ActionListener {
    private List<User> users;
    private JButton backButton = new JButton("Logout");

    public UserList(List<User> users) {
        this.users = users;

        setTitle("Book Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(866, 650);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        for (User user : users) {
            JButton userButton = new JButton(user.userID);
            userButton.addActionListener(this);

            userButton.setPreferredSize(new Dimension(300, 50));
            userButton.setBackground(Color.lightGray);
            userButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Style and design changes for the buttons
            userButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            userButton.setFocusPainted(false);
            userButton.setContentAreaFilled(false);
            userButton.setOpaque(true);
            userButton.setBorderPainted(false);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing between buttons

            buttonPanel.add(userButton);
        }

        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setBackground(new Color(255, 90, 90));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);

        // Create a panel for the top right corner
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(backButton);

        // Create a container panel to hold the buttons and the top panel
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(topPanel, BorderLayout.NORTH);
        containerPanel.add(new JScrollPane(buttonPanel), BorderLayout.CENTER);

        add(containerPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
            Login login = new Login();
        } else {
            for (User currUser : users) {
//                    if (e.getActionCommand().equals(currUser.userID)) {
//                        dispose();
//                        UserMediaAction mediaAction = new UserMediaAction(currUser);
//                        break;
//                    }
            }
        }
    }
}
