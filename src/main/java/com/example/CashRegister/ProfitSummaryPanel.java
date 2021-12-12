package com.example.CashRegister;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProfitSummaryPanel extends JPanel {

    DatabaseEndpoint db;

    JPanel banner;
    JPanel sidebar;

    JLabel date;
    JLabel time;

    JTabbedPane tabbedPane;
    JPanel profitTab;
    JPanel employeeTab;
    JPanel supplierTab;

    JTable employeeRanking;
    JTable supplierRanking;

    ArrayList<Object[]> employeeList;
    ArrayList<String[]> supplierList;

    JPanel radioPanel;
    ButtonGroup radioButtons;
    JRadioButton day;
    JRadioButton month;
    JRadioButton quarter;
    JRadioButton year;

    JButton increment;
    JButton decrement;

    public ProfitSummaryPanel(MainFrame mainFrame) {
        db = DatabaseEndpoint.getDatabaseEndpoint();
        employeeList = db.getRankedEmployeeList();

        this.setLayout(new BorderLayout());

        banner = new JPanel();
        banner.setPreferredSize(new Dimension(0, 100));
        banner.setLayout(new GridLayout(2, 1));


        sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(150, 0));
        sidebar.setLayout(new BorderLayout());

        // Banner Time and Date
        {

            LocalDateTime localDT = LocalDateTime.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

            date = new JLabel("Initialize", SwingConstants.CENTER);
            date.setText(localDT.format(dateFormat));
            date.setFont(new Font("Verdana", Font.PLAIN, 22));

            time = new JLabel("Initialize", SwingConstants.CENTER);
            time.setText(localDT.format(timeFormat));
            time.setFont(new Font("Verdana", Font.PLAIN, 20));

            banner.add(date);
            banner.add(time);

            Timer t = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    LocalDateTime newLocalDT = LocalDateTime.now();
                    time.setText(newLocalDT.format(timeFormat));
                    banner.repaint();
                }
            });
            t.start();
        }

        // RadioButtons
        {
            day = new JRadioButton("day");
            day.setSelected(true);
            month = new JRadioButton("month");
            quarter = new JRadioButton("quarter");
            year = new JRadioButton("year");

            radioButtons = new ButtonGroup();
            radioButtons.add(day);
            radioButtons.add(month);
            radioButtons.add(quarter);
            radioButtons.add(year);

            radioPanel = new JPanel();
            radioPanel.setLayout(new GridLayout(0, 1));
            radioPanel.setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createBevelBorder(BevelBorder.RAISED),
                            BorderFactory.createBevelBorder(BevelBorder.LOWERED)
                    )
            );

            radioPanel.add(day);
            radioPanel.add(month);
            radioPanel.add(quarter);
            radioPanel.add(year);


            JPanel spacerContainer = new JPanel();
            spacerContainer.setLayout(new BorderLayout());

            JPanel spacer = new JPanel();
            spacer.setPreferredSize(new Dimension(0, 22));

            spacerContainer.add(spacer, BorderLayout.NORTH);
            spacerContainer.add(radioPanel, BorderLayout.CENTER);

            sidebar.add(spacerContainer, BorderLayout.NORTH);
        }

        // Tabbed Pane
        {
            tabbedPane = new JTabbedPane();

            // Profit Tab
            {
                profitTab = new JPanel();
            }

            // Employee Ranking tab
            {
                employeeTab = new JPanel();
                employeeTab.setLayout(new BorderLayout());

                Object[][] lol = new Object[employeeList.size()][];
                lol = employeeList.toArray(lol);
                employeeRanking = new JTable(lol, new String[] {"Name", "Net Sales"});

                JScrollPane erScroll = new JScrollPane(employeeRanking);
                employeeRanking.setFillsViewportHeight(true);

                employeeTab.add(erScroll);
            }

            // Supplier Ranking tab
            {
                supplierTab = new JPanel();


            }

            tabbedPane.addTab("Profit Summary", profitTab);
            tabbedPane.addTab("Employee Ranking", employeeTab);
            tabbedPane.addTab("Supplier Ranking", supplierTab);

            this.add(tabbedPane, BorderLayout.CENTER);
        }


        this.add(banner, BorderLayout.NORTH);
        this.add(sidebar, BorderLayout.WEST);
    }

}
