package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

public class LoginPanel extends JPanel {

    private Application app;

    private JTextField textField;
    private JPasswordField passField;
    private JButton button;

    LoginPanel thisPanel;

    LoginPanel(JDialog dialog) {
        app = Application.getApp();
        thisPanel = this;

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(150, 30));
        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(150,30));
        passField.setEchoChar('*');
        button = new JButton("Login");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = valueOf(passField.getPassword());

                boolean success = app.login(username, password);
                if (success) {
                    //JOptionPane.showMessageDialog(thisPanel, "Success!");
                    dialog.dispose();
                }
                else
                    JOptionPane.showMessageDialog(thisPanel, "Invalid Credentials!");

            }
        });

        this.add(textField);
        this.add(passField);
        this.add(button);
    }
    LoginPanel() {
        app = Application.getApp();
        thisPanel = this;
        thisPanel.setLayout(new FlowLayout());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(150, 30));
        passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(150,30));
        passField.setEchoChar('*');
        button = new JButton("Login");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = valueOf(passField.getPassword());

                boolean success = app.login(username, password);
                if (!success)
                    JOptionPane.showMessageDialog(thisPanel, "Invalid Credentials!");

            }
        });

        this.add(textField);
        this.add(passField);
        this.add(button);
    }



}
