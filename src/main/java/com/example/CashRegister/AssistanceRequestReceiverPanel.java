package com.example.CashRegister;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AssistanceRequestReceiverPanel extends JPanel{
    private Application app;

    private JScrollPane panelForReceivedRequests;
    private JTextArea textArea;
    private JButton sendButton;
    Integer[] data = {1,2,4,5};
    JList<Integer> l = new JList<Integer>(data);
//    public AssistanceRequestReceiverPanel(){

//    }

    public AssistanceRequestReceiverPanel(MainFrame mainFrame){
        app = Application.getApp();
        this.setLayout(new BorderLayout());
//        this.setBorder(new EmptyBorder(new Insets(10, 50, 10, 50)));

//        panelForReceivedRequests = new JScrollPane();
        JPanel bottomPanel = new JPanel();

        panelForReceivedRequests = new JScrollPane(bottomPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//        mainFrame.getContentPane().setLayout(null);
//        mainFrame.getContentPane().add(panelForReceivedRequests);
//        this.add(new JLabel("somethin"));
//        panelForReceivedRequests.setViewportView(bottomPanel);

        bottomPanel.setPreferredSize(new Dimension(0, 100));
        for(int i =0 ; i< 1000; i++){
            bottomPanel.add(new JButton("somethin"));
        }
        this.add(bottomPanel);
//        this.add(new JButton("somethin"));
//        this.add(new JButton("somethin"));
//        this.add(new JButton("somethin"));
//        this.add(panelForReceivedRequests, BorderLayout.NORTH);

//        this.setBorder(new EmptyBorder(new Insets(10, 50, 10, 50)));


    }

}
