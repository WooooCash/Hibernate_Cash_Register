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

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame loginFrame = new LoginFrame(frame);
            }
        });

        leftPanel.add(loginButton);

        frame.add(leftPanel, BorderLayout.WEST);

        frame.pack();
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


        //Add content based on some sort of int/enum/bool depending on role
        if (status == 0) {} // create new panel for regular employee
        else if (status == 1) {} // create new panel for manager
//        centerPanel = new JPanel(); //switch out for custom panel class? based on user priviledges
//        centerPanel.setBackground(Color.blue);
        centerPanel = new EmployeeDashboardPanel();

        frame.add(centerPanel, BorderLayout.CENTER);



    }

    private void updateComponent(JComponent component) {
        component.revalidate();
        component.repaint();
    }


}
