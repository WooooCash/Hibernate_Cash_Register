package com.example.CashRegister;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class DatabaseEndpoint extends Thread {
    private static DatabaseEndpoint databaseEndpoint = new DatabaseEndpoint();
    private static SessionFactory factory;

    private DatabaseEndpoint() {
        factory =
            new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(AssistancerequestEntity.class)
                .addAnnotatedClass(CouponEntity.class)
                .addAnnotatedClass(EmployeeassistancerequestEntity.class)
                .addAnnotatedClass(EmployeeEntity.class)
                .addAnnotatedClass(InvoiceEntity.class)
                .addAnnotatedClass(MembershipaccountEntity.class)
                .addAnnotatedClass(ProductcategoryEntity.class)
                .addAnnotatedClass(ProductcategoryEntity.class)
                .addAnnotatedClass(ProductEntity.class)
                .addAnnotatedClass(ProductorderEntity.class)
                .addAnnotatedClass(SpecialproductsEntity.class)
                .addAnnotatedClass(SpecialproductcategoryEntity.class)
                .addAnnotatedClass(SupplierEntity.class)
                .addAnnotatedClass(TaxcategoryEntity.class)
                .buildSessionFactory();
    }


    public static DatabaseEndpoint getDatabaseEndpoint() {
        return databaseEndpoint;
    }
    public static int basicEmployeeReturn0ManagerReturn1(String name){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "select e.name, e.password from EmployeeEntity e " +
                "where e.employeeId not in (select nvl(e2.managerid,0) from EmployeeEntity e2) " +
                "and e.name = :name";
        Query query = session.createQuery(sql);
        query.setParameter("name", name);
        List is_employee_manager = query.list();
        System.out.println(is_employee_manager);
        session.close();
        return !is_employee_manager.isEmpty() ? 0 : 1;
    }
    public static int[] login(String name, String password){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "select e.employeeId, e.gender from EmployeeEntity e where e.name=:name and e.password=:password";
        Query query = session.createQuery(sql);
        //System.out.println(query);
        query.setParameter("name", name)
                .setParameter("password", password);
        List list = query.list();
        Object[] input = null;
        if(!list.isEmpty())
            input = (Object[]) list.get(0);

        session.close();


        return new int[] {!list.isEmpty() ? 1 : 0, !list.isEmpty() ? ((Long)input[0]).intValue() : -1};
    }

    public void saveNewAssistanceRequest(int empId, String description) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        AssistancerequestEntity ar = new AssistancerequestEntity();
        ar.setDescription(description);
        //Date date = LocalDate.now();

        ar.setDatetimeofrequest(new Date());
        int requestId = ((Long)session.save(ar)).intValue();

        EmployeeassistancerequestEntity ear = new EmployeeassistancerequestEntity();
        ear.setRequestId(requestId);
        ear.setEmployeeId(empId);
        session.save(ear);

        session.getTransaction().commit();
        session.close();
    }
    public ArrayList<ProductEntity> getAllProducts(){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "from ProductEntity";
        Query query = session.createQuery(sql);
        ArrayList<ProductEntity> listOfProducts = (ArrayList<ProductEntity>) query.list();
        return listOfProducts;
    }
    public static void closeConnection(){
        factory.close();
        System.out.println("closing works");
    }

}
