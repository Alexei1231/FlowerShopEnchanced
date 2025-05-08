import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gui.FlowerMenu;
import gui.MainMenu;
import model.Employee;
import model.Flower;
import model.Shop;
import files.FileOperations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainGUI {
    public static void main(String[] args) {
        Shop flowerShop = new Shop(0, "defaultShopName", "defaultShopAddress");

        FileOperations fileOperations = new FileOperations();
        flowerShop.setEmployees(fileOperations.readEmployees());
        flowerShop.setFlowers(fileOperations.readFlowers());

        MainMenu mainMenu = new MainMenu(flowerShop);
        mainMenu.setVisible(true);
    }
}
