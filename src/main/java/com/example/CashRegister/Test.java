package com.example.CashRegister;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Test {

    public Test() throws IOException {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Application app = Application.getApp();
        app.start();
    }
}

