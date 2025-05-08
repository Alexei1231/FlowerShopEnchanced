package gui;

import files.FileOperations;
import model.Employee;
import model.Flower;
import model.Shop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FlowerMenu extends JFrame {

    private JTextField nameField, articleField, priceField;
    private JComboBox<String> statusBox;
    private JTable table;
    private DefaultTableModel tableModel;
    Shop shop;
    FileOperations fileOperations;

    FlowerMenu(Shop flowerShop) {
        setTitle("Flowers Menu");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        this.shop = flowerShop;
        fileOperations = new FileOperations();

        initGui();
    }

    private void initGui() {
        JPanel formPanel = new JPanel();//fields
        formPanel.setLayout(new GridLayout(2, 3, 10, 10));
        nameField = new JTextField();
        articleField = new JTextField();
        priceField = new JTextField();
        statusBox = new JComboBox<>(new String[]{"Available", "Not Available"});

        formPanel.add(new JLabel("Name:"));
        formPanel.add(new JLabel("Article:"));
        formPanel.add(new JLabel("Price:"));
        formPanel.add(new JLabel("Status:"));
        formPanel.add(nameField);
        formPanel.add(articleField);
        formPanel.add(priceField);
        formPanel.add(statusBox);


        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1, 5, 10, 10));
        //panelButtons.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JButton addFlowerButton = new JButton("Add Flower");
        JButton editFlowerButton = new JButton("Edit Flower");
        JButton removeFlowerButton = new JButton("Remove Flower");
        JButton saveButton = new JButton("Save");
        JButton saveAndExitButton = new JButton("Save & Exit");

        JPanel panelTable = new JPanel();
        panelTable.setLayout(new GridLayout(1, 1, 10, 10));
        panelTable.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        ArrayList<Flower> flowersData = shop.getFlowers();//employees data that adds to the table

        tableModel = new DefaultTableModel(new String[]{"Name", "Age", "Status"}, 0);//table and tablemodel
        table = new JTable(tableModel);
        for (Flower flower : flowersData) {
            tableModel.addRow(new Object[]{flower.getName(), flower.getArticle(), flower.getPrice(), flower.getAvailable() ? "Available" : "Not Available"});
        }

        JScrollPane scrollPane = new JScrollPane(table);


        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);


        panelButtons.add(addFlowerButton);
        panelButtons.add(removeFlowerButton);
        panelButtons.add(editFlowerButton);
        panelButtons.add(saveButton);
        panelButtons.add(saveAndExitButton);

        addFlowerButton.addActionListener(e -> {
            String name = nameField.getText();
           int price = Integer.parseInt(priceField.getText());
           int article = Integer.parseInt(articleField.getText());
           String status = (String) statusBox.getSelectedItem();
            if (name.isEmpty() || price < 0) return;

            Flower flo = new Flower(name, article, price, status.equals("Available"));
            flowersData.add(flo);
            tableModel.addRow(new Object[]{flo.getName(), flo.getArticle(), flo.getAvailable() ? "Available" : "Not Available"});
            clearForm();
            refreshTable();
        });

        editFlowerButton.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                String name = nameField.getText();
                int price = Integer.parseInt(priceField.getText());
                int article = Integer.parseInt(articleField.getText());
                String status = (String) statusBox.getSelectedItem();

                Flower flo = new Flower(name, article, article, status.equals("Available"));
                flowersData.set(selected, flo);
                tableModel.setValueAt(flo.getName(), selected, 0);
                tableModel.setValueAt(flo.getArticle(), selected, 1);
                tableModel.setValueAt(flo.getPrice(), selected, 2);
                tableModel.setValueAt(flo.getAvailable() ? "Available" : "Not Available", selected, 3);
                clearForm();
                refreshTable();

            }
        });

        saveButton.addActionListener(e -> {
            saveCurrentState(flowersData);
        });

        saveAndExitButton.addActionListener(e -> {
            saveCurrentState(flowersData);
            dispose();
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0 && selected < flowersData.size()) {
                Flower flo = flowersData.get(selected);
                nameField.setText(flo.getName());
                articleField.setText(String.valueOf(flo.getArticle()));
                articleField.setText(String.valueOf(flo.getPrice()));
                statusBox.setSelectedItem(flo.getAvailable() ? "Available" : "Not Available");
            }
        });


    }

    private void clearForm() {
        nameField.setText("");
        articleField.setText("");
        statusBox.setSelectedIndex(0);
    }

    private void refreshTable() {
        ArrayList<Flower> flowersData = shop.getFlowers();
        shop.setFlowers(flowersData);
        tableModel.setRowCount(0);
        for (Flower flo : flowersData) {
            tableModel.addRow(new Object[]{flo.getName(), flo.getArticle(),flo.getPrice(), flo.getAvailable() ? "Available" : "Not Available"});
        }
    }

    private void saveCurrentState(ArrayList<Flower> flowersData) {
        fileOperations.saveFlowers(flowersData);
    }
}
