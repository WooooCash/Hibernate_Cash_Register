package com.example.CashRegister;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Locale;

public class DatabaseEndpoint {
    private static DatabaseEndpoint databaseEndpoint = new DatabaseEndpoint();
    private static SessionFactory factory;

    private DatabaseEndpoint() {
        factory =
            new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(AssistancerequestEntity.class)
                .addAnnotatedClass(CouponEntity.class)
                .buildSessionFactory();
    }


    public static DatabaseEndpoint getDatabaseEndpoint() {
        return databaseEndpoint;
    }
    public static boolean managerOrCashier(String name){
        Session session = factory.getCurrentSession();

        String sql = "select name, password from employee where employee_id not in (select nvl(managerid,0) from employee) and name = :employeeName";
        Query query = session.createQuery(sql);
        query.setParameter("employee_name", name);
        List is_employee_manager = query.list();
        session.close();
        return is_employee_manager != null;
    }
    public static boolean login(String name, String password){
        Session session = factory.getCurrentSession();
        String sql = "select 1 from employee where name = :name and password = :password";
        Query query = session.createQuery(sql);
        query.setParameter("employee_name", name.toLowerCase(Locale.ROOT))
                .setParameter("password", password);
        List nameInDatabase = query.list();
        session.close();
        return nameInDatabase != null;
    }


}
