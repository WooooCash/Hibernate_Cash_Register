package com.example.CashRegister;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
    private JFormattedTextField inputForProductAmount;
    private JLabel priceLabel;


    public OrderDashboard() {
//        it must be final so the taken type could be global
        final String[] selectedWordsInLists = {null, null};
        final int[] selectedIndexInLists = {-1, -1};
        final ArrayList<ProductEntity>[] productList = new ArrayList[]{new ArrayList<>(0)};

        ArrayList<ProductEntity> productOrigin = new ArrayList<ProductEntity>(0);
        ArrayList<String> orderNamings = new ArrayList<String>(0);
        ArrayList<Integer> productAmount = new ArrayList<Integer>(0);

        ArrayList<ProductEntity> orderProducts = new ArrayList<ProductEntity>(0);

//        here importing data for product
        productOrigin.add(create(1, "Cosik", "kg",0.5, 100,"Y",2, 500100300));
        productOrigin.add(create(2, "jakos", "lol",0.7, 200, "N",3, 120123123));

        String [] namingsShowed = new String[productOrigin.size()];
        for(int i =0 ; i < productOrigin.size(); ++i){
            namingsShowed[i] = productOrigin.get(i).getName();
            productList[0].add(productOrigin.get(i));
        }
        allProductsList.setListData(namingsShowed);


        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setTitle("Order Dashboard");
        searchProductButton.addActionListener(e -> {
            ArrayList<String> searchingList = new ArrayList<>();
            String search = searchProductsField.getText();
            productList[0].clear();
            if(search != null) {
                for (int i = 0; i < productOrigin.size(); ++i) {
                    if (productOrigin.get(i).getName().toLowerCase(Locale.ROOT).contains(search)) {
                        searchingList.add(productOrigin.get(i).getName());
                        productList[0].add(productOrigin.get(i));
                    }
                }
                allProductsList.setListData(searchingList.toArray());
            }
            else {
                allProductsList.setListData(productOrigin.toArray());
                productList[0] = new ArrayList<>(productOrigin);
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);


        allProductsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                System.out.println(allProductsList.toString());
                System.out.println(inputForProductAmount.getText());
                if(allProductsList.getSelectedValue() != null) {
                    selectedWordsInLists[0] = allProductsList.getSelectedValue().toString();
                    selectedWordsInLists[1] = null;
                    selectedIndexInLists[0] = allProductsList.getSelectedIndex();
                    selectedIndexInLists[1] = -1;
                    allProductsInOrder.clearSelection();

                    selectedProductLabel.setText(selectedWordsInLists[0]);
                    priceLabel.setText(""+productList[0].get(selectedIndexInLists[0]).getPrice() + "zł");
                    addProductButton.setText("Add Product");
                    inputForProductAmount.setText("0");
                    System.out.println(selectedWordsInLists[0]);
//                    System.out.println(selectedWordsInLists.getName());
                }
            }
        });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String selectedWordsInLists = selectedProductLabel.getText();
//                System.out.println(selectedWordsInLists);
                if(selectedWordsInLists[0] != null) {
                    if (!orderNamings.contains(selectedWordsInLists[0])) {
                        orderNamings.add(selectedWordsInLists[0]);
                        orderProducts.add(productList[0].get(selectedIndexInLists[0]));
                        productAmount.add(Integer.valueOf(inputForProductAmount.getText()));
                    }
                    String[] ord = orderNamings.toArray(new String[orderNamings.size()]);
                    allProductsInOrder.setListData(ord);
                    sumValueLabel.setText("0");
                }
                if(selectedWordsInLists[1] != null ){
                    if(orderNamings.size() != 0) {
                        orderProducts.remove(selectedIndexInLists[1]);
                        orderNamings.remove(selectedIndexInLists[1]);
                        productAmount.remove(selectedIndexInLists[1]);
                        String[] ord = orderNamings.toArray(new String[orderNamings.size()]);
                        allProductsInOrder.setListData(ord);
                    }
                }
            }
        });
        allProductsInOrder.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(allProductsInOrder.getSelectedValue() != null) {
                    selectedWordsInLists[1] = allProductsInOrder.getSelectedValue().toString();
                    selectedWordsInLists[0] = null;
                    selectedIndexInLists[1] = allProductsInOrder.getSelectedIndex();
                    selectedIndexInLists[0] = -1;
                    allProductsList.clearSelection();
                    System.out.println(selectedWordsInLists[0] == null ? "yas1": "nay1");
                    System.out.println(selectedWordsInLists[1] == null ? "yas2": "nay2");

                    addProductButton.setText("Remove Product");
                    priceLabel.setText(""+ orderProducts.get(selectedIndexInLists[1]).getPrice() + "zł");

                    System.out.println(selectedWordsInLists);
                    selectedProductLabel.setText(selectedWordsInLists[1]);
                }
            }
        });

        inputForProductAmount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if(!Character.isDigit(c))
                    e.consume();
                if(inputForProductAmount.getText().equals(""))
                    inputForProductAmount.setText("0");
            }
        });
        inputForProductAmount.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println(evt);
            }
        });
    }
    //    private DatabaseEndpoint databaseEndpoint = DatabaseEndpoint.getDatabaseEndpoint();
    public ProductEntity create(long id, String name, String unitScale, double Price, long instock,
                                String requiresAmount, long ProductCategory, long NIP ){
        ProductEntity pE = new ProductEntity();
        pE.setProductId(id);
        pE.setName(name);
        pE.setUnitscale(unitScale);
        pE.setPrice(Price);
        pE.setInstock(instock);
        pE.setRequiresamount(requiresAmount);
        pE.setProductcategoryId(ProductCategory);
        pE.setSuppliernip(NIP);
        return pE;
    }
}

