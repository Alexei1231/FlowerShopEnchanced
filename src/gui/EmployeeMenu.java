package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import files.FileOperations;
import model.*;

import java.awt.*;
import java.util.ArrayList;

public class EmployeeMenu extends JFrame {
    private JTextField nameField, ageField;
    private JComboBox<String> statusBox;
    private JTable table;
    private DefaultTableModel tableModel;
    Shop shop;
    FileOperations fileOperations;

    EmployeeMenu(Shop flowerShop) {
        setTitle("Employee Menu");
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
        ageField = new JTextField();
        statusBox = new JComboBox<>(new String[]{"Working", "Fired"});

        formPanel.add(new JLabel("Name:"));
        formPanel.add(new JLabel("Age:"));
        formPanel.add(new JLabel("Status:"));
        formPanel.add(nameField);
        formPanel.add(ageField);
        formPanel.add(statusBox);


        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1, 5, 10, 10));
        //panelButtons.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JButton addEmployeeButton = new JButton("Add Employee");
        JButton removeEmployeeButton = new JButton("Remove Employee");
        JButton editEmployeeButton = new JButton("Edit Employee");
        JButton saveButton = new JButton("Save");
        JButton saveAndExitButton = new JButton("Save & Exit");

        JPanel panelTable = new JPanel();
        panelTable.setLayout(new GridLayout(1, 1, 10, 10));
        panelTable.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        ArrayList<Employee> employeesData = shop.getEmployees();//employees data that adds to the table

        tableModel = new DefaultTableModel(new String[]{"Name", "Age", "Status"}, 0);//table and tablemodel
        table = new JTable(tableModel);
        for (Employee employee : employeesData) {
            tableModel.addRow(new Object[]{employee.getName(), employee.getAge(), employee.getIsFired() ? "Fired" : "Active"});
        }

        JScrollPane scrollPane = new JScrollPane(table);


        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);


        panelButtons.add(addEmployeeButton);
        panelButtons.add(removeEmployeeButton);
        panelButtons.add(editEmployeeButton);
        panelButtons.add(saveButton);
        panelButtons.add(saveAndExitButton);

        addEmployeeButton.addActionListener(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String status = (String) statusBox.getSelectedItem();
            if (name.isEmpty() || age < 0) return;

            Employee emp = new Employee(name, age, status.equals("Fired"));
            employeesData.add(emp);
            tableModel.addRow(new Object[]{emp.getName(), emp.getAge(), emp.getIsFired() ? "Fired" : "Active"});
            clearForm();
            refreshTable();
        });

        editEmployeeButton.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String status = (String) statusBox.getSelectedItem();

                Employee emp = new Employee(name, age, status.equals("Fired"));
                employeesData.set(selected, emp);

                tableModel.setValueAt(emp.getName(), selected, 0);
                tableModel.setValueAt(emp.getAge(), selected, 1);
                tableModel.setValueAt(emp.getIsFired() ? "Fired" : "Active", selected, 2);
                clearForm();
                refreshTable();

            }
        });

        saveButton.addActionListener(e -> {
            saveCurrentState(employeesData);
        });

        saveAndExitButton.addActionListener(e -> {
           saveCurrentState(employeesData);
           dispose();
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0 && selected < employeesData.size()) {
                Employee emp = employeesData.get(selected);
                nameField.setText(emp.getName());
                ageField.setText(String.valueOf(emp.getAge()));
                statusBox.setSelectedItem(emp.getIsFired() ? "Fired" : "Active");
            }
        });


    }

    private void clearForm() {
        nameField.setText("");
        ageField.setText("");
        statusBox.setSelectedIndex(0);
    }

    private void refreshTable() {
        ArrayList<Employee> employeesData = shop.getEmployees();
        shop.setEmployees(employeesData);
        tableModel.setRowCount(0);
        for (Employee emp : employeesData) {
            tableModel.addRow(new Object[]{emp.getName(), emp.getAge(), emp.getIsFired() ? "Fired" : "Active"});
        }
    }

    private void saveCurrentState(ArrayList<Employee> employeesData) {
        fileOperations.saveEmployees(employeesData);
    }
}
