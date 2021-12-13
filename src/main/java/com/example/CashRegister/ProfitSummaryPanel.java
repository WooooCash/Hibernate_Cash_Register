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

    private DatabaseEndpoint db;

    private JPanel banner;
    private JPanel sidebar;

    private JLabel date;
    private JLabel time;

    private JTabbedPane tabbedPane;
    private JPanel profitTab;
    private JPanel employeeTab;
    private JPanel supplierTab;

    private JLabel profitLabel;
    private double dayProfit;
    private double monthProfit;
    private double quarterProfit;
    private double yearProfit;
    private double allTimeProfit;

    private JTable employeeRanking;
    private JTable supplierRanking;

    private String[] columnNames;

    private ArrayList<Object[]> employeeListDay;
    private ArrayList<Object[]> employeeListMonth;
    private ArrayList<Object[]> employeeListQuarter;
    private ArrayList<Object[]> employeeListYear;
    private ArrayList<Object[]> employeeListAllTime;

    private ArrayList<Object[]> supplierListDay;
    private ArrayList<Object[]> supplierListMonth;
    private ArrayList<Object[]> supplierListQuarter;
    private ArrayList<Object[]> supplierListYear;
    private ArrayList<Object[]> supplierListAllTime;

    private JPanel radioPanel;
    private ButtonGroup radioButtons;
    private JRadioButton day;
    private JRadioButton month;
    private JRadioButton quarter;
    private JRadioButton year;
    private JRadioButton allTime;

    private JLabel filterLabel;
    private String dayString;
    private String monthString;
    private String quarterString;
    private String yearString;
    private String allTimeString;

    private JScrollPane erScroll;
    private JScrollPane srScroll;

    public ProfitSummaryPanel(MainFrame mainFrame) {
        LocalDateTime localDT = LocalDateTime.now();
        db = DatabaseEndpoint.getDatabaseEndpoint();

        dayProfit = db.getProfitDay();
        monthProfit = db.getProfitMonth();
        quarterProfit = db.getProfitQuarter();
        yearProfit = db.getProfitYear();
        allTimeProfit = db.getProfitAllTime();

        employeeListDay = db.getRankedEmployeeListDay();
        employeeListMonth = db.getRankedEmployeeListMonth();
        employeeListQuarter= db.getRankedEmployeeListQuarter();
        employeeListYear = db.getRankedEmployeeListYear();
        employeeListAllTime = db.getRankedEmployeeListAllTime();

        supplierListDay = db.getRankedSupplierListDay();
        supplierListMonth = db.getRankedSupplierListMonth();
        supplierListQuarter = db.getRankedSupplierListQuarter();
        supplierListYear = db.getRankedSupplierListYear();
        supplierListAllTime = db.getRankedSupplierListAllTime();



        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MMMM");
        DateTimeFormatter monthNumFormat = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yyyy");

        // FilterLabels
        {
            dayString = "Day: " + localDT.format(dayFormat);
            monthString = "Month: " + localDT.format(monthFormat);
            quarterString = "Quarter: Q" + (int)Math.ceil(Integer.parseInt(localDT.format(monthNumFormat))*4/12.0) +
                    " " + localDT.format(yearFormat);
            yearString = "Year: " + localDT.format(yearFormat);
            allTimeString = "All Time";
        }

        this.setLayout(new BorderLayout());

        banner = new JPanel();
        banner.setPreferredSize(new Dimension(0, 100));
        banner.setLayout(new GridLayout(2, 1));


        sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(150, 0));
        sidebar.setLayout(new BorderLayout());

        // Banner Time and Date
        {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

            date = new JLabel("Initialize", SwingConstants.CENTER);
            date.setText(localDT.format(dayFormat));
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


            filterLabel = new JLabel(dayString, SwingConstants.CENTER);

            sidebar.add(spacerContainer, BorderLayout.NORTH);
            sidebar.add(filterLabel);
        }

        // Tabbed Pane
        {
            tabbedPane = new JTabbedPane();

            // Profit Tab
            {
                profitTab = new JPanel();
                profitTab.setLayout(new GridLayout());

                profitLabel = new JLabel(dayProfit + " zł", SwingConstants.CENTER);
                profitLabel.setFont(new Font("Verdana", Font.PLAIN, 40));

                profitTab.add(profitLabel);
            }

            columnNames = new String[]{"Rank", "Name", "Sales (zł)"};
            // Employee Ranking tab
            {
                employeeTab = new JPanel();
                employeeTab.setLayout(new BorderLayout());

                Object[][] employeeArray = new Object[employeeListDay.size()][];
                employeeArray = employeeListDay.toArray(employeeArray);
                employeeRanking = new JTable(employeeArray, columnNames);

                erScroll = new JScrollPane(employeeRanking);
                employeeRanking.setFillsViewportHeight(true);

                employeeTab.add(erScroll);
            }

            // Supplier Ranking tab
            {
                supplierTab = new JPanel();
                supplierTab.setLayout(new BorderLayout());

                Object[][] supplierArray = new Object[supplierListDay.size()][];
                supplierArray = supplierListDay.toArray(supplierArray);
                supplierRanking = new JTable(supplierArray, columnNames);

                srScroll = new JScrollPane(supplierRanking);
                supplierRanking.setFillsViewportHeight(true);

                supplierTab.add(srScroll);
            }

            tabbedPane.addTab("Profits", profitTab);
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
                changeFilterLabel(dayString);

                changeProfitLabel(dayProfit + " zł");
                changeEmployeeFilter(employeeListDay);
                changeSupplierFilter(supplierListDay);
            }
        });
        month.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFilterLabel(monthString);

                changeProfitLabel(monthProfit + " zł");
                changeEmployeeFilter(employeeListMonth);
                changeSupplierFilter(supplierListMonth);
            }
        });
        quarter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFilterLabel(quarterString);

                changeProfitLabel(quarterProfit + " zł");
                changeEmployeeFilter(employeeListQuarter);
                changeSupplierFilter(supplierListQuarter);
            }
        });
        year.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFilterLabel(yearString);

                changeProfitLabel(yearProfit + " zł");
                changeEmployeeFilter(employeeListYear);
                changeSupplierFilter(supplierListYear);
            }
        });
        allTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFilterLabel(allTimeString);

                changeProfitLabel(allTimeProfit + " zł");
                changeEmployeeFilter(employeeListAllTime);
                changeSupplierFilter(supplierListAllTime);
            }
        });
    }

    private void changeEmployeeFilter(ArrayList<Object[]> list) {
        Object[][] employeeArray = new Object[list.size()][];
        employeeArray = list.toArray(employeeArray);
        employeeRanking = new JTable(employeeArray, columnNames);

        employeeTab.remove(0);
        erScroll = new JScrollPane(employeeRanking);
        employeeRanking.setFillsViewportHeight(true);

        employeeTab.add(erScroll);
        employeeTab.revalidate();
        employeeTab.repaint();
    }

    private void changeSupplierFilter(ArrayList<Object[]> list) {
        Object[][] supplierArray = new Object[list.size()][];
        supplierArray = list.toArray(supplierArray);
        supplierRanking = new JTable(supplierArray, columnNames);

        supplierTab.remove(0);
        srScroll = new JScrollPane(supplierRanking);
        supplierRanking.setFillsViewportHeight(true);

        supplierTab.add(srScroll);
        supplierTab.revalidate();
        supplierTab.repaint();
    }

    private void changeFilterLabel(String label) {
        filterLabel.setText(label);
        sidebar.revalidate();
        sidebar.repaint();
    }

    private void changeProfitLabel(String label) {
        profitLabel.setText(label);
        profitTab.revalidate();
        profitTab.repaint();
    }

}
