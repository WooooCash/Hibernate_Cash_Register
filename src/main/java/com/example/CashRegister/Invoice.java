package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Invoice extends Container {
    public Invoice(JDialog dialog, OrderDashboard dashboard) {
        super();
        setLayout(new GridLayout(0, 1));

        JLabel niplabel = new JLabel("NIP: ");
        add(niplabel);
        JTextField niptext = new JTextField(10);
        add(niptext);
        JLabel firstNameLabel = new JLabel("First Name: ");
        add(firstNameLabel);
        JTextField firstNameText = new JTextField(10);
        add(firstNameText);
        JLabel lastNameLabel = new JLabel("Last Name: ");
        add(lastNameLabel);
        JTextField lastNameText = new JTextField(10);
        add(lastNameText);
        JLabel addressLabel = new JLabel("Address: ");
        add(addressLabel);
        JTextField addressText = new JTextField(10);
        add(addressText);
        JButton okButton = new JButton("OK");
        add(okButton);
        niptext.addKeyListener(new java.awt.event.KeyAdapter() {
                                   public void keyTyped(java.awt.event.KeyEvent evt) {
                                       char c = evt.getKeyChar();
                                       if (!((c >= '0') && (c <= '9') ||
                                               (c == KeyEvent.VK_BACK_SPACE) ||
                                               (c == KeyEvent.VK_DELETE))) {
                                           evt.consume();
                                       }
                                       if(niptext.getText().length() >= 10)
                                           evt.consume();
                                   }
                               }
        );

        okButton.addActionListener(e -> {
            DatabaseEndpoint databaseEndpoint = DatabaseEndpoint.getDatabaseEndpoint();
            if (niptext.getText().length() != 10) {
                JOptionPane.showMessageDialog(dialog, "NIP must be 10 digits long");
                return;
            }
            if (niptext.getText().equals("") || lastNameText.getText().equals("") || firstNameText.getText().equals("") || addressText.getText().equals("")) {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                dashboard.addInvoice(niptext.getText(), firstNameText.getText() ,lastNameText.getText(), addressText.getText());
                JOptionPane.showMessageDialog(dialog, "Invoice added", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            dialog.dispose();
        });
    }
}
