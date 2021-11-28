package com.example.CashRegister;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.googlecode.lanterna.terminal.*;

import java.io.IOException;
import java.util.*;

import static java.lang.Thread.sleep;

public class Test {


    public Test() throws IOException {
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        //create session factory

        DefaultTerminalFactory dtf = new DefaultTerminalFactory();

        Screen screen = null;
        try {
            screen = dtf.createScreen();
            screen.startScreen();

            // Create panel to hold components
            Panel panel = new Panel();
            panel.setLayoutManager(new GridLayout(2));
            BasicWindow window = new BasicWindow();
            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));


            TerminalSize size = new TerminalSize(14, 10);

            // add title to panel
            Label title = new Label("Welcome to the Cash Register");
            title.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2));
            panel.addComponent(title);

            panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
            panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
            panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
            panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));


            // add input to login
            Label worker_id = new Label("Worker ID:");
            panel.addComponent(worker_id);
            TextBox loginBox = new TextBox();
            panel.addComponent(loginBox);

            // add horizontal line
            panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
            panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));

            // add button to login on left side
            Button login = new Button("Login");
            panel.addComponent(login);

            // if login is pressed, check if worker id is correct
            login.addListener(new Button.Listener() {
                                  @Override
                                  public void onTriggered(Button button) {
                                      if (ifEmployeeExists(loginBox.getText())) {
                                          gui.addWindowAndWait(new BasicWindow("Welcome"));
                                      } else {
                                          gui.addWindowAndWait(new BasicWindow("Wrong ID"));
                                      }
                                      gui.removeWindow(window);
                                  }
                              }
            );


            // if worker id is correct, open new window
            // if worker id is incorrect, display error message


//            CheckBoxList<String> list = new CheckBoxList<>(size);
//            panel.addComponent(list);
//            for (int i = 0; i < 10; i++) {
//                list.addItem("item " + i);
//            }

//            aaaaaaaaaaaaaaaaaaaaaxxxdfasdfaspodffastaf
            //  add panel to window
            window.setComponent(panel);


//
//            new Button("Fuck me", new Runnable() {
//                @Override
//                public void run() {
//                    //List<String> checkedItems = list.getCheckedItems();
//
//                    System.out.println(checkedItems);
//
//                    File input = new FileDialogBuilder()
//                            .setTitle("Open File")
//                            .setDescription("Choose a file")
//                            .setActionLabel("Open")
//                            .build()
//                            .showDialog(gui);
//                }
//            }).addTo(panel);


// Create window to hold the panel
            window.setComponent(panel);


            // Create gui and start gui
            gui.addWindowAndWait(window);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (screen != null) {
                try {
                    screen.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(ProductcategoryEntity.class)
//                .addAnnotatedClass(TaxcategoryEntity.class)
//                .buildSessionFactory();
//
//        //create session
//
//        Session session = factory.getCurrentSession();

//        try {
//            System.out.println("Creating new product object...");
//            ProductcategoryEntity pc = new ProductcategoryEntity();
//            pc.setProductcategoryId(3);
//            pc.setName("Categoria_test_2");
//            pc.setTaxcategoryId(1);
//
//            session.beginTransaction();
//
//
//            System.out.println("Saving the product...");
//            session.save(pc);
//
//            session.getTransaction().commit();
//
//            System.out.println("Done!");
//            session.close();
//
//
//        } finally {
//            factory.close();
//        }

    }

    // search id employee in database
    private static boolean ifEmployeeExists(String id) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(EmployeeEntity.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<EmployeeEntity> employeeEntityList = session.createQuery("from EmployeeEntity").list();
        for (EmployeeEntity employeeEntity : employeeEntityList) {
            if (employeeEntity.getEmployeeId() == Integer.parseInt(id)) {
                System.out.println(employeeEntity.getEmployeeId());
                return true;
            }
        }
        session.getTransaction().commit();
        session.close();
        return false;
    }

}

