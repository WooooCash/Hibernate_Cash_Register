package com.example.CashRegister;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

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
//    private DatabaseEndpoint databaseEndpoint = DatabaseEndpoint.getDatabaseEndpoint();

    public OrderDashboard() {
        String[] ar = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        ArrayList<ProductEntity> ar2 = new ArrayList<>(0);
        ProductEntity p = new ProductEntity();
        ProductEntity p2 = new ProductEntity();
        p.setProductId(1);
        p.setName("Cosik");
        p.setUnitscale("kg");
        p.setPrice(0.5);
        p.setInstock(100);
        p.setRequiresamount("Y");
        p.setProductcategoryId(2);
        p.setSuppliernip(500100300);
        ar2.add(p);
        p2.setProductId(1);
        p2.setName("Bruh");
        p2.setUnitscale("kg");
        p2.setPrice(0.5);
        p2.setInstock(100);
        p2.setRequiresamount("Y");
        p2.setProductcategoryId(2);
        p2.setSuppliernip(500100300);
        ar2.add(p2);
//        ar2 = databaseEndpoint.getAllProducts();
//        allProductsList.setListData(ar);
        String [] namings = new String[ar2.size()];
        for(int i =0 ; i < ar2.size(); ++i){
            namings[i] = ar2.get(i).getName();
        }
        allProductsList.setListData(namings);
        ArrayList<String> order = new ArrayList<String>(0);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setTitle("Order Dashboard");

        searchProductButton.addActionListener(e -> {
//            ArrayList<String> searchingList = new ArrayList<String>(0);
            ArrayList<String> searchingList = new ArrayList<>();

            String search = searchProductsField.getText();
            if(search != null) {
                for (int i = 0; i < ar2.size(); ++i) {
                    if (ar2.get(i).getName().toLowerCase(Locale.ROOT).contains(search))
                        searchingList.add(ar2.get(i).getName());
                }
                allProductsList.setListData(searchingList.toArray());
            }
            else
                allProductsList.setListData(ar2.toArray());
//            if(search != null) {
//                for (String word : ar)
//                    if (word.contains(search))
//                        searchingList.add(word);
                System.out.println(search);
//            }

        });

        System.out.println("something");
        this.pack();
        this.setLocationRelativeTo(null);


        allProductsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(allProductsList.toString());
                if(allProductsList.getSelectedValue() != null) {
                    String selected = allProductsList.getSelectedValue().toString();
                    selectedProductLabel.setText(selected);
//                    System.out.println(selected.getName());
                }
            }
        });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = selectedProductLabel.getText();
                System.out.println(selected);
                if(!order.contains(selected)) {
                    for (ProductEntity p : ar2)
                        if (p.getName().contains(selected))
                            order.add(selected);
                }
                String[] ord = order.toArray(new String[order.size()]);
                allProductsInOrder.setListData(ord);
                sumValueLabel.setText("0");
            }
        });

    }
}
