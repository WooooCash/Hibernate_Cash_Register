package com.example.CashRegister;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Locale;

public class OrderDashboard extends JFrame {
    private InvoiceEntity invoiceEntity = new InvoiceEntity();
    private boolean invoiceCreated = false;

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
    private double percentDiscount = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP).floatValue();
    private double permaDiscount = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP).floatValue();
    private long couponID = -1;
    private long membershipAccountId = -1;
    private JButton finaliseOrderButton;
    private JButton addCouponButton;
    private JLabel unitscaleLabel;
    private JButton addInvoiceButton;
    private JRadioButton cashRadioButton;
    private JRadioButton cardRadioButton;
    private JFormattedTextField amountOfCash;
    private JLabel amountOfCashLabel;
    private JButton addMembershipCardButton;
    DatabaseEndpoint databaseEndpoint = DatabaseEndpoint.getDatabaseEndpoint();
    private ArrayList<ProductEntity> orderProducts;
    private ArrayList<Integer> productAmount;
    public OrderDashboard(MainFrame mainFrame) {
//        it must be final so the taken type could be global
        final String[] selectedWordsInLists = {null, null};
        final int[] selectedIndexInLists = {-1, -1};
        final ArrayList<ProductEntity>[] productList = new ArrayList[]{new ArrayList<>(0)};

        ArrayList<ProductEntity> productOrigin = databaseEndpoint.getAllProducts();

        ArrayList<String> orderNamings = new ArrayList<String>(0);
        productAmount = new ArrayList<Integer>(0);

        orderProducts = new ArrayList<ProductEntity>(0);

        ButtonGroup group = new ButtonGroup();
        group.add(cashRadioButton);
        group.add(cardRadioButton);
        cashRadioButton.setSelected(true);
//        here importing data for product
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
                    if (productOrigin.get(i).getName().toLowerCase(Locale.ROOT)
                            .contains(search.toLowerCase(Locale.ROOT))) {
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
                if( allProductsList.getSelectedValue() != null ) {
                    selectedWordsInLists[0] = allProductsList.getSelectedValue().toString();
                    selectedWordsInLists[1] = null;
                    selectedIndexInLists[0] = allProductsList.getSelectedIndex();
                    selectedIndexInLists[1] = -1;
                    allProductsInOrder.clearSelection();

                    selectedProductLabel.setText(selectedWordsInLists[0]);
                    priceLabel.setText(""+productList[0].get( selectedIndexInLists[0] ).getPrice() + "zł");
                    addProductButton.setText("Add Product");
                    inputForProductAmount.setText("1");
                    unitscaleLabel.setText( "" + productList[0].get( selectedIndexInLists[0] ).getUnitscale() );
                    inputForProductAmount.setEditable(true);

                }
            }
        });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productAmountValue = inputForProductAmount.getText();
                if(selectedWordsInLists[0] != null) {
                    if ( !orderNamings.contains( selectedWordsInLists[0] ) ) {
                        orderNamings.add( selectedWordsInLists[0] );
                        orderProducts.add( productList[0].get( selectedIndexInLists[0] ) );
                        productAmount.add(
                            productAmountValueNullOrZero(productAmountValue) ? 1 :
                                Integer.parseInt( productAmountValue ) );
                    }
                    else{
                        for( int i = 0 ; i < orderNamings.size() ; ++i ){
                            if( orderNamings.get(i).equals( selectedWordsInLists[0] ) ) {
                                productAmount.set( i, productAmount.get( i ) +
                                        ( productAmountValueNullOrZero( productAmountValue ) ?
                                                1 : Integer.parseInt( productAmountValue ) ) );
                            }
                        }
                    }
                    String[] ord = orderNamings.toArray(new String[0]);
                    sumValueLabel.setText( "" + computeOrderSum( orderProducts, productAmount ) + "zł");
                    allProductsInOrder.setListData(ord);
                }
                if(selectedWordsInLists[1] != null ){
                    if(orderNamings.size() != 0) {
                        orderProducts.remove(selectedIndexInLists[1]);
                        orderNamings.remove(selectedIndexInLists[1]);
                        productAmount.remove(selectedIndexInLists[1]);
                        String[] ord = orderNamings.toArray(new String[orderNamings.size()]);
                        sumValueLabel.setText( "" + computeOrderSum( orderProducts, productAmount ) + "zł");
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

                    addProductButton.setText("Remove Product");
                    inputForProductAmount.setText( String.valueOf( productAmount.get( selectedIndexInLists[1] ) ) );
                    inputForProductAmount.setEditable(false);
                    unitscaleLabel.setText( orderProducts.get( selectedIndexInLists[1] ).getUnitscale() );


                    priceLabel.setText(""+ orderProducts.get(selectedIndexInLists[1]).getPrice() + "zł");

                    selectedProductLabel.setText(selectedWordsInLists[1]);
                }
            }
        });

        inputForProductAmount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
