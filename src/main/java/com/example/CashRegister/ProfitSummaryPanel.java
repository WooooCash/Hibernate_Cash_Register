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

    ArrayList<Object[]> employeeListDay;
    ArrayList<Object[]> employeeListMonth;
    ArrayList<Object[]> employeeListQuarter;
    ArrayList<Object[]> employeeListYear;
    ArrayList<Object[]> employeeListAllTime;

    ArrayList<Object[]> supplierListDay;
    ArrayList<Object[]> supplierListMonth;
    ArrayList<Object[]> supplierListQuarter;
    ArrayList<Object[]> supplierListYear;
    ArrayList<Object[]> supplierListAllTime;

    JPanel radioPanel;
    ButtonGroup radioButtons;
    JRadioButton day;
    JRadioButton month;
    JRadioButton quarter;
    JRadioButton year;
    JRadioButton allTime;

    JButton increment;
    JButton decrement;

    JScrollPane erScroll;

    public ProfitSummaryPanel(MainFrame mainFrame) {
        db = DatabaseEndpoint.getDatabaseEndpoint();

        employeeListDay = db.getRankedEmployeeListDay();
        employeeListMonth = db.getRankedEmployeeListMonth();
        employeeListQuarter= db.getRankedEmployeeListQuarter();
        employeeListYear = db.getRankedEmployeeListYear();
        employeeListAllTime = db.getRankedEmployeeListAllTime();


        supplierListAllTime = db.getRankedSupplierListAllTime();

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

            Timer t = new Timer(100, new ActionListener() {
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
            allTime = new JRadioButton("all time");

            setButtonActions();

            radioButtons = new ButtonGroup();
            radioButtons.add(day);
            radioButtons.add(month);
            radioButtons.add(quarter);
            radioButtons.add(year);
            radioButtons.add(allTime);

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
            radioPanel.add(allTime);


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

                Object[][] employeeArray = new Object[employeeListAllTime.size()][];
                employeeArray = employeeListAllTime.toArray(employeeArray);
                employeeRanking = new JTable(employeeArray, new String[] {"Name", "Sales"});

                erScroll = new JScrollPane(employeeRanking);
                employeeRanking.setFillsViewportHeight(true);

                employeeTab.add(erScroll);
            }

            // Supplier Ranking tab
            {
                supplierTab = new JPanel();
                supplierTab.setLayout(new BorderLayout());

                Object[][] supplierArray = new Object[supplierListAllTime.size()][];
                supplierArray = supplierListAllTime.toArray(supplierArray);
                supplierRanking = new JTable(supplierArray, new String[] {"Name", "Sales"});

                JScrollPane srScroll = new JScrollPane(supplierRanking);
                supplierRanking.setFillsViewportHeight(true);

                supplierTab.add(srScroll);
            }

            tabbedPane.addTab("Profit Summary", profitTab);
            tabbedPane.addTab("Employee Ranking", employeeTab);
            tabbedPane.addTab("Supplier Ranking", supplierTab);

            this.add(tabbedPane, BorderLayout.CENTER);
        }


        this.add(banner, BorderLayout.NORTH);
        this.add(sidebar, BorderLayout.WEST);
    }

    private void setButtonActions() {
        day.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] employeeArray = new Object[employeeListDay.size()][];
                employeeArray = employeeListDay.toArray(employeeArray);
                employeeRanking = new JTable(employeeArray, new String[] {"Name", "Sales"});

                employeeTab.remove(0);
                erScroll = new JScrollPane(employeeRanking);
                employeeRanking.setFillsViewportHeight(true);

                employeeTab.add(erScroll);
                employeeTab.revalidate();
                employeeTab.repaint();
            }
        });
        month.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] employeeArray = new Object[employeeListMonth.size()][];
                employeeArray = employeeListMonth.toArray(employeeArray);
                employeeRanking = new JTable(employeeArray, new String[] {"Name", "Sales"});

                employeeTab.remove(0);
                erScroll = new JScrollPane(employeeRanking);
                employeeRanking.setFillsViewportHeight(true);

                employeeTab.add(erScroll);
                employeeTab.revalidate();
                employeeTab.repaint();
            }
        });
        quarter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] employeeArray = new Object[employeeListQuarter.size()][];
                employeeArray = employeeListQuarter.toArray(employeeArray);
                employeeRanking = new JTable(employeeArray, new String[] {"Name", "Sales"});

                employeeTab.remove(0);
                erScroll = new JScrollPane(employeeRanking);
                employeeRanking.setFillsViewportHeight(true);

                employeeTab.add(erScroll);
                employeeTab.revalidate();
                employeeTab.repaint();
            }
        });
        year.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] employeeArray = new Object[employeeListYear.size()][];
                employeeArray = employeeListYear.toArray(employeeArray);
                employeeRanking = new JTable(employeeArray, new String[] {"Name", "Sales"});

                employeeTab.remove(0);
                erScroll = new JScrollPane(employeeRanking);
                employeeRanking.setFillsViewportHeight(true);

                employeeTab.add(erScroll);
                employeeTab.revalidate();
                employeeTab.repaint();
            }
        });
        allTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] employeeArray = new Object[employeeListAllTime.size()][];
                employeeArray = employeeListAllTime.toArray(employeeArray);
                employeeRanking = new JTable(employeeArray, new String[] {"Name", "Sales"});

                employeeTab.remove(0);
                erScroll = new JScrollPane(employeeRanking);
                employeeRanking.setFillsViewportHeight(true);

                employeeTab.add(erScroll);
                employeeTab.revalidate();
                employeeTab.repaint();
            }
        });

    }

}
