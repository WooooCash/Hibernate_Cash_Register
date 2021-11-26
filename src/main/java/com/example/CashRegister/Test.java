package com.example.CashRegister;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.googlecode.lanterna.terminal.*;
import org.hibernate.internal.build.AllowSysOut;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.function.Supplier;
import java.util.regex.Pattern;

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
            CheckBoxList<String> list = new CheckBoxList<>(size);
            panel.addComponent(list);
            for (int i = 0; i < 10; i++) {
                list.addItem("item " + i);
            }


            panel.addComponent(new Separator(Direction.HORIZONTAL).setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)));

//
            new Button("Fuck me", new Runnable() {
                @Override
                public void run() {
                    List<String> checkedItems = list.getCheckedItems();

                    System.out.println(checkedItems);

                    File input = new FileDialogBuilder()
                            .setTitle("Open File")
                            .setDescription("Choose a file")
                            .setActionLabel("Open")
                            .build()
                            .showDialog(gui);
                }
            }).addTo(panel);

// Create window to hold the panel
            window.setComponent(panel);


            // Create gui and start gui
            gui.addWindowAndWait(window);

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (screen != null) {
                try {
                    screen.close();
                } catch(IOException e) {
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

}

