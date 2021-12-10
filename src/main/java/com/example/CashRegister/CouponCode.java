package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CouponCode extends Container {
    public CouponCode(JDialog dialog) {
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
                dialog.dispose();
            }
        });
    }
}
