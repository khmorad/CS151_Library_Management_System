package view.media_list;

import model.Book;
import view.auth_page.UserLogin;
import view.media_action.UserMediaAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class UserMediaList extends JFrame implements ActionListener {
    private List<Book> books;
    private JButton backButton = new JButton("Logout");

    public UserMediaList(List<Book> books) {
        this.books = books;

        setTitle("Book Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(866, 650);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        for (Book book : books) {
            JButton bookButton = new JButton(book.title);
            bookButton.addActionListener(this);

            bookButton.setPreferredSize(new Dimension(300, 50));
            bookButton.setBackground(Color.lightGray);
            bookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Style and design changes for the buttons
            bookButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            bookButton.setFocusPainted(false);
            bookButton.setContentAreaFilled(false);
            bookButton.setOpaque(true);
            bookButton.setBorderPainted(false);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing between buttons

            buttonPanel.add(bookButton);
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
            UserLogin login = new UserLogin();
        } else {
            for (Book book : books) {
                if (e.getActionCommand().equals(book.title)) {
                    dispose();
                    UserMediaAction mediaAction = new UserMediaAction(book);
                    break;
                }
            }
        }
    }
}
