package view.media_action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.LMSController;
import model.Book;
import model.Media;
import view.media_list.AdminMediaList;
import javax.swing.*;
import java.awt.*;

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
        if (book.isCheckedOut()) {
            availabilityField.setText("Not Available");
        } else {
            availabilityField.setText("Available");
        }

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

        updateButton.setBackground(new Color(0, 150, 0));
        deleteButton.setBackground(new Color(250, 0, 0));
        cancelButton.setBackground(Color.lightGray);

        titleField.setText(book.title);
        authorField.setText(book.author);
        isbnField.setText(book.ISBN);

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
        if (e.getSource() == updateButton) {
            this.book.title = titleField.getText();
            this.book.author = authorField.getText();
            this.book.ISBN = isbnField.getText();
            LMSController.lms.printDevMsg("UPDATE");

            JOptionPane.showMessageDialog(this, "Item has been updated!");
        } else if (e.getSource() == deleteButton) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete \""+this.book.title+"\"");
            switch (choice){
                case 0:
                    LMSController.lms.printDevMsg("DELETE!");
                    LMSController.lms.getCatalog().remove(this.book);
                    JOptionPane.showMessageDialog(this, "Item has been deleted!");

                    //Simulate pressing cancelButton
                    e.setSource(this.cancelButton);
                    this.actionPerformed(e);
                    break;
                case 1, 2:
                    LMSController.lms.printDevMsg("NO DELETE!");
                    break;
            }
        } else if (e.getSource() == cancelButton) {
            dispose();
            AdminMediaList medias = new AdminMediaList();
        }
    }
}
