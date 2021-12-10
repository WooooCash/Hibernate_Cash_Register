package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDashboardPanel extends JPanel {

    private Application app;

    private JButton createOrderButton;
    private JButton requestAssistanceButton;

//    public EmployeeDashboardPanel() {
//        this.setLayout(new GridLayout());
//
//        createOrderButton = new JButton("NEW ORDER");
//        requestAssistanceButton = new JButton("REQUEST ASSISTANCE");
//
//        this.add(createOrderButton);
//        this.add(requestAssistanceButton);
//    }

    public EmployeeDashboardPanel(MainFrame mainFrame) {
        this.setLayout(new GridLayout());

        createOrderButton = new JButton("NEW ORDER");
        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.createOrder();
            }
        });
        requestAssistanceButton = new JButton("REQUEST ASSISTANCE");
        requestAssistanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("halo");
                mainFrame.setAssistanceRequestPage();
            }
        });

        this.add(createOrderButton);
        this.add(requestAssistanceButton);
    }

}
