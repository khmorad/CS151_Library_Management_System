package view.media_action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.LMSController;
import model.Book;
import model.GeneralUser;
import model.Media;
import view.media_list.UserMediaList;
import javax.swing.*;
import java.awt.*;

public class UserMediaAction extends JFrame implements ActionListener {
    JButton checkoutButton = new JButton("Checkout");
    JButton returnButton = new JButton("Return");
    JButton backButton = new JButton("Back");
    JLabel availabilityLabel;

    JLabel bookTitleLabel;
    JLabel authorLabel;
    JLabel isbnLabel;

    private Media currentMediaItem;

    public UserMediaAction(Book book) {

        setTitle("Book Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(866, 650);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));
        this.currentMediaItem = book;
        this.bookTitleLabel = new JLabel("Book Title: " + book.title);
        this.authorLabel = new JLabel("Author: " + book.author);
        this.isbnLabel = new JLabel("ISBN: " + book.ISBN);
        this.availabilityLabel = new JLabel("Available: " + (book.isCheckedOut() ? "No" : "Yes"));

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

        if (book.isCheckedOut()) {
            availabilityLabel = new JLabel("Availability: Not Available");
            availabilityLabel.setForeground(Color.RED);
        } else {
            availabilityLabel = new JLabel("Availability: Available");

        }

        JLabel bookTitleLabel = new JLabel("Book Title: " + book.title);
        JLabel authorLabel = new JLabel("Author: " + book.author);
        JLabel isbnLabel = new JLabel("ISBN: " + book.ISBN);

        ImageIcon bookTitleLabelIcon = new ImageIcon("library_management_system/src/view/graphics/icon3.png");
        bookTitleLabel.setIcon(bookTitleLabelIcon);
        ImageIcon authIcon = new ImageIcon("library_management_system/src/view/graphics/icon4.png");
        authorLabel.setIcon(authIcon);
        ImageIcon isbnLabelIcon = new ImageIcon("library_management_system/src/view/graphics/icon5.png");
        isbnLabel.setIcon(isbnLabelIcon);

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

        bookPanel.setBackground(new Color(255, 255, 255, 200));
        buttonPanel.setBackground(new Color(255, 255, 255, 200));
        contentPane.add(bookPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setField(Book book){
        this.bookTitleLabel.setText("Book Title: " + book.title);
        this.authorLabel.setText("Author: " + book.author);
        this.isbnLabel.setText("ISBN: " + book.ISBN);
        this.availabilityLabel.setText("Available: " + (book.isCheckedOut() ? "No" : "Yes"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ///If user is in this view, they are not librarian.
        GeneralUser genUser = (GeneralUser) LMSController.lms.getCurrentUser();

        if (e.getSource() == checkoutButton) {
            if (genUser.checkOut(this.currentMediaItem)) {
                LMSController.lms.printDevMsg("CHECKOUT");
            }

            //Bug: When button is pressed and UI is updated, there is a werid background added to the reply.
            if (this.currentMediaItem instanceof Book)
                this.setField((Book) this.currentMediaItem);
        } else if (e.getSource() == returnButton) {
            if (genUser.returnMedia(this.currentMediaItem)) {
                LMSController.lms.printDevMsg("RETURN");
            }

            //Bug: When button is pressed and UI is updated, there is a werid background added to the reply.
            if (this.currentMediaItem instanceof Book)
                this.setField((Book) this.currentMediaItem);
        } else if (e.getSource() == backButton) {
            dispose();
            new UserMediaList();
        }
    }
}
