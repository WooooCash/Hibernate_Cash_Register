package com.example.CashRegister;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssistanceRequestPanel extends JPanel {

    private Application app;

    private JLabel label;
    private JTextArea textArea;
    private JButton sendButton;

    public AssistanceRequestPanel(MainFrame mainFrame) {
        app = Application.getApp();

        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(new Insets(10, 50, 10, 50)));

        label = new JLabel("Description");
        label.setPreferredSize(new Dimension(0, 100));

        textArea = new JTextArea(5, 20);
        sendButton = new JButton("Send Request");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Send", "Cancel"};
                int result = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to send this request?",
                        "Request Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                if (result == 0)
                    app.sendAssistanceRequest(textArea.getText());
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(0, 100));
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(sendButton, BorderLayout.NORTH);

        this.add(label, BorderLayout.NORTH);
        this.add(textArea, BorderLayout.CENTER);

        this.add(bottomPanel, BorderLayout.SOUTH);


    }


}
