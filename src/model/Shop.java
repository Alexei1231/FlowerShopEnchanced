package model;

import java.util.ArrayList;

public class Shop {
    private int shopID;
    private String shopName;
    private String shopAddress;
    ArrayList<Employee> employees;
    ArrayList<Flower> flowers;

    public Shop() {
        this.shopID = 0;
    }

    public Shop(int shopID, String shopName, String shopAddress) {
        this.shopID = shopID;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        employees = new ArrayList<Employee>();
        flowers = new ArrayList<Flower>();
    }



    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setFlowers(ArrayList<Flower> flowers) {
        this.flowers = flowers;
    }

    public ArrayList<Flower> getFlowers() {
        return flowers;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }


}
