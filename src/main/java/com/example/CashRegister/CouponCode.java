package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CouponCode extends Container {
    public CouponCode(JDialog dialog, OrderDashboard frame) {
        setLayout(new GridLayout(2, 1));
        JLabel label = new JLabel("Coupon Code");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);
        JTextField couponField = new JTextField();
        couponField.setPreferredSize(new Dimension(100, 20));
        add(couponField);
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 20));
        add(okButton);
        dialog.add(this);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(couponField.getText());
                DatabaseEndpoint databaseEndpoint = DatabaseEndpoint.getDatabaseEndpoint();
                if (couponField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "No code inserted. Try again");
                    dialog.dispose();
                    return;
                }
                long couponCode = Long.parseLong( couponField.getText() );
                String outputFromDatabase =  databaseEndpoint.getCouponEntity( couponCode );
                if( outputFromDatabase.equals( "" ) ) {
                    JOptionPane.showMessageDialog(null, "No coupon in database. Try again");
                }
                else {
                    frame.setCoupon(outputFromDatabase, couponField.getText());
                    JOptionPane.showMessageDialog(null, "Coupon applied :D");
                    frame.actualizeOverallSum();
                }
                dialog.dispose();
            }
        });
        couponField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
//                check if number. If not, consume it :O
                if(!Character.isDigit(c))
                    e.consume();
            }
        });
    }
}
