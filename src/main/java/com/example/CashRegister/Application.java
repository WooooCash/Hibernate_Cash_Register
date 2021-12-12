package com.example.CashRegister;

public class Application {
//    needed for closing application database shutdown
//      #TODO uncomment for database connection

    public class ShutdownConnectionClose extends Thread {
        public void run() {
            databaseEndpoint.closeConnection();
        }
    }

    private static Application app = new Application();
    private int currentUserId;
//      #TODO uncomment for database connection
    private DatabaseEndpoint databaseEndpoint = DatabaseEndpoint.getDatabaseEndpoint();
    MainFrame mainFrame;
    public static Application getApp() {
        return app;
    }

    public void start() {
        try{
            //      #TODO uncomment for database connection
            Runtime.getRuntime().addShutdownHook(new ShutdownConnectionClose());
            mainFrame = new MainFrame();
            mainFrame.initialize();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public boolean login(String username, String password) {

        String realUser = "user";
        String realPass = "pass";
        System.out.println("Your credentials: " + username + " " + password);

//             #TODO uncomment for database connection
       int[] loginInfo = databaseEndpoint.login(username, password);
       if( loginInfo[0] == 1){
//        if ( username.equals(realUser) && password.equals(realPass) ) {
            System.out.println("Successfully logged in as: " + username);
            currentUserId = loginInfo[1];
//             #TODO uncomment for database connection
            int isEmployeeManager = databaseEndpoint.basicEmployeeReturn0ManagerReturn1(username);
            mainFrame.loggedIn(username, isEmployeeManager);// status indicates if succesfully logged in and what privildedges are granted to the user
            //mainFrame.loggedIn(username, 0);// status indicates if succesfully logged in and what privildedges are granted to the user

            return true;
        }
        else
            System.out.println("Invalid credentials");
        return false;
    }

    public void sendAssistanceRequest(String description) {
        //send this description to database
        databaseEndpoint.saveNewAssistanceRequest(currentUserId, description);
        System.out.println("Description: " + description);
        mainFrame.setDashboardPage(0);
    }
    public int getCurrentUserId(){
        return currentUserId;
    }
}
