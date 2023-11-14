package view.media_action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.LMSController;
import model.Book;
import view.media_list.AdminMediaList;
import view.media_list.UserMediaList;
import javax.swing.*;
import java.awt.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminMediaAction extends JFrame implements ActionListener {
    JTextField titleField = new JTextField(20);
    JTextField authorField = new JTextField(20);
    JTextField isbnField = new JTextField(20);
    JTextField availabilityField = new JTextField(20);

    JButton updateButton = new JButton("Update");
    JButton deleteButton = new JButton("Delete");
    JButton cancelButton = new JButton("Cancel");

    private Book book;

    public AdminMediaAction(Book book) {
        this.book = book;

        setTitle("Book Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(866, 650);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        JLabel bookTitleLabel = new JLabel("Book Title:");
        JLabel authorLabel = new JLabel("Author:");
        JLabel isbnLabel = new JLabel("ISBN:");
        JLabel availabilityLabel = new JLabel("Availability:");

        bookTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        authorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        isbnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        availabilityLabel.setFont(new Font("Arial", Font.BOLD, 16));

        updateButton.setPreferredSize(new Dimension(150, 40));
        deleteButton.setPreferredSize(new Dimension(150, 40));
        cancelButton.setPreferredSize(new Dimension(150, 40));

        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonColor = new Color(0, 102, 204);

        updateButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        cancelButton.setFont(buttonFont);

        updateButton.setForeground(Color.WHITE);
        deleteButton.setForeground(Color.WHITE);
        cancelButton.setForeground(Color.WHITE);

        updateButton.setBackground(buttonColor);
        deleteButton.setBackground(new Color(250, 0, 0));
        cancelButton.setBackground(Color.lightGray);

        titleField.setText(book.title);
        authorField.setText(book.author);
        isbnField.setText(book.ISBN);
        availabilityField.setText("To be updated");

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);

        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        cancelButton.addActionListener(this);

        add(bookPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            book.title = titleField.getText();
            book.author = authorField.getText();
            book.ISBN = isbnField.getText();

            System.out.println("UPDATE");
        } else if (e.getSource() == deleteButton) {
            System.out.println("DELETE");
        } else if (e.getSource() == cancelButton) {
            dispose();
            try {
                AdminMediaList medias = new AdminMediaList(
                        Book.readFromJsonFile("library_management_system/database/books.json"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
