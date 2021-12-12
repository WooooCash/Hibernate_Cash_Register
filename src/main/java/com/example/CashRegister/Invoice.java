package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;

public class Invoice extends Container {
    public Invoice(JDialog dialog) {
        super();
        setLayout(new GridLayout(0, 1));
        JLabel niplabel = new JLabel("NIP: ");
        JTextField niptext = new JTextField(10);
        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameText = new JTextField(10);
        JLabel addressLabel = new JLabel("Address: ");
        JTextField addressText = new JTextField(10);
        add(niplabel);
        add(niptext);
        add(nameLabel);
        add(nameText);
        add(addressLabel);
        add(addressText);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            dialog.dispose();
        });
        add(okButton);
    }
}
