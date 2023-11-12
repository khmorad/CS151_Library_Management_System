package view.auth_page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Index;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;

public class UserRegister extends JFrame implements ActionListener {

    private JTextField userID = new JTextField(15);
    private JTextField firstName = new JTextField(15);
    private JTextField lastName = new JTextField(15);
    private JTextField email = new JTextField(15);
    private JPasswordField password = new JPasswordField(15);
    private JButton registerButton = new JButton("Register");
    private JButton loginButton = new JButton("Login");
    private JButton backButton = new JButton("Back");

    public UserRegister() {
        setTitle("User Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(866, 650);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        registerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        registerButton.setFocusPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);

        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);

        Font labelFont = new Font("Arial", Font.BOLD, 14);

        JLabel userIDLabel = new JLabel("User ID:");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");

        userIDLabel.setFont(labelFont);
        firstNameLabel.setFont(labelFont);
        lastNameLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(new Color(0, 150, 0));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);

        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(100, 150, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        JPanel registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        registerPanel.add(userIDLabel, constraints);

        constraints.gridx = 1;
        registerPanel.add(userID, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        registerPanel.add(firstNameLabel, constraints);

        constraints.gridx = 1;
        registerPanel.add(firstName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        registerPanel.add(lastNameLabel, constraints);

        constraints.gridx = 1;
        registerPanel.add(lastName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        registerPanel.add(emailLabel, constraints);

        constraints.gridx = 1;
        registerPanel.add(email, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        registerPanel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        registerPanel.add(password, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        registerPanel.add(registerButton, constraints);

        JLabel existingAccountLabel = new JLabel("Already have an account?");
        existingAccountLabel.setFont(new Font("Arial", Font.BOLD, 12));

        constraints.gridy = 6;
        registerPanel.add(existingAccountLabel, constraints);

        constraints.gridy = 7;
        registerPanel.add(loginButton, constraints);

        // Set preferred size for wider text fields
        userID.setPreferredSize(new Dimension(300, 30));
        firstName.setPreferredSize(new Dimension(300, 30));
        lastName.setPreferredSize(new Dimension(300, 30));
        email.setPreferredSize(new Dimension(300, 30));
        password.setPreferredSize(new Dimension(300, 30));

        // Set preferred size for buttons
        registerButton.setPreferredSize(new Dimension(150, 40));
        loginButton.setPreferredSize(new Dimension(150, 40));

        // Set cursor and tool tips
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setToolTipText("Register");
        loginButton.setToolTipText("Login");

        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(255, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setToolTipText("Go back to the previous page");

        JPanel backPanel = new JPanel(new FlowLayout());

        backPanel.add(backButton);

        constraints.gridy = 10;
        registerPanel.add(backPanel, constraints);

        registerButton.setEnabled(false);

        password.getDocument().addDocumentListener(new MyDocumentListener());
        firstName.getDocument().addDocumentListener(new MyDocumentListener());
        lastName.getDocument().addDocumentListener(new MyDocumentListener());
        userID.getDocument().addDocumentListener(new MyDocumentListener());
        email.getDocument().addDocumentListener(new MyDocumentListener());

        // Add action listeners
        registerButton.addActionListener(this);
        loginButton.addActionListener(this);
        backButton.addActionListener(this);

        add(registerPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class MyDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateButtonState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateButtonState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateButtonState();
        }

        private void updateButtonState() {
            // Enable the button if all text fields are filled, otherwise disable it
            if (!userID.getText().isEmpty() && !firstName.getText().isEmpty() && !lastName.getText().isEmpty() &&
                    !email.getText().isEmpty() && !password.getText().isEmpty()) {
                registerButton.setEnabled(true);
            } else {
                registerButton.setEnabled(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            dispose();
            UserLogin userLogin = new UserLogin(userID.getText(), new String(password.getPassword()));
        } else if (e.getSource() == loginButton) {
            dispose();
            UserLogin userLogin = new UserLogin();
        } else {
            dispose();
            Index index = new Index();
        }
    }
}
