package com.example.CashRegister;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.*;

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
                .addAnnotatedClass(OrderEntity.class)
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
        String sql = "select e.employeeId, e.gender from EmployeeEntity e where lower(e.name)=:name and e.password=:password";
        Query query = session.createQuery(sql);
        //System.out.println(query);
        query.setParameter("name", name.toLowerCase( Locale.ROOT ) )
                .setParameter("password", password);
        List list = query.list();
        Object[] input = null;
        if(!list.isEmpty())
            input = (Object[]) list.get(0);

        session.close();


        return new int[] {!list.isEmpty() ? 1 : 0, !list.isEmpty() ? ((Long)input[0]).intValue() : -1};
    }

    public ArrayList<Object[]> getRankedEmployeeListTemplate(String conditions) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "select e.employeeId, e.name, nvl(sum(o.ordertotalprice),0) as sales " +
                "from EmployeeEntity e left join OrderEntity o ON e.employeeId = o.employeeId " +
                 conditions +
                "group by e.employeeId, e.name " +
                "order by sales desc ";

        Query query = session.createQuery(sql);
        List list = query.list();

        ArrayList<Object[]> employeeRankingList = new ArrayList<>();
        int i = 1;
        for (Object o : list){
            Object[] input = (Object[])o;
            employeeRankingList.add(new Object[] {i++, input[1], input[2]});
        }

        session.close();

        return employeeRankingList;
    }

    public ArrayList<Object[]> getRankedEmployeeListDay() {
        String condition = "and to_char(o.orderdate) = to_char(sysdate) ";
        return getRankedEmployeeListTemplate(condition);
    }

    public ArrayList<Object[]> getRankedEmployeeListMonth() {
        String condition = "and to_char(o.orderdate, 'YYYY MON') = to_char(sysdate, 'YYYY MON') ";
        return getRankedEmployeeListTemplate(condition);
    }

    public ArrayList<Object[]> getRankedEmployeeListQuarter() {
        String condition = "and to_char(o.orderdate, 'YYYY') = to_char(sysdate, 'YYYY') " +
        "and ceil(to_number(to_char(orderdate, 'MM'))*4/12) = ceil(to_number(to_char(sysdate, 'MM'))*4/12) ";
        return getRankedEmployeeListTemplate(condition);
    }

    public ArrayList<Object[]> getRankedEmployeeListYear() {
        String condition = "and to_char(o.orderdate, 'YYYY') = to_char(sysdate, 'YYYY') ";
        return getRankedEmployeeListTemplate(condition);
    }


    public ArrayList<Object[]> getRankedEmployeeListAllTime() {
        return getRankedEmployeeListTemplate("");
    }

    public ArrayList<Object[]> getRankedSupplierListTemplate(String conditions) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "select s.nip, s.name, nvl(sum(po.priceforproduct),0) as sales " +
                "from ProductEntity p join ProductorderEntity po ON p.productId = po.productId " +
                "join OrderEntity o ON po.orderId = o.orderId " +
                "right join SupplierEntity s on s.nip = p.suppliernip " +
                conditions +
                "group by s.nip, s.name " +
                "order by sales desc ";

        Query query = session.createQuery(sql);
        List list = query.list();

        ArrayList<Object[]> supplierRankingList = new ArrayList<>();
        int i = 1;
        for (Object o : list){
            Object[] input = (Object[])o;
            supplierRankingList.add(new Object[] {i++, input[1], input[2]});
        }

        session.close();

        return supplierRankingList;
    }

    public ArrayList<Object[]> getRankedSupplierListDay() {
        String condition = "and to_char(o.orderdate) = to_char(sysdate) ";
        return getRankedSupplierListTemplate(condition);
    }

    public ArrayList<Object[]> getRankedSupplierListMonth() {
        String condition = "and to_char(o.orderdate, 'YYYY MON') = to_char(sysdate, 'YYYY MON') ";
        return getRankedSupplierListTemplate(condition);
    }

    public ArrayList<Object[]> getRankedSupplierListQuarter() {
        String condition = "and to_char(o.orderdate, 'YYYY') = to_char(sysdate, 'YYYY') " +
                "and ceil(to_number(to_char(orderdate, 'MM'))*4/12) = ceil(to_number(to_char(sysdate, 'MM'))*4/12) ";
        return getRankedSupplierListTemplate(condition);
    }

    public ArrayList<Object[]> getRankedSupplierListYear() {
        String condition = "and to_char(o.orderdate, 'YYYY') = to_char(sysdate, 'YYYY') ";
        return getRankedSupplierListTemplate(condition);
    }

    public ArrayList<Object[]> getRankedSupplierListAllTime() {
        return getRankedSupplierListTemplate("");
    }


    public double getProfitTemplate(String conditions) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "select sum(ordertotalprice) " +
                "from OrderEntity " +
                conditions;

        Query query = session.createQuery(sql);
        List list = query.list();

        double input = (double)list.get(0);

        session.close();

        return input;
    }

    public double getProfitDay() {
        String condition = "where to_char(orderdate) = to_char(sysdate)";
        return getProfitTemplate(condition);
    }

    public double getProfitMonth() {
        String condition = "where to_char(orderdate, 'YYYY MON') = to_char(sysdate, 'YYYY MON')";
        return getProfitTemplate(condition);
    }

    public double getProfitQuarter() {
        String condition = "where to_char(orderdate, 'YYYY') = to_char(sysdate, 'YYYY') " +
        "and ceil(to_number(to_char(orderdate, 'MM'))*4/12) = ceil(to_number(to_char(sysdate, 'MM'))*4/12)";
        return getProfitTemplate(condition);
    }

    public double getProfitYear() {
        String condition = "where to_char(orderdate, 'YYYY') = to_char(sysdate, 'YYYY')";
        return getProfitTemplate(condition);
    }

    public double getProfitAllTime() {
        return getProfitTemplate("");
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
        session.close();
        return listOfProducts;
    }
    public void addProductOrderEntity(long productamount,
                                      long productId,
                                      long orderid){
//       long productamount, dostajemy
//      long taxcategoryname, wyciagamy
//      long taxprice, dostajemy, wyciagamy i obliczamy
//      double priceforproduct, wyciagamy
//      long productId, dostajemy
//      long orderid dostajemy
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "select TC.taxcategoryId, TC.percentagetax, P.price " +
                "from ProductEntity P, ProductcategoryEntity PC, TaxcategoryEntity TC " +
                "where P.productId=:id " +
                "and PC.productcategoryId=P.productcategoryId " +
                "and PC.taxcategoryId=TC.taxcategoryId";
        Query query = session.createQuery(sql);
        query.setParameter("id", productId);
        Object [] listedParameters = (Object[])query.list().get(0);

        long taxId = (long) listedParameters[0];
        float taxPercentageTax = (float) listedParameters[1];
        double price = (double) listedParameters[2] * productamount;
        double taxprice = price * productamount * taxPercentageTax;

        ProductorderEntity ProductOrder = new ProductorderEntity();
        ProductOrder.setProductamount(productamount);
        ProductOrder.setTaxcategoryname(taxId);
        ProductOrder.setTaxprice(taxprice);
        ProductOrder.setPriceforproduct(price);
        ProductOrder.setProductId(productId);
        ProductOrder.setOrderId(orderid);

        session.save(ProductOrder);

        //Commit the transaction
        session.getTransaction().commit();

        System.out.println("Added product to order. hoohee");
        session.close();
    }

    public long addOrderEntity(double totalPrice,
                               long cashRegisterNumber,
                               String transactionType,
                               long membershipCardId,
                               long invoiceId,
                               long couponId,
                               long employeeId){
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        OrderEntity Order = new OrderEntity();
        Order.setOrderdate(new java.sql.Date(new Date().getTime()));
        Order.setOrdertotalprice(totalPrice);
        Order.setCashregisternumber(cashRegisterNumber);
        Order.setTransactiontype(transactionType);
        Order.setMembershipId(membershipCardId);
        Order.setInvoiceId(invoiceId);
        Order.setCouponId(couponId);
        Order.setEmployeeId(employeeId);
        long p = (long) session.save(Order);
        //Commit the transaction
        session.getTransaction().commit();

        System.out.println("Added order. hoohee");
        session.close();
        return p;
    }
    public String getCouponEntity(long couponCode){
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "from CouponEntity CE where :id=CE.couponcode";
        Query query = session.createQuery(sql);
        query.setParameter("id", couponCode);
        List result = query.list();
        CouponEntity coupon;
        String returnStatement;
        if( !result.isEmpty() ) {
            coupon = (CouponEntity) query.list().get(0);
            returnStatement = "" + (coupon.getTypeofcoupon().equals("P") ? "procent " : "liczba ") +
                    coupon.getCouponamount() + " " + coupon.getCouponId();
        }
        else{
            returnStatement = "";
        }
        session.close();
        return returnStatement;
    }
