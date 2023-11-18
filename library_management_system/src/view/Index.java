package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import view.auth_page.AdminLogin;
import view.auth_page.Login;
import view.auth_page.UserRegister;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Index extends JFrame implements ActionListener {
    JButton userButton = new JButton("USER");
    JButton adminButton = new JButton("ADMIN");
    JButton quitButton = new JButton("QUIT"); // Added quit button

    public Index() {
        setTitle("SJSU Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(866, 650); // Slightly increased height to accommodate the quit button
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the image and paint it as the background
                ImageIcon imageIcon = new ImageIcon("library_management_system/src/view/graphics/spartan.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Martin Luther King, JR. Library");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel selectRoleLabel = new JLabel("Please Select Your Role");
        selectRoleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        selectRoleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(0, 1, 0, 10));
        messagePanel.add(welcomeLabel);
        messagePanel.add(selectRoleLabel);

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

        bottomPanel.add(quitPanel, BorderLayout.SOUTH);

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

        buttonPanel.setBackground(new Color(255, 255, 255, 200));
        contentPane.add(messagePanel, BorderLayout.PAGE_START);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(quitPanel, BorderLayout.PAGE_END);
        setContentPane(contentPane);
        buttonPanel.setBackground(new Color(255, 255, 255, 200));
        bottomPanel.setBackground(new Color(255, 255, 255, 200));
        messagePanel.setBackground(new Color(255, 255, 255, 200));
        quitPanel.setBackground(new Color(255, 255, 255, 200));

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
            Login adminLogin = new Login();
        } else if (e.getSource() == quitButton) {
            System.exit(0); // Quit application when quit button is clicked
        }
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            // Set the opacity of the background image
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Change the opacity value as
                                                                                         // needed
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}
