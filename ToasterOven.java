public class ToasterOven extends KitchenAppliance {

    int width;
    boolean convection;

    public ToasterOven (double p, int stock, int w, String c, String b, int width, boolean convection){
        super(p,stock,w,c,b);
        this.width = width;
        this.convection = convection;
    }

    // inherits double sellUnits(int amount) from Product

    public String toString(){
        if (convection){
            return width + " inch " + getBrand() + "Toaster with convection (" + getColor() + ", " + getWattage() + " watts) (" + getPrice() + " dollars each, " + getStockQuantity() + " in stock, " + getSoldQuantity() + " sold)";
        } else {
            return width + " inch " + getBrand() + "Toaster (" + getColor() + ", " + getWattage() + " watts) (" + getPrice() + " dollars each, " + getStockQuantity() + " in stock, " + getSoldQuantity() + " sold)";
        }
    }
}
