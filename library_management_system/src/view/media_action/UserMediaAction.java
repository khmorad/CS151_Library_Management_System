package view.media_action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.LMSController;
import model.Book;
import model.GeneralUser;
import view.media_list.UserMediaList;
import javax.swing.*;
import java.awt.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserMediaAction extends JFrame implements ActionListener {
    JButton checkoutButton = new JButton("Checkout");
    JButton returnButton = new JButton("Return");
    JButton backButton = new JButton("Back");

    private String currentItemID;

    public UserMediaAction(Book book) {

        setTitle("Book Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(866, 650);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        this.currentItemID = book.getItemID();
        JLabel bookTitleLabel = new JLabel("Book Title: " + book.title);
        JLabel authorLabel = new JLabel("Author: " + book.author);
        JLabel isbnLabel = new JLabel("ISBN: " + book.ISBN);
        JLabel availabilityLabel = new JLabel("Availability: Available");

        bookTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        authorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        isbnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        availabilityLabel.setFont(new Font("Arial", Font.BOLD, 16));

        checkoutButton.setPreferredSize(new Dimension(150, 40));
        returnButton.setPreferredSize(new Dimension(150, 40));
        backButton.setPreferredSize(new Dimension(150, 40));

        checkoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonColor = new Color(0, 102, 204);

        checkoutButton.setFont(buttonFont);
        returnButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        checkoutButton.setForeground(Color.WHITE);
        returnButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);

        checkoutButton.setBackground(buttonColor);
        returnButton.setBackground(new Color(0, 150, 0));
        backButton.setBackground(new Color(255, 0, 0));

        JPanel bookPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        bookPanel.add(bookTitleLabel, constraints);

        constraints.gridy = 1;
        bookPanel.add(authorLabel, constraints);

        constraints.gridy = 2;
        bookPanel.add(isbnLabel, constraints);

        constraints.gridy = 3;
        bookPanel.add(availabilityLabel, constraints);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(checkoutButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(backButton);

        checkoutButton.addActionListener(this);
        returnButton.addActionListener(this);
        backButton.addActionListener(this);

        add(bookPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkoutButton) {
            ///If user is in this view, they are not librarian.
            GeneralUser genUser = (GeneralUser) LMSController.lms.getCurrentUser();
            if(genUser.checkOut(this.currentItemID)){

            }
            System.out.println("CHECKOUT");
        } else if (e.getSource() == returnButton) {
            System.out.println("RETURN");
        } else if (e.getSource() == backButton) {
            dispose();
            try {
                UserMediaList medias = new UserMediaList(
                        Book.readFromJsonFile("library_management_system/database/books.json"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
