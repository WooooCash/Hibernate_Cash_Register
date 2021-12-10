package com.example.CashRegister;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AssistanceRequestReceiverPanel extends JPanel{
    private Application app;

    private JScrollPane requestScrollPane;
//    private JTextArea textArea;
//    private JButton sendButton;
    Integer[] data = {1,2,4,5};
    JList<Integer> requestList;

    public AssistanceRequestReceiverPanel(MainFrame mainFrame){
        app = Application.getApp();
        this.setLayout(new BorderLayout());

        Integer[] testData = new Integer[200];
        for (int i = 0; i < 200; i++) testData[i] = i;

        requestList = new JList<Integer>(testData);

        requestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        leftPanel.setPreferredSize(new Dimension(200, 0));

        requestScrollPane = new JScrollPane(requestList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        leftPanel.add(requestScrollPane, BorderLayout.CENTER);


        this.add(leftPanel, BorderLayout.WEST);
    }
}
