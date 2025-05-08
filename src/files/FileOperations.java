package files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Employee;
import model.Flower;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileOperations {


    public void saveEmployees(ArrayList<Employee> employees) {
        try {
            FileWriter fw = new FileWriter("employees.json", false);
            Gson gson = new Gson();
            fw.write(gson.toJson(employees));
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFlowers(ArrayList<Flower> flowers) {
        try {
            FileWriter fw = new FileWriter("flowers.json", false);
            Gson gson = new Gson();
            fw.write(gson.toJson(flowers));
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Employee> readEmployees() {
        try {
            FileReader fr = new FileReader("employees.json");
            Gson gson = new Gson();
            Type employeesListType = new TypeToken<ArrayList<Employee>>() {
            }.getType();
            ArrayList<Employee> loadedEmployees = gson.fromJson(fr, employeesListType);
            return loadedEmployees;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Flower> readFlowers() {
        try {
            FileReader fr = new FileReader("flowers.json");
            Gson gson = new Gson();
            Type flowersListType = new TypeToken<ArrayList<Flower>>() {
            }.getType();
            ArrayList<Flower> loadedFlowers = gson.fromJson(fr, flowersListType);
            return loadedFlowers;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
