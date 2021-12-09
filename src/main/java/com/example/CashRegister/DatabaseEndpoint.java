package com.example.CashRegister;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class DatabaseEndpoint extends Thread {
    private static DatabaseEndpoint databaseEndpoint = new DatabaseEndpoint();
    private SessionFactory factory;

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

    public int basicEmployeeReturn0ManagerReturn1(String name) {
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
//comment
    public boolean login(String name, String password) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "select e.name, e.password from EmployeeEntity e where e.name=:name and e.password=:password";
        Query query = session.createQuery(sql);
        System.out.println(query);
        query.setParameter("name", name)
                .setParameter("password", password);
        List nameInDatabase = query.list();
        session.close();
        return !nameInDatabase.isEmpty();
    }

    public void addTaxcategoryEntity(String name, float percentage){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        TaxcategoryEntity taxcategoryEntity = new TaxcategoryEntity();
        taxcategoryEntity.setPercentagetax(percentage);
        taxcategoryEntity.setTaxname(name);
        session.save(taxcategoryEntity);

        //Commit the transaction
        session.getTransaction().commit();
        session.close();
        System.out.println("Added tax category. hoohee");
    }
    public void deleteTaxcategoryEntity(long id){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        TaxcategoryEntity taxcategoryEntity = new TaxcategoryEntity();
        taxcategoryEntity.setTaxcategoryId(id);
        session.delete(taxcategoryEntity);

        //Commit the transaction
        session.getTransaction().commit();
        session.close();
        System.out.println("Deleted tax category. hoohee");
    }

    public void updateTaxcategoryEntity(long id, String name, float tax){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "from TaxcategoryEntity TC where TC.taxcategoryId=:id";
        Query query = session.createQuery(sql);
        query.setParameter("id", id);
        List nameInDatabase = query.list();

        TaxcategoryEntity taxcategorytoEdit = (TaxcategoryEntity) nameInDatabase.get(0);
        taxcategorytoEdit.setTaxname(name);
        taxcategorytoEdit.setPercentagetax(tax);
        session.update(taxcategorytoEdit);

        //Commit the transaction
        session.getTransaction().commit();
        System.out.println("Updated tax category. hoohee");
        session.close();
    }

    public void closeConnection(){
        factory.close();
        System.out.println("closing works");
    }

}
