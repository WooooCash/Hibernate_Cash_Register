package com.example.CashRegister;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderDashboard extends JFrame {
    private Application app;
    private JPanel mainPanel;
    private JTextField searchProductsField;
    private JButton searchProductButton;
    private JList allProductsList;
    private JLabel selectedProductLabel;
    private JButton addProductButton;
    private JList allProductsInOrder;
    private JLabel sumValueLabel;

    public OrderDashboard() {
        String[] ar = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        allProductsList.setListData(ar);
        String[] order = {};
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setTitle("Order Dashboard");

        searchProductButton.addActionListener(e -> {
            String search = searchProductsField.getText();
            System.out.println(search);
        });


        this.pack();
        this.setLocationRelativeTo(null);


        allProductsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selected = allProductsList.getSelectedValue().toString();
                selectedProductLabel.setText(selected);
            }
        });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = selectedProductLabel.getText();
                System.out.println(selected);
                String[] order = {selected};
                allProductsInOrder.setListData(order);
                sumValueLabel.setText("0");
            }
        });

    }
}
