package files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Employee;
import model.Flower;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperations {
//    public String employeesJson;
//    public String flowersJson;
//
//    FileOperations(ArrayList<Employee> employees, ArrayList<Flower> flowers) {
//        employeesJson = new Gson().toJson(employees);
//        flowersJson = new Gson().toJson(flowers);
//    }

    public void saveEmployees() {
        try {
            FileWriter fw = new FileWriter("employees.json", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFlowers() {
        try {
            FileWriter fw = new FileWriter("flowers.json", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
