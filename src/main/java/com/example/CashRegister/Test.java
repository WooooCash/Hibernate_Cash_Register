package com.example.CashRegister;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.function.Supplier;

public class Test {

    public static void main(String[] args) {

        //create session factory

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ProductcategoryEntity.class)
                .addAnnotatedClass(TaxcategoryEntity.class)
                .buildSessionFactory();

        //create session

        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating new product object...");
            ProductcategoryEntity pc = new ProductcategoryEntity();
            pc.setProductcategoryId(3);
            pc.setName("Categoria_test_2");
            pc.setTaxcategoryId(1);

            session.beginTransaction();


            System.out.println("Saving the product...");
            session.save(pc);

            session.getTransaction().commit();

            System.out.println("Done!");
            session.close();


        } finally {
            factory.close();
        }

    }

}
