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
        buttonPanel.setLayout(new GridLayout(0, 1, 0, 10)); // Grid layout for buttons with spacing

        for (Book book : books) {
            JButton bookButton = new JButton(book.title);
            bookButton.addActionListener(this);

            // Button styling
            bookButton.setPreferredSize(new Dimension(300, 50));
            bookButton.setBackground(Color.lightGray);
            bookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            bookButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            bookButton.setFocusPainted(false);
            bookButton.setContentAreaFilled(false);
            bookButton.setOpaque(true);
            bookButton.setBorderPainted(false);
            buttonPanel.add(bookButton);
        }

        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setPreferredSize(new Dimension(400, 500)); // Set preferred size for the scroll pane

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBackground(Color.WHITE); // Set background color for the list panel
        listPanel.add(scrollPane, BorderLayout.CENTER);

        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setBackground(new Color(255, 90, 90));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
