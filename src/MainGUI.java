import gui.FlowerMenu;
import gui.MainMenu;
import model.Shop;
import files.FileOperations;

public class MainGUI {
    public static void main(String[] args) {
        Shop flowerShop = new Shop(0, "defaultShopName", "defaultShopAdress");
        FileOperations fileOperations = new FileOperations();
        flowerShop.setEmployees(fileOperations.readEmployees());
        flowerShop.setFlowers(fileOperations.readFlowers());

        MainMenu mainMenu = new MainMenu(flowerShop);
        mainMenu.setVisible(true);

    }
}
