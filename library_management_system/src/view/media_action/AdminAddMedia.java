package view.media_action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.*;
import view.media_list.AdminMediaList;
import view.media_list.UserMediaList;
import java.util.*;
import java.util.List;

import javax.swing.*;

import controller.LMSController;

import java.awt.*;

public class AdminAddMedia extends JFrame implements ActionListener {
    JTextField titleField = new JTextField(20);
    JTextField authorField = new JTextField(20);
    JTextField isbnField = new JTextField(20);
    JTextField availabilityField = new JTextField(20);

    JButton addButton = new JButton("Add");
    JButton cancelButton = new JButton("Cancel");

    public AdminAddMedia() {

        setTitle("Book Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(866, 650);
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

        JLabel bookTitleLabel = new JLabel("Book Title:");
        JLabel authorLabel = new JLabel("Author:");
        JLabel isbnLabel = new JLabel("ISBN:");
        JLabel availabilityLabel = new JLabel("Availability:");

        bookTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        authorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        isbnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        availabilityLabel.setFont(new Font("Arial", Font.BOLD, 16));

        addButton.setPreferredSize(new Dimension(150, 40));

        cancelButton.setPreferredSize(new Dimension(150, 40));

        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonColor = new Color(0, 102, 204);

        addButton.setFont(buttonFont);

        cancelButton.setFont(buttonFont);

        addButton.setForeground(Color.WHITE);

        cancelButton.setForeground(Color.WHITE);

        addButton.setBackground(new Color(0, 150, 0));

        cancelButton.setBackground(Color.lightGray);

        JPanel bookPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        bookPanel.add(bookTitleLabel, constraints);
        constraints.gridx = 1;
        bookPanel.add(titleField, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        bookPanel.add(authorLabel, constraints);
        constraints.gridx = 1;
        bookPanel.add(authorField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        bookPanel.add(isbnLabel, constraints);
        constraints.gridx = 1;
        bookPanel.add(isbnField, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        bookPanel.add(availabilityLabel, constraints);
        constraints.gridx = 1;
        bookPanel.add(availabilityField, constraints);

        availabilityField.setText("Available");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        addButton.addActionListener(this);
        cancelButton.addActionListener(this);
        bookPanel.setBackground(new Color(255, 255, 255, 200));
        buttonPanel.setBackground(new Color(255, 255, 255, 200));
        contentPane.add(bookPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            dispose();
            AdminMediaList adminList = new AdminMediaList();
        } else if (e.getSource() == addButton) {
            Book newBook = new Book(null, titleField.getText(), authorField.getText(),
                    isbnField.getText());
            // logics: save it to book database
            dispose();

            try {
                List<Media> newBookList = Book.readFromJsonFile("library_management_system/database/books.json");
                newBookList.add(1, newBook);
                List<Book> list = new LinkedList<>();
                for (int i = 0; i < newBookList.size(); i++) {
                    list.add((Book) newBookList.get(i));
                }
                Book.writeToJsonFile(list, "library_management_system/database/books.json");
                LMSController.lms.setCatalog(newBookList);

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            // back to admin media list
            AdminMediaList medias = new AdminMediaList();
        }
    }
}
