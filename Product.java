public class Product {

    private double price;
    private int stockQuantity;
    private int soldQuantity;

    public Product (double p, int stock){
        p = price;
        stock = stockQuantity;
    }

    double sellUnits(int amount){
        if (amount>=stockQuantity){
            stockQuantity -= amount;
            soldQuantity += amount;
            return price*amount;
        } else {
            return 0.0;
        }
    }

    // getters

    public double getPrice(){

        return price;
    }

    public int getStockQuantity(){
        return stockQuantity;
    }

    public int getSoldQuantity(){

        return soldQuantity;
    }

}
