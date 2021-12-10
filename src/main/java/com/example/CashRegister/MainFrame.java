package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private Application app;

    private JFrame frame;
    private JPanel leftPanel;
    private JPanel centerPanel;

    public MainFrame() {


    }

    public void initialize() {
        app = Application.getApp();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setLayout(new BorderLayout());

        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(150, 0));
        leftPanel.setBackground(Color.cyan);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame loginFrame = new LoginFrame();
            }
        });

        leftPanel.add(loginButton);

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void loggedIn(String username, int status) {
        leftPanel.remove(leftPanel.getComponent(0));

        JLabel loggedInText = new JLabel("Welcome " + username + "!");
        leftPanel.add(loggedInText);

        //Add new button options to left Panel and add center panel
        for (int i = 0; i < 6; i++) {
            leftPanel.add(new JButton("Button" + i));
        }
        updateComponent(leftPanel);

        setDashboardPage(status);
    }

    private void updateComponent(JComponent component) {
        component.revalidate();
        component.repaint();
    }

    public void setDashboardPage(int status) {
        if (centerPanel.getComponentCount() != 0)
            centerPanel.remove(0);
        System.out.println(status);
        if (status == 0) {centerPanel.add(new EmployeeDashboardPanel(this), BorderLayout.CENTER);} // create new panel for regular employee
        else if (status == 1) {centerPanel.add(new ManagerDashboardPanel(this), BorderLayout.CENTER);} // create new panel for manager
        updateComponent(centerPanel);
    }

    public void setAssistanceRequestPage() {
        System.out.println("halo");
        centerPanel.remove(0);
        centerPanel.add(new AssistanceRequestPanel(this), BorderLayout.CENTER);
        updateComponent(centerPanel);
    }

    public void setCheckRequestPanel() {
        centerPanel.remove(0);
        centerPanel.add(new AssistanceRequestReceiverPanel(this), BorderLayout.CENTER);
        updateComponent(centerPanel);
    }

//    public void createOrder() {
//        centerPanel.remove(0);
//        centerPanel.add(new OrderPanel(this), BorderLayout.CENTER);
//        updateComponent(centerPanel);
//    }

    public void createOrder() {
        System.out.println("weszlo");
        JFrame frame = new OrderDashboard();
        frame.setVisible(true);
    }
}
