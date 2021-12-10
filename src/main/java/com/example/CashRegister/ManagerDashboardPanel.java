package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerDashboardPanel extends JPanel {

    private Application app;

    private JButton createOrderButton;
    private JButton checkAssistanceRequestsButton;

    public ManagerDashboardPanel(MainFrame mainFrame) {
        this.setLayout(new GridLayout());

        createOrderButton = new JButton("NEW ORDER");
        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.createOrder();
            }
        });
        checkAssistanceRequestsButton = new JButton("CHECK ASSISTANCE REQUESTS");
        checkAssistanceRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setCheckRequestPanel();
            }
        });

        this.add(createOrderButton);
        this.add(checkAssistanceRequestsButton);
    }

}
