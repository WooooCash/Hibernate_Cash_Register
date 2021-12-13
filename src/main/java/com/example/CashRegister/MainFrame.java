package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainFrame extends JFrame {

    private Application app;

    private JFrame frame;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JFrame Productframe;
    private JButton logoutButton;
    private JButton dashboardRedirectButton;

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
        dashboardRedirectButton = new JButton("Go to Dashboard");
        leftPanel.add(dashboardRedirectButton);
        dashboardRedirectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Yes", "No"};
                int result = JOptionPane.showOptionDialog(null,
                        "Do you really want to go to the Dashboard?",
                        "Request Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);
                if(result == 0)
                    setDashboardPage(status);
            }
        });

        logoutButton = new JButton("Logout");
        leftPanel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Yes", "No"};
                int result = JOptionPane.showOptionDialog(null,
                        "Do you really want to logout?",
                        "Request Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);
                if(result == 0) {
                    leftPanel.removeAll();
                    JButton loginButton = new JButton("Login");
                    loginButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            LoginFrame loginFrame = new LoginFrame();
                        }
                    });
                    leftPanel.add(loginButton);
                    updateComponent(leftPanel);
                    centerPanel.removeAll();
                    updateComponent(centerPanel);
                }
            }
        });

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
        Productframe = new OrderDashboard(this);
        Productframe.setVisible(true);
    }
    public void destroyOrder(){
        Productframe.dispose();
    }

    public void setProfitSummaryPage() {
        centerPanel.remove(0);
        centerPanel.add(new ProfitSummaryPanel(this), BorderLayout.CENTER);
        updateComponent(centerPanel);
    }
}
