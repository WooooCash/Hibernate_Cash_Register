package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;

public class ManagerDashboardPanel extends JPanel {

    private Application app;

    private JButton createOrderButton;
    private JButton checkAssistanceRequestsButton;

    public ManagerDashboardPanel() {
        this.setLayout(new GridLayout());

        createOrderButton = new JButton("NEW ORDER");
        checkAssistanceRequestsButton = new JButton("CHECK ASSISTANCE REQUESTS");

        this.add(createOrderButton);
        this.add(checkAssistanceRequestsButton);
    }

}
