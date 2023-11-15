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
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2, 8, 10));

        Color buttonColor = new Color(135, 206, 235);

        // Convert RGB to HSB
        float[] hsb = Color.RGBtoHSB(buttonColor.getRed(), buttonColor.getGreen(), buttonColor.getBlue(), null);

        // Reduce the saturation by 50% (you can adjust this value)
        hsb[1] *= 0.5f;
        buttonColor = new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));

        for (int i = 1; i < books.size(); i++) {
            JButton bookButton = new JButton(books.get(i).title);
            bookButton.addActionListener(this);

            // Button styling
            bookButton.setPreferredSize(new Dimension(300, 50));
            bookButton.setBackground(buttonColor); // Orange background
            bookButton.setForeground(Color.BLACK); // White text color
            bookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            bookButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Padding
            bookButton.setFocusPainted(false);
            bookButton.setFont(new Font("Arial", Font.BOLD, 16)); // Font style

            // Add shadow effect
            bookButton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 120, 70), 1),
                    BorderFactory.createEmptyBorder(4, 14, 4, 14)));

            buttonPanel.add(bookButton);
        }

        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setPreferredSize(new Dimension(400, 500)); // Set preferred size for the scroll pane

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBackground(Color.WHITE); // Set background color for the list panel
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        listPanel.add(scrollPane, BorderLayout.CENTER);

        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setBackground(new Color(255, 90, 90));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(Color.WHITE); // Set background color for the top panel
        topPanel.add(backButton);

        JPanel containerPanel = new JPanel(new BorderLayout());

        containerPanel.add(topPanel, BorderLayout.NORTH);
        containerPanel.add(listPanel, BorderLayout.CENTER);

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
