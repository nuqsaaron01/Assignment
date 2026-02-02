import java.util.Scanner;

class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void show() {
        System.out.println("Name: " + name + " | Price: " + price);
    }
}

class Vape extends Product {
    private String flavor;
    private int stock;

    public Vape(String name, double price, String flavor, int stock) {
        super(name, price);
        this.flavor = flavor;
        this.stock = stock;
    }

    public void setStock(int s) {
        this.stock = s;
    }

    @Override
    public void show() {
        System.out.println("Item: " + name + " [" + flavor + "] - P" + price + " | Stock: " + stock);
    }
}

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vape[] list = new Vape[50]; 
        int n = 0; 

        int choice = 0;
        while (choice != 5) {
            System.out.println("\n--- VAPE INVENTORY ---");
            System.out.println("1. Add Product");
            System.out.println("2. Display All");
            System.out.println("3. Edit Stock");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 1) {
                if (n < 50) {
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Price: "); double price = sc.nextDouble(); sc.nextLine();
                    System.out.print("Flavor: "); String flavor = sc.nextLine();
                    System.out.print("Stock: "); int stock = sc.nextInt();
                    
                    list[n] = new Vape(name, price, flavor, stock);
                    n++;
                    System.out.println("Done!");
                }
            } 
            else if (choice == 2) {
                if (n == 0) System.out.println("List is empty.");
                for (int i = 0; i < n; i++) {
                    System.out.print((i + 1) + ". ");
                    list[i].show();
                }
            } 
            else if (choice == 3) {
                System.out.print("Index: "); int idx = sc.nextInt() - 1;
                if (idx >= 0 && idx < n) {
                    System.out.print("New Stock: ");
                    list[idx].setStock(sc.nextInt());
                }
            } 
            else if (choice == 4) {
                System.out.print("Index: "); int idx = sc.nextInt() - 1;
                if (idx >= 0 && idx < n) {
                    for (int i = idx; i < n - 1; i++) {
                        list[i] = list[i + 1];
                    }
                    n--;
                    System.out.println("Removed.");
                }
            }
        }
        System.out.println("Bye!");
    }
}
