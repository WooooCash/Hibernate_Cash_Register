package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;

public class Invoice extends Container {
    public Invoice(JDialog dialog, OrderDashboard dashboard) {
        super();
        setLayout(new GridLayout(0, 1));
        JLabel niplabel = new JLabel("NIP: ");
        add(niplabel);
        JTextField niptext = new JTextField(10);
        add(niptext);
        JLabel nameLabel = new JLabel("Name: ");
        add(nameLabel);
        JTextField nameText = new JTextField(10);
        add(nameText);
        JLabel addressLabel = new JLabel("Address: ");
        add(addressLabel);
        JTextField addressText = new JTextField(10);
        add(addressText);
        JButton okButton = new JButton("OK");
        add(okButton);
        okButton.addActionListener(e -> {
            System.out.println("NIP: " + niptext.getText());
            System.out.println("Name: " + nameText.getText());
            System.out.println("Address: " + addressText.getText());
            DatabaseEndpoint databaseEndpoint = DatabaseEndpoint.getDatabaseEndpoint();
            if (niptext.getText().equals("") || nameText.getText().equals("") || addressText.getText().equals("")) {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dialog.dispose();
        });
    }
}
