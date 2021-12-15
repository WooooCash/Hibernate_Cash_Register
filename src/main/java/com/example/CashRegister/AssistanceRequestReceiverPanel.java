package com.example.CashRegister;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class AssistanceRequestReceiverPanel extends JPanel{
    private Application app;

    private JScrollPane requestScrollPane;
//    private JTextArea textArea;
//    private JButton sendButton;
    Integer[] data = {1,2,4,5};
    JList<String> requestList;
    JPanel leftPanel;
    JPanel rightPanel;
    JLabel name;
    JLabel lastName;
    JLabel dateOfRequest;
    JLabel description;
    JLabel mgrAvailableLabel;

    DatabaseEndpoint databaseEndpoint = DatabaseEndpoint.getDatabaseEndpoint();
    public AssistanceRequestReceiverPanel(MainFrame mainFrame){
        app = Application.getApp();

        this.setLayout(new BorderLayout());
        ArrayList<AssistanceRequestClassForEditing> assistanceRequestList =
                databaseEndpoint.getCustomEmployeeAssistanceEntity();
        Collections.sort(assistanceRequestList, new DateComparator());

        ArrayList<String> stringsToShow = new ArrayList<>();
        for(int i = 0 ; i < assistanceRequestList.size() ; ++i){
            LocalDate date = assistanceRequestList.get( i ).getDateOfRequest().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            stringsToShow.add( date.getDayOfMonth() + "-" +
                    date.getMonth() + "-" +
                    date.getYear() + " : " + assistanceRequestList.get( i ).getName());
        }


        requestList = new JList(stringsToShow.toArray());
        name = new JLabel("Huhi");
        name.setBackground(Color.red);
        lastName = new JLabel("Yas");
        lastName.setBackground(Color.blue);
        description = new JLabel("Kp", SwingConstants.CENTER);
        description.setBackground(Color.pink);
        dateOfRequest = new JLabel("cC");
        name.setBackground(Color.cyan);
        requestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        requestList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged( ListSelectionEvent e ) {
                String html = "<html><body style='width: %1spx'>%1s";
                System.out.println( requestList.getSelectedIndex() );
                AssistanceRequestClassForEditing tempForChangingLabels =
                        assistanceRequestList.get( requestList.getSelectedIndex() );
                LocalDate date = tempForChangingLabels.getDateOfRequest().toInstant()
                        .atZone( ZoneId.systemDefault() ).toLocalDate();
                name.setText( tempForChangingLabels.getName() );
                lastName.setText( tempForChangingLabels.getLastname() );
                description.setText( String.format(html, 200, tempForChangingLabels.getDescription() ) );
                dateOfRequest.setText( date.getDayOfMonth() + "-" + date.getMonth() + "-" + date.getYear() );

            }
        });
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        JLabel l = new JLabel("Lol");
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        String html = "<html><body style='width: %1spx'>%1s";
        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean eu nulla urna. Donec sit amet risus nisl, a porta enim. Quisque luctus, ligula eu scelerisque gravida, tellus quam vestibulum urna, ut aliquet sapien purus sed erat. Pellentesque consequat vehicula magna, eu aliquam magna interdum porttitor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed sollicitudin sapien non leo tempus lobortis. Morbi semper auctor ipsum, a semper quam elementum a. Aliquam eget sem metus.";
        leftPanel.setPreferredSize(new Dimension(200, 0));
//        rightPanel.setPreferredSize(new Dimension(400, 400));
        rightPanel.setBackground(Color.orange);

        requestScrollPane = new JScrollPane(requestList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        leftPanel.add(requestScrollPane, BorderLayout.CENTER);
        JPanel l12 = new JPanel();
        l12.setLayout(new FlowLayout() );
        l12.setPreferredSize(new Dimension(400, 50));
        l12.add(name, BorderLayout.EAST);
        l12.add(lastName, BorderLayout.CENTER);
        rightPanel.add( l12, BorderLayout.NORTH );
        rightPanel.add(dateOfRequest, BorderLayout.SOUTH);
        rightPanel.add(description, BorderLayout.CENTER);

        JPanel mgr = new JPanel();
        mgr.setPreferredSize(new Dimension(0, 50));
        mgrAvailableLabel = new JLabel();
        String managerName = databaseEndpoint.getManagerName(app.getCurrentUserId());
        if (managerName == null) {
            mgr.setBackground(Color.gray);
        } else {
            if (app.getManagerAvailable()) {
                mgrAvailableLabel.setText("Your manager " + managerName + " is available");
                mgr.setBackground(Color.green);
            } else {
                mgrAvailableLabel.setText("Your manager " + managerName + " is not available");
                mgr.setBackground(Color.red);
            }
            mgr.add(mgrAvailableLabel, SwingConstants.CENTER);
        }

        leftPanel.add(mgr, BorderLayout.SOUTH);

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.CENTER);
    }
    private AssistancerequestEntity createAssistanceRequest(Date date, long id, String desc){
        AssistancerequestEntity aRE = new AssistancerequestEntity();
        aRE.setDatetimeofrequest(date);
        aRE.setRequestId(id);
        aRE.setDescription(desc);
        return aRE;
    }
    static class DateComparator implements Comparator<AssistanceRequestClassForEditing> {
        public int compare(AssistanceRequestClassForEditing c1, AssistanceRequestClassForEditing c2) {
            LocalDate firstDate = c1.getDateOfRequest().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate secondDate = c2.getDateOfRequest().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if( firstDate.isAfter( secondDate ) )
                    return -1;
            if( firstDate.isEqual( secondDate ) )
                    return 0;
            return 1;
        }
    }
}
