import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

abstract class Product implements Serializable {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract void displayDetails();
}

class Vape extends Product implements Serializable {
    private String flavor;
    private int stock;

    public Vape(String name, double price, String flavor, int stock) {
        super(name, price);
        this.flavor = flavor;
        this.stock = stock;
    }

    public String getName() { return name; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public void displayDetails() {
        System.out.println("Product : " + name);
        System.out.println("Flavor  : " + flavor);
        System.out.println("Price   : ₱" + price);
        System.out.println("Stock   : " + stock);
        System.out.println("-------------------------");
    }
}

public class MainApp {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        ArrayList<Vape> inventory = new ArrayList<>();
        String file = "vape_data.ser";

        File f = new File(file);
        if (f.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            inventory = (ArrayList<Vape>) ois.readObject();
            ois.close();
        } else {
            inventory.add(new Vape("Smok Novo X", 950, "Mango Ice", 10));
            inventory.add(new Vape("Geekvape Aegis", 1200, "Grape Freeze", 8));
        }

        int choice = 0;

        while (choice != 5) {
            System.out.println("\nVAPE SHOP");
            System.out.println("1. Add");
            System.out.println("2. View");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            input.nextLine(); 

            if (choice == 1) {
                System.out.print("Name: "); String n = input.nextLine();
                System.out.print("Price: "); double p = input.nextDouble();
                input.nextLine(); 
                System.out.print("Flavor: "); String fl = input.nextLine();
                System.out.print("Stock: "); int s = input.nextInt();
                inventory.add(new Vape(n, p, fl, s));
            } 
            else if (choice == 2) {
                for (int i = 0; i < inventory.size(); i++) {
                    System.out.println("ID: " + (i + 1));
                    inventory.get(i).displayDetails();
                }
            } 
            else if (choice == 3) {
                System.out.print("Enter ID: ");
                int id = input.nextInt() - 1;
                if (id >= 0 && id < inventory.size()) {
                    System.out.print("New Stock: ");
                    inventory.get(id).setStock(input.nextInt());
                }
            } 
            else if (choice == 4) {
                System.out.print("Enter ID: ");
                int id = input.nextInt() - 1;
                if (id >= 0 && id < inventory.size()) {
                    inventory.remove(id);
                }
            }
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(inventory);
            oos.close();
        }
    }
}
