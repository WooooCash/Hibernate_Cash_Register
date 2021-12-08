package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;

public class EmployeeDashboardPanel extends JPanel {

    private Application app;

    private JButton createOrderButton;
    private JButton requestAssistanceButton;

    public EmployeeDashboardPanel() {
        this.setLayout(new GridLayout());

        createOrderButton = new JButton("NEW ORDER");
        requestAssistanceButton = new JButton("REQUEST ASSISTANCE");

        this.add(createOrderButton);
        this.add(requestAssistanceButton);
    }

}
