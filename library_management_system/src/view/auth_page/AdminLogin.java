package view.auth_page;

import javax.swing.*;

import controller.LMSController;
import view.Index;
import view.media_list.AdminMediaList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import model.Book;

public class AdminLogin extends JFrame implements ActionListener {

    private JTextField username = new JTextField(15); // Adjusted to accommodate a 15-character username
    private JPasswordField password = new JPasswordField(15); // Adjusted to accommodate a 15-character password
    private JButton loginButton = new JButton("Login");
    private JButton backButton = new JButton("Back");

    public AdminLogin() {

        setTitle("SJSU Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(866, 650);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);

        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);

        JLabel usernameLabel = new JLabel("Enter Username:");
        JLabel passwordLabel = new JLabel("Enter Password:");
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(100, 150, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(255, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        loginPanel.add(username, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        loginPanel.add(password, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        loginPanel.add(loginButton, constraints);

        constraints.gridy = 3;
        loginPanel.add(backButton, constraints);

        username.setPreferredSize(new Dimension(300, 30));
        password.setPreferredSize(new Dimension(300, 30));

        loginButton.setPreferredSize(new Dimension(150, 40));
        backButton.setPreferredSize(new Dimension(100, 30));

        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        loginButton.addActionListener(this);
        backButton.addActionListener(this);

        add(loginPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (LMSController.lms.login(this.username.getText(), new String(this.password.getPassword())) == null){
                //Login Failed
                LMSController.lms.printDevMsg("Login failed, from admin login button");
                return;
            }
            dispose();
            AdminMediaList medias = new AdminMediaList();


        } 
        else if (e.getSource() == backButton) {
            dispose();
            Index index = new Index();
        }
    }
}
