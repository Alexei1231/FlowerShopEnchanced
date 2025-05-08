package gui;

import files.FileOperations;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class MainMenu extends JFrame {
     Shop flowerShop;


    public MainMenu(Shop flowerShop) {
        super("FlowerShopEnchanced");
        this.flowerShop = flowerShop;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        initGui();


    }

    private void initGui() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JTable employeesTable = new JTable();

        JButton employeesButton = new JButton("Change Employees");
        JButton flowersButton = new JButton("Change Flowers");
        JButton storeSettingsButton = new JButton("Shop Parameters");
        JButton exitButton = new JButton("Exit");

        employeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeMenu(flowerShop).setVisible(true);
            }

        });

        flowersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FlowerMenu(flowerShop).setVisible(true);
            }
        });

        storeSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShopMenu(flowerShop).setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFlowersAndEmployeesToFile();
                System.exit(0);
            }
        });

        panel.add(employeesButton);
        panel.add(flowersButton);
        panel.add(storeSettingsButton);
        panel.add(exitButton);

        add(panel);
    }

    void saveFlowersAndEmployeesToFile() {
        FileOperations fileOperations = new FileOperations();
        fileOperations.saveEmployees(flowerShop.getEmployees());
        fileOperations.saveFlowers(flowerShop.getFlowers());
    }
}