//                check if number. If not, consume it :O
                if(!Character.isDigit(c))
                    e.consume();
            }
        });

        addCouponButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(percentDiscount <= 0 || percentDiscount <= 0) {
                    JDialog dialog = new JDialog(OrderDashboard.this, "Coupon Code");
                    dialog.setContentPane(new CouponCode(dialog, OrderDashboard.this));
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                }
                else {
                    percentDiscount = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP).floatValue();
                    permaDiscount = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP).floatValue();
                    couponID = -1;
                    actualizeOverallSum();
                    addCouponButton.setText("Add coupon");
                    addCouponButton.setForeground(Color.black);
                }
            }
        });
        finaliseOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String typeOfTransaction = "";
                float computedOrderSum = computeOrderSum(orderProducts, productAmount);
                if(orderProducts.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No products in Order. Add something");
                    return;
                }
                if( amountOfCash.isEditable() ) {
                    typeOfTransaction = "gotowka";
                    if( amountOfCash.getText().equals( "" ) || amountOfCash.getText().equals( "." ) )
                        amountOfCash.setText( "0" );
                    System.out.println( Float.valueOf( amountOfCash.getText() ) );
                    float sumOfCash = BigDecimal.valueOf( Float.valueOf( amountOfCash.getText() ) ).
                            setScale(2, RoundingMode.HALF_UP).floatValue();

                    if( sumOfCash < computedOrderSum ){
                        JOptionPane.showMessageDialog(null,
                                "Too small amount of cash to cover the price of cart",
                                "Error while checking",
                                JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                }
                else
                    typeOfTransaction = "karta";

                Object[] options = {"Send", "Cancel"};
                int result = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to finalize Order?","Request Confirmation",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[1]);
                if (result == 0) {
                    if (invoiceCreated) {
                        databaseEndpoint.addInvoiceEntity(invoiceEntity);
                    }
                    int invoiceID = invoiceCreated ? (int) invoiceEntity.getInvoiceId() : 404;
                    long idOfEmployee = (long) app.getApp().getCurrentUserId();
                    //                    id 404, because if it's default
                    long couponIdForForm = couponID == -1 ? 404 : couponID;
                    long membershipAccount = membershipAccountId == -1 ? 404 : membershipAccountId;
                    long order_id = databaseEndpoint.addOrderEntity(
                            (double) computedOrderSum,
                            1,
                            typeOfTransaction,
                            membershipAccount,
                            invoiceID,
                            couponIdForForm,
                            idOfEmployee
                    );
                    for (int i = 0; i < orderProducts.size(); ++i)
                        databaseEndpoint.addProductOrderEntity(
                                productAmount.get(i),
                                orderProducts.get(i).getProductId(),
                                order_id
                        );
                    if(invoiceCreated) {
                        invoiceEntity.setTaxamount(databaseEndpoint.getTaxAmount(order_id));
                        invoiceEntity.setNetprice((float) (computedOrderSum - invoiceEntity.getTaxamount()));
                        databaseEndpoint.updateInvoiceEntity(invoiceEntity);
                    }
                    String specialProductInfo = "";
                    if(membershipAccount != 404)
                        specialProductInfo = databaseEndpoint.getSpecialProduct(computedOrderSum);
                    if( typeOfTransaction.equals("gotowka") ) {
                        float sumOfCash = BigDecimal.valueOf(Float.valueOf(amountOfCash.getText())).
                                setScale(2, RoundingMode.HALF_UP).floatValue();
                        if ( sumOfCash >= computedOrderSum) {

                            float change = BigDecimal.valueOf(sumOfCash - computedOrderSum).
                                    setScale(2, RoundingMode.HALF_UP).floatValue();
                            JOptionPane.showMessageDialog(null,
                                    "Cash from customer was equal: " + sumOfCash + "zl \n"+
                                    "Cash from Order was equal: "+computedOrderSum + "zl \n"+
                                    "Change: "+ (change) + "zl"+specialProductInfo,
                                    "Change",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    else{
                        if( !specialProductInfo.equals("") )
                            JOptionPane.showMessageDialog(null,
                                    specialProductInfo,
                                    "Special Product Info",
                                    JOptionPane.PLAIN_MESSAGE);
                    }
                    if (invoiceCreated) {
                        databaseEndpoint.updateInvoiceEntity(invoiceEntity);
                    }
                    mainFrame.destroyOrder();
                }
            }
        });
        addMembershipCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(membershipAccountId == -1) {
                    JDialog dialog = new JDialog(OrderDashboard.this, "MebershipCard");
                    dialog.setContentPane(new MembershipCardPanel(dialog, OrderDashboard.this));
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                }
                else {
                    membershipAccountId = -1;
                    addMembershipCardButton.setForeground(Color.black);
                    addMembershipCardButton.setText("Add membership card");
                }
            }
        });
        addInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!invoiceCreated){
                    JDialog dialog = new JDialog(OrderDashboard.this, "Invoice");
                    dialog.setContentPane(new Invoice(dialog, OrderDashboard.this));
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setVisible(true);
                }
                else {
                    invoiceCreated = false;
                    addInvoiceButton.setText("Add Invoice");
                    addInvoiceButton.setForeground(Color.black);
                }
            }
        });

        cashRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountOfCashLabel.setText( "How much cash?" );
                amountOfCash.setEditable(true);
            }
        });
        cardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountOfCashLabel.setText( "Cash not available" );
                amountOfCash.setEditable(false);
            }
        });
        amountOfCash.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                char dot = '.';
                if( Character.compare(dot, c) == 0 ) {
                    if (amountOfCash.getText().contains("."))
                        e.consume();
                }
                else if( !Character.isDigit(c) )
                    e.consume();
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
    private boolean productAmountValueNullOrZero(String productAmountValue){
        if( productAmountValue == null || productAmountValue.equals("") || Integer.parseInt( productAmountValue ) == 0 )
            return true;
        return false;
    }
    private float computeOrderSum(ArrayList<ProductEntity> productListInOrder, ArrayList<Integer> productAmount){
        float sum = 0.0f;
        for(int i = 0 ; i < productListInOrder.size() ; ++i)
            sum += (float) ( productListInOrder.get(i).getPrice()
                    * Float.valueOf( String.valueOf( productAmount.get( i ) ) ) );
        sum = sum * (float)( (100.0f - percentDiscount) / 100.0f );
        sum = sum - (float)permaDiscount;
        if(sum <= 0)
            sum = 0.0f;
        sum = BigDecimal.valueOf(sum).setScale(2, RoundingMode.HALF_UP).floatValue();
        return sum;
    }
    public void actualizeOverallSum(){
        sumValueLabel.setText( "" + computeOrderSum( orderProducts, productAmount ) + "zł");
    }
    public void setCoupon(String couponTextFromDatabase, String couponCode){
        String[] output = couponTextFromDatabase.split(" ");
        if( output[0].equals( "procent" ) ) {
            percentDiscount = Double.parseDouble(output[1]);
            couponID = Long.parseLong( output[2] );
            percentDiscount = BigDecimal.valueOf(percentDiscount).setScale(2, RoundingMode.HALF_UP).floatValue();
        }
        else {
            permaDiscount = Double.parseDouble(output[1]);
            couponID = Long.parseLong( output[2] );
            permaDiscount = BigDecimal.valueOf(permaDiscount).setScale(2, RoundingMode.HALF_UP).floatValue();
        }
        addCouponButton.setText("Remove coupon with code: " + couponCode);
        addCouponButton.setForeground(Color.red);

    }
    public void setMembershipCard(MembershipaccountEntity mE){
        addMembershipCardButton.setText("Remove account with code: " + mE.getPhonenumber());
        addMembershipCardButton.setForeground(Color.red);
        membershipAccountId = mE.getMembershipId();
    }

    public void addInvoice(String nip, String firstName, String lastName, String address) {
        invoiceEntity.setNip(Long.parseLong(nip));
        invoiceEntity.setFirstname(firstName);
        invoiceEntity.setLastname(lastName);
        invoiceEntity.setAddress(address);
        invoiceCreated = true;
        addInvoiceButton.setText("Remove invoice with ID:" + invoiceEntity.getNip());
        addInvoiceButton.setForeground(Color.red);

    }
}

