package com.example.CashRegister;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JDialog {

    private JDialog frame;
    private JFrame mainFrame;

    LoginFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        frame = new JDialog(mainFrame, "Login", true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new LoginPanel(frame));
        frame.pack();
        frame.setVisible(true);
    }



}
