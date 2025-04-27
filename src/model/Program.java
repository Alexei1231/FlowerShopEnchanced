package model;

import java.util.ArrayList;

import java.util.Scanner;

public class Program {

    Scanner sc = new Scanner(System.in);
    private boolean workingStage = true;

    Shop flowerShop;

    public Program() {
        flowerShop = new Shop(0, "FlowerShop", "default");
        menu();
    }

    int parceViaConsoleAndCheckInt() { //setting a value to an integer via console with a check of the datatype
        int a = 0;
        boolean flag = true;
        while (flag) {
            try {
                a = sc.nextInt();
                flag = false;
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.next();//thanks to sc.next() we avoid a cycle of exceptions from happening
            }
        }
        return a;
    }

    void addEmployeeToShop() {
        System.out.println("Enter employee name: ");
        String name = sc.next();

        System.out.println("Enter employee age: ");
        int age = parceViaConsoleAndCheckInt();


        //System.out.println("Enter employee ID: ");
//        int id = -999;  //an old way of checking the int value to avoid exceptions
//        while (id == -999) {
//            try {
//                id = sc.nextInt();
//            } catch (Exception e) {
//                System.out.println("Invalid ID");
//                sc.next();
//            }
//        }
        //int id = parceViaConsoleAndCheckInt();
        Employee employee = new Employee(name, age);
        flowerShop.addEmployee(employee);


    }

    void addFlowerToShop() {

        System.out.println("Enter the article of the flower");
        int article = parceViaConsoleAndCheckInt();
        System.out.println("Enter the name of the flower: ");
        String flowerName = sc.nextLine();
        while (flowerName.isBlank()) {
            System.out.println("Enter the name of the flower: ");
            flowerName = sc.nextLine();
        }
        System.out.println("Enter the price of the flower: ");
        int price = parceViaConsoleAndCheckInt();

        Flower flower = new Flower(article, flowerName, price);
        flowerShop.flowers.add(flower);

    }

    void showEmployees() {
        for (Employee emp : flowerShop.getEmployees()) {
            System.out.println("id: " + flowerShop.employees.indexOf(emp) + ", name: " + emp.getName() + ", age: " + emp.getAge() + ", fired: " + emp.getIsFired());
        }
    }

//    Employee getEmployeeFromCollection(ArrayList<Employee> items) {
//        Scanner sc = new Scanner(System.in);  // Assumes a scanner is available.
//        Employee employee = null;
//        boolean found = false;
//
//        while (!found) {
//            try {
//                System.out.print("Please enter a valid employee ID: ");
//                int id = parceViaConsoleAndCheckInt();
//
//                // Check that ID is within the valid range
//                if (id < 0 || id >= items.size()) {
//                    System.out.println("ID not found in the list. Please try again.");
//                } else {
//                    employee = items.get(id);
//                    found = true;
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Please enter a valid integer ID.");
//                sc.next(); // Clears the invalid input from the scanner.
//            } catch (Exception e) {
//                System.out.println("An unexpected error occurred. Please try again.");
//            }
//        }
//
//        return employee;
//    }

    void fireEmployeeById() {
        showEmployees();
        int id = -999;
        while(id<0 || id>flowerShop.getEmployees().size()) {
            System.out.println("Enter the correct id of the employee you would like to fire: ");
            id = parceViaConsoleAndCheckInt();
        }
        Employee employee = flowerShop.employees.get(id);
        employee.setIsFired(true);
        flowerShop.employees.set(id, employee);
    }

    void bringEmployeeBack() {
        showEmployees();
        int id = -999;
        while(id<0 || id>flowerShop.getEmployees().size()) {
            System.out.println("Enter the correct id of the employee you would like to unfire: ");
            id = parceViaConsoleAndCheckInt();
        }
        Employee employee = flowerShop.employees.get(id);
        employee.setIsFired(false);
        flowerShop.employees.set(id, employee);
    }

    void showFlowers() {
        for (Flower flower : flowerShop.getFlowers()) {
            System.out.println("id: " + flowerShop.flowers.indexOf(flower) + ", article: " + flower.getArticle() + ", name: " + flower.getName() + ", price: " + flower.getPrice() + ", availability: " + flower.getAvailable());
        }
    }

    void setFlowerStatusToUnavailable() {
        showFlowers();
        int id = -999;
        while(id<0 || id>flowerShop.flowers.size()) {
            System.out.println("Enter the correct id of the flower you'd like to change status to unavailable: ");
            id = parceViaConsoleAndCheckInt();
        }
        Flower flower = flowerShop.flowers.get(id);
        flower.setAvailable(false);
        flowerShop.flowers.set(id, flower);
    }

    void  setFlowerStatusToAvailable(){
        showFlowers();
        int id = -999;
        while(id<0 || id>flowerShop.flowers.size()) {
            System.out.println("Enter the correct id of the flower you'd like to change status to available: ");
            id = parceViaConsoleAndCheckInt();
        }
        Flower flower = flowerShop.flowers.get(id);
        flower.setAvailable(true);
        flowerShop.flowers.set(id, flower);
    }


    public void menu() {
        while (workingStage) {
            System.out.println("1 - to add an employee to your shop\n" +
                    "2 - to add a new flower \n" +
                    "3 - to show employees \n" +
                    "4 - to fire an employee\n" +
                    "5 - to bring an employee back\n" +
                    "6 - to show items\n" +
                    "7 - to change item's status to unavailable\n" +
                    "8 - to change item's status to available\n" +
                    "9 - to shut the program down\n");

            int userChoice = parceViaConsoleAndCheckInt();
            switch (userChoice) {
                case 1:
                    addEmployeeToShop();
                    this.menu();
                    break;
                case 2:
                    addFlowerToShop();
                    this.menu();
                    break;
                case 3:
                    showEmployees();
                    this.menu();
                    break;
                case 4:
                    fireEmployeeById();
                    this.menu();
                    break;
                case 5:
                    bringEmployeeBack();
                    this.menu();
                    break;
                case 6:
                    showFlowers();
                    this.menu();
                    break;
                case 7:
                    setFlowerStatusToUnavailable();
                    this.menu();
                    break;
                case 8:
                    setFlowerStatusToAvailable();
                    this.menu();
                    break;
                case 9:
                    this.workingStage = false;
                    break;
                default:
                    menu();
            }

        }
    }

}
