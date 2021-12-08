package com.example.CashRegister;

public class Application {
    private static Application app = new Application();

    MainFrame mainFrame;

    public static Application getApp() {
        return app;
    }

    public void start() {
        mainFrame = new MainFrame();
        mainFrame.initialize();
    }

    public boolean login(String username, String password) {

        String realUser = "user";
        String realPass = "pass";
        System.out.println("Your credentials: " + username + " " + password);

        if (username.equals(realUser) && password.equals(realPass)) {
            System.out.println("Successfully logged in as: " + username);
            mainFrame.loggedIn(username, 1);// status indicates if succesfully logged in and what privildedges are granted to the user
            return true;
        }

        System.out.println("Invalid credentials");
        return false;

    }
}
