public class KitchenAppliance extends Product {

    private int wattage;
    private String color;
    private String brand;



    // getters
    public KitchenAppliance (double p, int stock, int w, String c, String b){
        super(p, stock);
        wattage = w;
        color = c;
        brand = b;
    }
    public int getWattage(){
        return wattage;
    }

    public String getColor(){
        return color;
    }

    public String getBrand(){
        return brand;
    }

}
