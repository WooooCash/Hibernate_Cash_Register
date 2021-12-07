package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private Application app;

    private JFrame frame;
    private JPanel leftPanel;

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

    public void loggedIn(String username) {
        leftPanel.remove(leftPanel.getComponent(0));

        JLabel loggedInText = new JLabel("Welcome " + username + "!");
        leftPanel.add(loggedInText);
        updateComponent(leftPanel);
    }

    private void updateComponent(JComponent component) {
        component.revalidate();
        component.repaint();
    }


}
