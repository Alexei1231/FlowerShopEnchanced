package model;

public class Employee {
    //private int id;
    private String name;
    private int age;
    private boolean isFired = false;

//    public int getId() {
//        return id;
//    }

//    public void setId(int tempID) {
//        this.id = tempID;
//    }

    public String getName() {
        return name;
    }

    public void setName(String tempName) {
        this.name = tempName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int tempAge) {
        this.age = tempAge;
    }

    public Employee(String name, int age) {
        //this.id = id;
        this.name = name;
        this.age = age;
    }

    public Employee(String name, int age, boolean isFired) {
        this.name = name;
        this.age = age;
        this.isFired = isFired;
    }

    public Employee() {
    }

    public Employee(int id) {}

    public void setIsFired(boolean fired) {
        isFired = fired;
    }

    public boolean getIsFired() {
        return isFired;
    }
}
