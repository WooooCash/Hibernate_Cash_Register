package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OrderPanel extends JPanel {

    private JButton addProductButton;
    private JButton removeProductButton;
    private Application app;



    public OrderPanel(MainFrame mainFrame) {
        app = Application.getApp();
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        // SEARCH PRODUCTS SECTION
        JPanel searchProductsPanel = new JPanel();
        searchProductsPanel.setBackground(Color.RED);
        c.weightx = 0.6;
        c.weighty = 0.2;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        this.add(searchProductsPanel, c);

        // set layout for searchProductsPanel
        searchProductsPanel.setLayout(new BoxLayout(searchProductsPanel, BoxLayout.Y_AXIS));

        // add label to search products
        JLabel searchProductsLabel = new JLabel("Search Products");
        searchProductsPanel.add(searchProductsLabel);
        // add text field to search products
        JTextField searchProductsTextField = new JTextField(20);
        searchProductsPanel.add(searchProductsTextField);
        // add button to search products
        JButton searchProductsButton = new JButton("Search");
        searchProductsPanel.add(searchProductsButton);




        // LIST PRODUCTS SECTION
        JPanel listProductsPanel = new JPanel();
        listProductsPanel.setBackground(Color.BLACK);
        // c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.6;
        c.weighty = 0.8;
        c.gridx = 0;
        c.gridy = 1;
        this.add(listProductsPanel, c);

        JPanel actionsPanel = new JPanel();
        actionsPanel.setBackground(Color.BLUE);
        // c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.4;
        c.weighty = 0.4;
        c.gridx = 1;
        c.gridy = 0;
        this.add(actionsPanel, c);


        JPanel orderPanel = new JPanel();
        orderPanel.setBackground(Color.GREEN);
        // c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.4;
        c.weighty = 0.6;
        c.gridx = 1;
        c.gridy = 1;
        this.add(orderPanel, c);


    }

}
