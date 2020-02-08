import java.util.Scanner;

public class ElectronicStore {

    private final int MAX_PRODUCTS = 10;
    private String name;
    private double revenue;
    private Product[] products = new Product[MAX_PRODUCTS];

    public ElectronicStore(String n) {
         name = n;
    }

    public String getName() {
        return name;
    }

    public boolean addProduct(Product p) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = p;
                return true;
            }
        }
        return false;
    }

    public void sellProducts() {
        // displaying stock
        int maxCounter = 0;                                             // to check input later
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                System.out.println(i + "." + products[i]);
                maxCounter = i;
            }
        }

        // taking in input from user, checking input
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of the product you wish to purchase:");
        int Choice = in.nextInt();
        if (Choice < 0 || Choice > maxCounter) {
            System.out.println("Sorry, that is not a valid selection. Please try again.");
            return;
        }
        System.out.println("Enter how many of product" + Choice + "you would like to purchase:");
        int Number = in.nextInt();
        if (Number < 1 || Number > products[Choice].getStockQuantity()) {
            System.out.println("Sorry, that is not a valid selection. Please try again.");
            return;
        }

        // updating variables "making sale" - using sellUnits method defined in Product.java
        revenue += products[Choice].sellUnits(Number);
    }

    public void sellProducts(int item, int amount) {
        int maxCounter = 0;                                             // to check input later
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                maxCounter = i;
            }
        }
        if (item >= 0 && item > maxCounter && amount >= 1 && amount > products[item].getStockQuantity()) {
            revenue += products[item].sellUnits(amount);
        }
    }

    public double getRevenue(){
        return revenue;
    }

    public void printStock(){
        System.out.println(name + "'s Starting Stock Is:");
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                System.out.println(i + "." + products[i]);
            }
        }
    }

}
