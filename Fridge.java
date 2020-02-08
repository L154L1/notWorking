public class Fridge extends KitchenAppliance {

    private double cubicFeet;
    private boolean hasFreezer;

    public Fridge (double p, int stock, int w, String c, String b, double cubicFeet, boolean h){
        super(p,stock,w,c,b);
        this.cubicFeet = cubicFeet;
        h = hasFreezer;
    }

    // inherits double sellUnits(int amount) from Product

    public String toString() {
        if (hasFreezer) {
            return cubicFeet + " cu. ft. Sub Zero Fridge with Freezer (" + getColor() + ", " + getWattage() + ") (" + getPrice() + "dollars each, " + getStockQuantity() + " in stock, " + getSoldQuantity() + "sold)";
        } else {
            return cubicFeet + " cu. ft. Sub Zero Fridge (" + getColor() + ", " + getWattage() + ") (" + getPrice() + "dollars each, " + getStockQuantity() + " in stock, " + getSoldQuantity() + "sold)";

        }
    }
}
