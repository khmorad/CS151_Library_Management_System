package view.auth_page;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.LMSController;
import model.Book;
import model.GeneralUser;
import model.Librarian;
import model.User;
import view.media_list.AdminMediaList;
import view.media_list.UserMediaList;

public class Login extends JFrame implements ActionListener {

    private JTextField username = new JTextField(15); // Adjusted to accommodate a 15-character username
    private JPasswordField password = new JPasswordField(15); // Adjusted to accommodate a 15-character password
    private JButton loginButton = new JButton("Login");
    private JButton backButton = new JButton("Back to Register");

    public Login() {
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
        loginButton.setEnabled(false);
        password.getDocument().addDocumentListener(new MyDocumentListener());
        username.getDocument().addDocumentListener(new MyDocumentListener());

        add(loginPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Login(String un, String pw) {
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
        username.setText(un);
        password.setText(pw);

        loginButton.setPreferredSize(new Dimension(150, 40));
        backButton.setPreferredSize(new Dimension(100, 30));

        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        loginButton.addActionListener(this);
        backButton.addActionListener(this);
        loginButton.setEnabled(true);
        password.getDocument().addDocumentListener(new MyDocumentListener());
        username.getDocument().addDocumentListener(new MyDocumentListener());

        add(loginPanel, BorderLayout.CENTER);
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
            if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
                loginButton.setEnabled(true);
            } else {
                loginButton.setEnabled(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            System.out.println("NOTE " + password.getPassword().toString());

            char[] pass = password.getPassword();
            String passString = new String(pass);
            System.out.println(passString);

            User currentUser = LMSController.lms.login(username.getText(), passString);
            if (currentUser == null) {
                // Login Failed

                LMSController.lms.printDevMsg("Login failed");
                JOptionPane.showMessageDialog(this, "Login failed!");
                return;
            }

            dispose();
            try {
                LMSController.lms.setCatalog(Book.readFromJsonFile("library_management_system/database/books.json"));

                if (passString.equals("admin") && username.getText().equals("admin")) {
                    AdminMediaList list = new AdminMediaList();

                } else {

                    UserMediaList list = new UserMediaList();
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } else if (e.getSource() == backButton) {
            dispose();
            new UserRegister();
        }
    }

}