//
    public static void closeConnection(){
        factory.close();
        System.out.println("closing works");
    }

    public void addInvoiceEntity(InvoiceEntity invoiceEntity) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(invoiceEntity);
        session.getTransaction().commit();
    }

    public double getTaxAmount(long order_id) {
        System.out.println("getTaxAmount" + order_id);
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "select sum(sum(p.price * tc.percentagetax)) " +
                "from OrderEntity o " +
                "join ProductorderEntity po on o.orderId = po.orderId" +
                "    join ProductEntity p on po.productId = p.productId" +
                "    join ProductcategoryEntity pc on p.productcategoryId = pc.productcategoryId" +
                "    join TaxcategoryEntity tc on pc.taxcategoryId = tc.taxcategoryId" +
                "        where o.orderId = :id" +
                "        group by tc.percentagetax";
        Query query = session.createQuery(sql);
        query.setParameter("id", (long)order_id);
        System.out.println("getTaxAmount" + query.list().get(0));
        double result = (double) query.list().get(0);
        session.close();
        return result;
    }

    public void updateInvoiceEntity(InvoiceEntity invoiceEntity) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.update(invoiceEntity);
        session.getTransaction().commit();
        session.close();
    }
    public ArrayList<AssistanceRequestClassForEditing> getCustomEmployeeAssistanceEntity() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        String sql = "select e.name, e.lastname, e.employeeId, ar.description, ar.datetimeofrequest " +
                "from EmployeeassistancerequestEntity ea, EmployeeEntity e, AssistancerequestEntity ar " +
                "where ea.requestId = ar.requestId and e.employeeId=ea.employeeId";
        Query query = session.createQuery(sql);
        List<Object[]> output = query.list();
        ArrayList<AssistanceRequestClassForEditing> listOfCustomAssistanceRequest = new ArrayList<>();
        if( output.isEmpty() )
            listOfCustomAssistanceRequest = null;
        else
            for( int i = 0 ; i < output.size() ; ++i )
                listOfCustomAssistanceRequest.add( new AssistanceRequestClassForEditing( output.get(i) ));

        session.close();
        return listOfCustomAssistanceRequest;
    }
}
