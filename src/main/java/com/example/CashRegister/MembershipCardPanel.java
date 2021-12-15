package com.example.CashRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MembershipCardPanel extends Container {
    public MembershipCardPanel(JDialog dialog, OrderDashboard frame) {
        DatabaseEndpoint databaseEndpoint = DatabaseEndpoint.getDatabaseEndpoint();
        setLayout(new GridLayout(2, 1));
        JLabel label = new JLabel("Membership Phone Number");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);
        JTextField membershipCardPhone = new JTextField();
        membershipCardPhone.setPreferredSize(new Dimension(100, 20));
        add(membershipCardPhone);
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 20));
        add(okButton);
        dialog.add(this);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(membershipCardPhone.getText());
//                DatabaseEndpoint databaseEndpoint = DatabaseEndpoint.getDatabaseEndpoint();
                if (membershipCardPhone.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "No phone number inserted. Try again");
                    return;
                }
                long phoneNumber = Long.parseLong( membershipCardPhone.getText() );
//                String outputFromDatabase = "cosik";
                MembershipaccountEntity outputFromDatabase =  databaseEndpoint.getMembershipAccountEntity( phoneNumber );
                if( outputFromDatabase == null ) {
                    JOptionPane.showMessageDialog(null,
                            "This phone number is not in database. Try again");
                    return;
                }
                else {
                    frame.setMembershipCard(outputFromDatabase);
                    JOptionPane.showMessageDialog(null, "Account found :D");
                    frame.actualizeOverallSum();
                }
                dialog.dispose();
            }
        });
        membershipCardPhone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
//                check if number. If not, consume it :O
                if(!Character.isDigit(c) || membershipCardPhone.getText().length()>8)
                    e.consume();
            }
        });
    }
}
