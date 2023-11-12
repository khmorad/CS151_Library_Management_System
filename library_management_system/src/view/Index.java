package view;

import javax.swing.*;

import view.auth_page.AdminLogin;
import view.auth_page.UserRegister;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JFrame implements ActionListener {
    JButton userButton = new JButton("USER");
    JButton adminButton = new JButton("ADMIN");
    JButton quitButton = new JButton("QUIT"); // Added quit button

    public Index() {
        setTitle("SJSU Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 240); // Slightly increased height to accommodate the quit button
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        JLabel welcomeLabel = new JLabel("Welcome to Martin Luther King, JR. Library");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel selectRoleLabel = new JLabel("Please Select Your Role");
        selectRoleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectRoleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        userButton.setFont(new Font("Arial", Font.BOLD, 14));
        userButton.setBackground(new Color(100, 150, 255));
        userButton.setForeground(Color.WHITE);
        userButton.setFocusPainted(false);

        adminButton.setFont(new Font("Arial", Font.BOLD, 14));
        adminButton.setBackground(new Color(0, 150, 0)); // Changing admin button color to green
        adminButton.setForeground(Color.WHITE);
        adminButton.setFocusPainted(false);

        quitButton.setFont(new Font("Arial", Font.BOLD, 14));
        quitButton.setBackground(new Color(255, 0, 0)); // Changing quit button color to red
        quitButton.setForeground(Color.WHITE);
        quitButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(userButton);
        buttonPanel.add(adminButton);

        JPanel quitPanel = new JPanel(new FlowLayout()); // Panel for the quit button
        quitPanel.add(quitButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(quitPanel, BorderLayout.SOUTH);

        add(welcomeLabel, BorderLayout.NORTH);
        add(selectRoleLabel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH); // Added quit button panel

        userButton.setPreferredSize(new Dimension(150, 40));
        adminButton.setPreferredSize(new Dimension(150, 40));
        quitButton.setPreferredSize(new Dimension(100, 30));

        userButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        userButton.setFocusPainted(false);
        userButton.setContentAreaFilled(false);
        userButton.setOpaque(true);
        userButton.setBorderPainted(false);

        adminButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        adminButton.setFocusPainted(false);
        adminButton.setContentAreaFilled(false);
        adminButton.setOpaque(true);
        adminButton.setBorderPainted(false);

        quitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        quitButton.setFocusPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setOpaque(true);
        quitButton.setBorderPainted(false);

        userButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        adminButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        quitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        userButton.addActionListener(this);
        adminButton.addActionListener(this);
        quitButton.addActionListener(this); // ActionListener for quit button

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // handle request
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userButton) {
            dispose();
            UserRegister userLogin = new UserRegister();
        } else if (e.getSource() == adminButton) {
            dispose();
            AdminLogin adminLogin = new AdminLogin();
        } else if (e.getSource() == quitButton) {
            System.exit(0); // Quit application when quit button is clicked
        }
    }
}
